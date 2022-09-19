/*
 * Copyright © 9/15/2022, Pexers (https://github.com/Pexers)
 */

package model.specifics

import kotlinx.coroutines.runBlocking
import kotlinx.serialization.Serializable
import model.DeploymentData
import model.requests.MsAzureRequests
import controller.propertyNotFoundAndExit

@Serializable
data class SubscriptionsData(var value: List<SubscriptionData>)

@Serializable
data class SubscriptionData(var subscriptionId: String = "", var displayName: String = "", val state: String = "")

class MsAzureSpecifics : CloudSpecifics {
    val subscription = SubscriptionData()
    var subscriptions: List<SubscriptionData> = listOf()

    override fun setSpecifics(deploymentData: DeploymentData) {
        runBlocking { requestSubscriptions() } // HTTP request
        val subscriptionData = subscriptions.find { sub -> sub.subscriptionId == deploymentData.subscriptionId }
        if (subscriptionData == null) {
            propertyNotFoundAndExit(deploymentData.subscriptionId ?: "Subscription ID")
            return
        }
        subscription.displayName = subscriptionData.displayName
        subscription.subscriptionId = subscriptionData.subscriptionId
    }

    private suspend fun requestSubscriptions(): List<SubscriptionData> {
        subscription.displayName = ""
        subscription.subscriptionId = ""
        subscriptions = MsAzureRequests.getSubscriptions().value
        return subscriptions
    }

}
