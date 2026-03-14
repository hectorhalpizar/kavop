package me.hectorhalpizar.kavop.data.datasource

import me.hectorhalpizar.kavop.domain.Audio
import me.hectorhalpizar.kavop.util.MediaPath

internal class IosMediaFetcher : MediaFetcher {

    override suspend fun loadAudio(mediaPath: MediaPath): Set<Audio> {
        TODO("Not yet implemented")
    }
}

actual fun getMediaFetcher(): MediaFetcher = IosMediaFetcher()