/*
 * Copyright © 7/21/2022, Pexers (https://github.com/Pexers)
 */

package model

import kotlinx.serialization.Serializable

@Serializable
data class DeploymentData(
    val cloudProvider: String,
    val accessToken: String,
    val project: String,
    val function: FunctionDeploymentData,
    val functionFile: String,
    val dependenciesFile: String?,      // Optional
    val configurationsFile: String?,    // Optional
)

@Serializable
data class FunctionDeploymentData(
    val name: String,
    val location: String,
    val bucket: String,
    val runtime: String,
    val trigger: TriggerDeploymentData
)

@Serializable
data class TriggerDeploymentData(
    val type: String,
    val bucket: String?,    // Storage
    val eventType: String?  // Storage
)