package me.hectorhalpizar.kavop.data.datasource

import me.hectorhalpizar.kavop.domain.Audio

internal class IosMediaFetcher : MediaFetcher {
    override fun fetchAudio(): Set<Audio> {
        TODO("Not yet implemented")
    }

}

actual fun getMediaFetcher(): MediaFetcher = IosMediaFetcher()