/*
 * Copyright © 7/19/2022, Pexers (https://github.com/Pexers)
 */

package model.resources.functions.runtimes.utils

import model.Utils
import model.resources.functions.runtimes.Runtime

interface RuntimeUtils {
    val runtime: Runtime

    fun readTemplateFile(fileName: String, sourcesDir: String) =
        Utils.readResFile(filePath = "${runtime.templatesDir}/$sourcesDir/$fileName")

    fun createResourcesFile(fileName: String, content: String, tmpDirName: String) =
        Utils.createFileWithDirs(directories = "${runtime.tmpDir}/$tmpDirName/src/main/resources", fileName, content)

    fun copySourceFileToTmp(fileName: String, sourcesDir: String, tmpDirName: String) =
        Utils.copyFile(
            sourceFile = "${Utils.RESOURCES}/${runtime.templatesDir}/$sourcesDir/$fileName",
            targetFile = "${runtime.tmpDir}/$tmpDirName/$fileName"
        )

}