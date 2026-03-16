package me.hectorhalpizar.kavop.data.repository

import android.content.Context
import me.hectorhalpizar.kavop.ApplicationContextHolder
import me.hectorhalpizar.kavop.data.repository.AudioKeyValue.AUDIO_PATHS_KEY
import me.hectorhalpizar.kavop.data.repository.AudioKeyValue.KAVOP_AUDIO_PLAYER_PREFERENCES
import me.hectorhalpizar.kavop.util.AndroidMediaPath
import me.hectorhalpizar.kavop.util.MediaPath
import me.hectorhalpizar.kavop.util.collections.toSafeMutableSet
import me.hectorhalpizar.kavop.util.uri.pathSafe


internal class AndroidStoreAudioPathsRepository : StoreAudioPathsRepository {

    override fun addNewPath(newPath: MediaPath) {
        newPath as AndroidMediaPath
        val context = ApplicationContextHolder.context
        val prefs = context.getSharedPreferences(KAVOP_AUDIO_PLAYER_PREFERENCES,
            Context.MODE_PRIVATE)
        val currentAudioPaths = prefs.getStringSet(AUDIO_PATHS_KEY, emptySet()).toSafeMutableSet

        currentAudioPaths.add(newPath.path.uri.pathSafe)

        val editor = prefs.edit()
        editor.putStringSet(AUDIO_PATHS_KEY, currentAudioPaths.toSet())
        editor.apply()
    }
}

internal actual fun getStoreAudioRepository(): StoreAudioPathsRepository = AndroidStoreAudioPathsRepository()