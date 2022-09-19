/*
 * Copyright © 9/15/2022, Pexers (https://github.com/Pexers)
 */

package model.specifics

import model.DeploymentData

interface CloudSpecifics {
    fun setSpecifics(deploymentData: DeploymentData)
}
