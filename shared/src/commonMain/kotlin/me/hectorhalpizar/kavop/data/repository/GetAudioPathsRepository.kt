package me.hectorhalpizar.kavop.data.repository

interface GetAudioPathsRepository {
    fun getAudioPaths(): Set<String>
}