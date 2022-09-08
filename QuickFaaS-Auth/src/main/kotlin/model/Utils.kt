/*
 * Copyright (c) 7/12/2022, Pexers (https://github.com/Pexers)
 */

package model

import java.awt.Desktop
import java.io.InputStream
import java.net.URI
import java.nio.charset.Charset

object Utils {

    fun openWebPage(url: String): Boolean {
        val uri: URI = URI.create(url)
        val desktop = if (Desktop.isDesktopSupported()) Desktop.getDesktop() else null
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri)
                return true
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return false
    }

    fun readResFile(filePath: String, lfFormat: Boolean = false): String {
        val text: String = readResFileAsStream(filePath)?.readTextAndClose() ?: ""
        return if (lfFormat) text.replace("\r", "") else text
    }

    fun readResFileAsStream(filePath: String): InputStream? = Utils.javaClass.classLoader.getResourceAsStream(filePath)

    // Extension function
    private fun InputStream.readTextAndClose(charset: Charset = Charsets.UTF_8): String {
        return this.bufferedReader(charset).use { it.readText() }
    }

}
