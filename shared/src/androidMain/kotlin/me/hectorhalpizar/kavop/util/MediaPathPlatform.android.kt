package me.hectorhalpizar.kavop.util

import androidx.documentfile.provider.DocumentFile

internal class AndroidMediaPath : MediaPath {

    internal lateinit var path: DocumentFile

    override val isDirectory: Boolean
        get() = path.isDirectory
}

internal actual fun createMediaPath(): MediaPath = AndroidMediaPath()