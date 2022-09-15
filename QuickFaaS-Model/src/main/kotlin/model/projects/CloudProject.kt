/*
 * Copyright © 9/15/2022, Pexers (https://github.com/Pexers)
 */

package model.projects

import model.resources.buckets.BucketData
import model.resources.functions.CloudFunction
import controller.propertyNotFoundAndExit

interface CloudProject {
    var projectData: ProjectData
    var buckets: List<BucketData>
    val function: CloudFunction

    suspend fun requestBuckets(): List<BucketData>

    fun setBucketData(bucketName: String) {
        val bucketData = buckets.find { bucket -> bucket.name == bucketName }
        if (bucketData == null) {
            propertyNotFoundAndExit(bucketName)
            return
        }
        function.bucket.bucketData = bucketData
    }
}
