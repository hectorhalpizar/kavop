package me.hectorhalpizar.kavop.data.repository

import me.hectorhalpizar.kavop.util.MediaPath

interface StoreAudioPathsRepository {

    fun addNewPath(newPath: MediaPath)
}
