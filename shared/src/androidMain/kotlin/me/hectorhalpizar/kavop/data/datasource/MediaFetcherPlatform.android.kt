package me.hectorhalpizar.kavop.data.datasource

import android.media.MediaMetadataRetriever
import android.util.Log
import androidx.documentfile.provider.DocumentFile
import kotlinx.coroutines.suspendCancellableCoroutine
import me.hectorhalpizar.kavop.ApplicationContextHolder
import me.hectorhalpizar.kavop.domain.Audio
import me.hectorhalpizar.kavop.util.AndroidMediaPath
import me.hectorhalpizar.kavop.util.MediaPath
import me.hectorhalpizar.kavop.util.uri.pathSafe
import kotlin.coroutines.resume

internal class AndroidMediaFetcher : MediaFetcher {

    override suspend fun loadAudio(mediaPath: MediaPath): Set<Audio> = suspendCancellableCoroutine { cont ->

        val result : MutableSet<Audio> = mutableSetOf()

        val androidMediaPath = mediaPath as AndroidMediaPath
        val files = androidMediaPath.path.listFiles()

        val audioPaths = filter(files)

        audioPaths.forEach { file ->
            try {
                val audio = getAudioFromMediaRetriever(file)
                result.add(audio)
            } catch (e: Exception) {
                val errorMessage = "Error getting Audio Info from MediaRetriever with file \"${file.uri.path}\". " +
                                   "Current Audio Files. $result."
                Log.w(TAG, errorMessage, e)
            }
        }

        cont.resume(result)
    }

    private fun filter(files: Array<DocumentFile>): Array<DocumentFile> {
        val result = mutableSetOf<DocumentFile>()

        files.forEach { file ->
            when {
                file.isDirectory -> {
                    val files = filter(file.listFiles())
                    result.addAll(files)
                }

                file.isFile -> {
                    val extension = file.uri.pathSafe.split(".").last()

                    if (extension in audioFileExtensionFilter) {
                        file.uri.pathSafe.let { path ->
                            result.add(file)
                        }
                    }
                }
            }
        }

        return result.toTypedArray()
    }

    private fun getAudioFromMediaRetriever(file: DocumentFile): Audio {

        val retriever = MediaMetadataRetriever()
        retriever.setDataSource(ApplicationContextHolder.context, file.uri)

        val title =
            retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE)
        val artist =
            retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST)
        val album =
            retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM)
        val duration =
            retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)?.toLong()

        val audio = Audio(0, title, artist, album, duration, file.uri.path, null)

        return audio
    }

    companion object {
        private const val TAG = "AndroidMediaFetcher"
    }

}

internal actual fun getMediaFetcher(): MediaFetcher = AndroidMediaFetcher()