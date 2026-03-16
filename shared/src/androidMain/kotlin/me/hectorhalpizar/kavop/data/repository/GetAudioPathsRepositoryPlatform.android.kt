package me.hectorhalpizar.kavop.data.repository

import android.content.Context
import me.hectorhalpizar.kavop.ApplicationContextHolder
import me.hectorhalpizar.kavop.data.repository.AudioKeyValue.AUDIO_PATHS_KEY
import me.hectorhalpizar.kavop.data.repository.AudioKeyValue.KAVOP_AUDIO_PLAYER_PREFERENCES

internal class AndroidGetAudioPathsRepository : GetAudioPathsRepository {
    override fun getAudioPaths(): Set<String> {
        val context = ApplicationContextHolder.context
        val prefs = context.getSharedPreferences(KAVOP_AUDIO_PLAYER_PREFERENCES,
            Context.MODE_PRIVATE)
        val result = prefs.getStringSet(AUDIO_PATHS_KEY, emptySet()) as Set<String>
        return result
    }
}

internal actual
    fun getAudioPathsRepository(): GetAudioPathsRepository = AndroidGetAudioPathsRepository()
