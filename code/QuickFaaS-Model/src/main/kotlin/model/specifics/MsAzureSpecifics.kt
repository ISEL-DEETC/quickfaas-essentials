/*
 * Copyright © 7/21/2022, Pexers (https://github.com/Pexers)
 */

package model.specifics

import kotlinx.serialization.Serializable
import model.requests.MsAzureRequests

@Serializable
data class SubscriptionsData(var value: List<SubscriptionData>)

@Serializable
data class SubscriptionData(var subscriptionId: String = "", var displayName: String = "", val state: String = "")

class MsAzureSpecifics : CloudSpecifics {
    val subscription = SubscriptionData()
    var subscriptions: List<SubscriptionData> = listOf()

    suspend fun requestSubscriptions(): List<SubscriptionData> {
        subscription.displayName = ""
        subscription.subscriptionId = ""
        subscriptions = MsAzureRequests.getSubscriptions().value
        return subscriptions
    }

    fun setSubscriptionData(idx: Int) {
        val selectedSubscription = subscriptions[idx]
        subscription.displayName = selectedSubscription.displayName
        subscription.subscriptionId = selectedSubscription.subscriptionId
    }

}
