/*
 * Copyright © 9/15/2022, Pexers (https://github.com/Pexers)
 */

package model.projects

import controller.propertyNotFoundAndExit
import model.resources.buckets.BucketData
import model.resources.functions.CloudFunction

interface CloudProject {
    var projectData: ProjectData
    var buckets: List<BucketData>
    val function: CloudFunction

    /**
     * Remote calls provider requesting available buckets
     */
    suspend fun requestBuckets(): List<BucketData>

    /**
     * Find and set function's [BucketData] for the specified [bucketName]
     */
    fun setBucketData(bucketName: String) {
        val bucketData = buckets.find { bucket -> bucket.name == bucketName }
        if (bucketData == null) {
            propertyNotFoundAndExit(bucketName)
            return
        }
        function.bucket.bucketData = bucketData
    }
}
