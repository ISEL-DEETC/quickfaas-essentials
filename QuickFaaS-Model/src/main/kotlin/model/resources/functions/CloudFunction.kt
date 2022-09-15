/*
 * Copyright © 7/19/2022, Pexers (https://github.com/Pexers)
 */

package model.resources.functions

import controller.Configurations.AUTO_DELETE_FUNC_ZIP
import model.TriggerDeploymentData
import model.Utils
import model.projects.ProjectData
import model.resources.buckets.BucketData
import model.resources.buckets.CloudBucket
import model.resources.functions.runtimes.Runtime
import model.resources.functions.runtimes.RuntimeVersion
import model.resources.functions.runtimes.scripts.CloudBuildScripts
import model.resources.functions.triggers.StorageTrigger
import model.resources.functions.triggers.Trigger
import propertyNotFoundAndExit
import java.io.File

interface CloudFunction {
    var name: String
    var hookFunction: HookFunction
    var buildScripts: CloudBuildScripts
    val bucket: CloudBucket
    val locations: List<String>
    var location: String
    val triggers: List<Trigger>
    var trigger: Trigger
    val runtimes: Array<RuntimeVersion>
    var runtimeVersion: RuntimeVersion?

    fun buildAndZip(cpShortName: String): File {
        var tmpDir = File("")
        try {
            tmpDir = Utils.createTempDir(runtimeVersion!!.runtime.tmpDir).toFile()
            val sourcesDir = "$cpShortName/${trigger.shortName}"  // e.g.: {runtime}/gcp/http
            when (runtimeVersion!!.runtime) {
                Runtime.JAVA -> buildScripts.javaBuildScript(this, sourcesDir, tmpDir.name)
                else -> throw NotImplementedError()
            }
        } catch (e: Exception) {
            if (AUTO_DELETE_FUNC_ZIP && tmpDir.path.isNotEmpty()) tmpDir.deleteRecursively()
            throw e
        }
        return tmpDir
    }

    suspend fun deployZip(zipFilePath: String, projData: ProjectData): DeploymentTimeData
    fun getEntryPoint(): String
    fun getTriggerUrl(projData: ProjectData): Pair<String, String>

    fun setTrigger(triggerData: TriggerDeploymentData, projectBuckets:List<BucketData>) {
        val trigger = triggers.find { trigger -> trigger.shortName == triggerData.type }
        if (trigger == null) {
            propertyNotFoundAndExit(triggerData.type)
            return
        }
        this.trigger = trigger
        runtimeVersion = null
        hookFunction.definition = ""
        hookFunction.dependencies = ""

        when (trigger) {
            is StorageTrigger -> {
                val triggerBucket = projectBuckets.find { bucket -> bucket.name == triggerData.bucket }
                if (triggerBucket == null) {
                    propertyNotFoundAndExit(triggerData.bucket ?: "Trigger bucket")
                    return
                }
                trigger.bucketData = triggerBucket
                val eventType =
                    StorageTrigger.EventType.values().find { type -> type.eventName == triggerData.eventType }
                if (eventType == null) {
                    propertyNotFoundAndExit(triggerData.eventType ?: "Event type")
                    return
                }
                trigger.eventType = eventType
            }
            else -> return //TODO.
        }
    }

    fun setRuntimeVersion(runtimeVersionName: String) {
        val (runtimeName, version) = runtimeVersionName.split(
            ("(?=\\d)(?<=\\D)").toRegex(), limit = 2
        )  // d: char, D:number
        val runtime = Runtime.values().find { runtime -> runtime.shortName == runtimeName }
        runtimeVersion = RuntimeVersion.values()
            .find { runtimeVersion -> runtimeVersion.runtime == runtime && runtimeVersion.version == version }
        if (runtimeVersion == null) {
            propertyNotFoundAndExit(runtimeVersionName)
            return
        }
        hookFunction.dependencies = runtimeVersion!!.runtime.dependsSyntax
    }
}
