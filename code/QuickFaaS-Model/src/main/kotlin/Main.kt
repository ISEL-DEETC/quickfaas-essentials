/*
 * Copyright © 7/21/2022, Pexers (https://github.com/Pexers)
 */

import controller.Configurations
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import model.DeploymentData
import model.GcpProvider
import model.MsAzureProvider
import model.Utils
import model.resources.functions.runtimes.Runtime
import model.resources.functions.runtimes.RuntimeVersion
import model.resources.functions.triggers.StorageTrigger
import kotlin.system.exitProcess

private val cloudProviders = arrayOf(MsAzureProvider, GcpProvider)

fun main() { // TODO: MsAzure not yet fully configured. Missing 'subscriptionId'
    val deploymentJson: DeploymentData = Json.decodeFromString(Utils.readFile("./func-deployment.json"))
    val cloudProvider = cloudProviders.find { cc -> cc.shortName == deploymentJson.cloudProvider }?.newCloudProvider()
    if (cloudProvider == null) propertyNotFoundAndExit("cloudProvider")

    cloudProvider!!.let { cp ->
        cp.companion.cloudRequests.setBearerToken(deploymentJson.accessToken)

        // Find and set project
        runBlocking { cp.requestProjects() } // HTTP request
        val projData = cp.projects.find { proj -> proj.name == deploymentJson.project }
        if (projData == null) {
            propertyNotFoundAndExit(deploymentJson.project)
            return
        }
        cp.project.projectData = projData

        // Fulfill Function instance data
        cp.project.function.let { fn ->
            fn.name = deploymentJson.function.name
            fn.location = deploymentJson.function.location
            runBlocking { cp.project.requestBuckets() } // HTTP request
            val bucketData = cp.project.buckets.find { bucket -> bucket.name == deploymentJson.function.bucket }
            if (bucketData == null) {
                propertyNotFoundAndExit(deploymentJson.function.bucket)
                return
            }
            fn.bucket.bucketData = bucketData
            val (runtimeName, version) = deploymentJson.function.runtime.split(
                ("(?=\\d)(?<=\\D)").toRegex(), limit = 2
            )  // d: char, D:number
            val runtime = Runtime.values().find { runtime -> runtime.shortName == runtimeName }
            val runtimeVersion = RuntimeVersion.values()
                .find { runtimeVersion -> runtimeVersion.runtime == runtime && runtimeVersion.version == version }
            if (runtimeVersion == null) {
                propertyNotFoundAndExit(deploymentJson.function.runtime)
                return
            }
            fn.runtimeVersion = runtimeVersion


            val triggerType = deploymentJson.function.trigger.type
            val trigger = fn.triggers.find { trigger -> trigger.shortName == triggerType }
            if (trigger == null) {
                propertyNotFoundAndExit(triggerType)
                return
            }
            fn.trigger = trigger
            when (fn.trigger) {
                is StorageTrigger -> {
                    val triggerBucket =
                        cp.project.buckets.find { bucket -> bucket.name == deploymentJson.function.trigger.bucket }
                    if (triggerBucket == null) {
                        propertyNotFoundAndExit(deploymentJson.function.trigger.bucket ?: "Trigger bucket")
                        return
                    }
                    (fn.trigger as StorageTrigger).bucketData = triggerBucket
                    val eventType = StorageTrigger.EventType.values().find { type -> type.eventName == deploymentJson.function.trigger.eventType }
                    if (eventType == null) {
                        propertyNotFoundAndExit(deploymentJson.function.trigger.eventType ?: "Event type")
                        return
                    }
                    (fn.trigger as StorageTrigger).eventType = eventType

                }
                else -> return //TODO.
            }

            // Set Template function file based on language & trigger
            val langConfig = runtimeVersion.runtime.language.getConfigurations()
            fn.hookFunction.templateFile =
                langConfig.triggers.first { trig -> trig.name == fn.trigger.shortName }.templateFile
            // Set Hook function definition
            fn.hookFunction.definition = Utils.readFile(deploymentJson.functionFile)
            // Not mandatory
            if (deploymentJson.dependenciesFile != null && deploymentJson.dependenciesFile.isNotEmpty()) fn.hookFunction.dependencies =
                Utils.readFile(deploymentJson.dependenciesFile)
            // Not mandatory
            if (deploymentJson.configurationsFile != null && deploymentJson.configurationsFile.isNotEmpty()) fn.hookFunction.configurations =
                Utils.readFile(deploymentJson.configurationsFile)
        }

        // Build function project
        val tmpDir = cp.project.function.buildAndZip(cp.companion.shortName)
        // Deploy serverless function
        runBlocking { // this: CoroutineScope
            cp.project.function.deployZip("${tmpDir.path}/${Utils.ZIP_FILE}", cp.project.projectData) // HTTP requests
        }
        if (Configurations.AUTO_DELETE_FUNC_ZIP && tmpDir.path.isNotEmpty()) tmpDir.deleteRecursively()
    }
}

private fun propertyNotFoundAndExit(propertyName: String) {
    println("'$propertyName' not found.")
    exitProcess(1)
}
