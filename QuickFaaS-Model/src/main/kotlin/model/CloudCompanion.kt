/*
 * Copyright © 7/21/2022, Pexers (https://github.com/Pexers)
 */

package model

import model.requests.CloudRequests

interface CloudCompanion {
    val name: String
    val shortName: String
    val cloudRequests: CloudRequests

    fun newCloudProvider(): CloudProvider
}
