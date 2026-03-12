package me.hectorhalpizar.kavop.data.datasource

import me.hectorhalpizar.kavop.domain.Audio

interface MediaFetcher {
    fun fetchAudio(): Set<Audio>
}
