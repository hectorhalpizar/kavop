package me.hectorhalpizar.kavop.data.datasource

import me.hectorhalpizar.kavop.domain.Audio
import me.hectorhalpizar.kavop.util.MediaPath

interface MediaFetcher {

    suspend fun loadAudio(mediaPath: MediaPath): Set<Audio>
}
