/*
 * Copyright © 9/15/2022, Pexers (https://github.com/Pexers)
 */

package model

import model.projects.CloudProject
import model.projects.ProjectData
import model.specifics.CloudSpecifics
import controller.propertyNotFoundAndExit

interface CloudProvider {
    val companion: CloudCompanion
    var projects: List<ProjectData>
    val project: CloudProject
    val cloudSpecifics: CloudSpecifics?

    suspend fun requestProjects(): List<ProjectData>
    fun setProjectData(projectName: String) {
        val projectData = projects.find { proj -> proj.name == projectName }
        if (projectData == null) {
            propertyNotFoundAndExit(projectName)
            return
        }
        project.projectData = projectData
        project.function.bucket.bucketData.name = ""  // Selected bucket depends on the selected project
    }
}
