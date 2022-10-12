/*
 * Copyright © 9/15/2022, Pexers (https://github.com/Pexers)
 */

package controller

import kotlinx.coroutines.runBlocking
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import model.DeploymentData
import model.GcpProvider
import model.MsAzureProvider
import model.Utils
import kotlin.system.exitProcess

private val cloudProviders = arrayOf(MsAzureProvider, GcpProvider)  // Supported cloud providers

fun main() {
    // Read function deployment JSON file
    val deploymentJson: DeploymentData = Json.decodeFromString(Utils.readFile("./func-deployment.json"))

    // Set cloud provider
    val cloudProvider = cloudProviders.find { cc -> cc.shortName == deploymentJson.cloudProvider }?.newCloudProvider()
    if (cloudProvider == null) propertyNotFoundAndExit("cloudProvider")

    cloudProvider!!.let { cp ->
        // Set access token
        cp.companion.cloudRequests.setBearerToken(deploymentJson.accessToken)

        // Set cloud-specific attributes. Example: subscriptionID for MsAzure
        cp.cloudSpecifics?.setSpecifics(deploymentJson)

        // Set project
        runBlocking { cp.requestProjects() } // HTTP request
        cp.setProjectData(deploymentJson.project)

        // Fulfill Function instance data
        cp.project.function.let { fn ->
            // Set function name & location
            fn.name = deploymentJson.function.name
            fn.location = deploymentJson.function.location

            // Set bucket
            runBlocking { cp.project.requestBuckets() } // HTTP request
            cp.project.setBucketData(deploymentJson.function.bucket)

            // Set trigger
            fn.setTrigger(deploymentJson.function.trigger, cp.project.buckets)

            // Set runtime & respective version
            fn.setRuntimeVersion(deploymentJson.function.runtime)

            // Set Template function file based on language & trigger
            val langConfig = fn.runtimeVersion!!.runtime.language.getConfigurations()
            fn.hookFunction.templateFile =
                langConfig.triggers.first { trig -> trig.name == fn.trigger.shortName }.templateFile

            // Set Hook function definition
            fn.hookFunction.definition = Utils.readFile(deploymentJson.functionFile)

            // Set custom dependencies. Not mandatory
            if (deploymentJson.dependenciesFile != null && deploymentJson.dependenciesFile.isNotEmpty()) fn.hookFunction.dependencies =
                Utils.readFile(deploymentJson.dependenciesFile)

            // Set custom JSON configurations. Not mandatory
            if (deploymentJson.configurationsFile != null && deploymentJson.configurationsFile.isNotEmpty()) fn.hookFunction.configurations =
                Utils.readFile(deploymentJson.configurationsFile)
        }

        // Build function project
        val tmpDir = cp.project.function.buildAndZip(cp.companion.shortName)

        // Deploy serverless function
        runBlocking { // this: CoroutineScope
            cp.project.function.deployZip("${tmpDir.path}/${Utils.ZIP_FILE}", cp.project.projectData) // HTTP requests
        }
        // Delete tmpDir
        if (General.AUTO_DELETE_FUNC_ZIP && tmpDir.path.isNotEmpty()) tmpDir.deleteRecursively()

        println("Deployment finished successfully!")
    }
}

/**
 * Informs user that a given JSON [property] from the func-deployment.json was not found.
 */
fun propertyNotFoundAndExit(property: String) {
    println("'$property' not found.")
    exitProcess(1)
}
