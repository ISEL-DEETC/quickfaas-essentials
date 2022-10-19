/*
 * Copyright © 7/21/2022, Pexers (https://github.com/Pexers)
 */

package model

import model.projects.MsAzureProject
import model.projects.MsAzureProjectData
import model.projects.ProjectData
import model.requests.MsAzureRequests
import model.specifics.MsAzureSpecifics
import propertyNotFoundAndExit

class MsAzureProvider : CloudProvider {

    companion object : CloudCompanion {
        override val name = "Microsoft Azure"
        override val shortName = "msazure"
        override val cloudRequests = MsAzureRequests
        override fun newCloudProvider(): CloudProvider = MsAzureProvider()
    }

    override val companion = Companion
    override var projects: List<ProjectData> = listOf()
    override val project = MsAzureProject()
    override val cloudSpecifics = MsAzureSpecifics()

    override suspend fun requestProjects(): List<ProjectData> {
        project.projectData.name = ""
        projects = MsAzureRequests.getResourceGroups(cloudSpecifics.subscription.subscriptionId).value
        return projects
    }

    override fun setProjectData(projectName: String) {
        val projectData = projects.find { proj -> proj.name == projectName }
        if (projectData == null) {
            propertyNotFoundAndExit(projectName)
            return
        }
        project.projectData = projectData
        // Save subscription ID in project data
        (project.projectData as MsAzureProjectData).subscriptionId = cloudSpecifics.subscription.subscriptionId
        project.function.bucket.bucketData.name = ""
    }

}
