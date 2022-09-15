/*
 * Copyright © 9/15/2022, Pexers (https://github.com/Pexers)
 */

package model.resources.functions.runtimes.scripts

import model.resources.functions.CloudFunction

interface CloudBuildScripts {
    fun javaBuildScript(func: CloudFunction, sourcesDir: String, tmpDirName: String)
    fun nodeJsBuildScript()
}