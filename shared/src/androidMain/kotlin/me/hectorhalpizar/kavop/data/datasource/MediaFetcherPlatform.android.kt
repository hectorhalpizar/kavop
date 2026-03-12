package me.hectorhalpizar.kavop.data.datasource

import android.database.Cursor
import android.provider.MediaStore
import me.hectorhalpizar.kavop.ApplicationContextHolder
import me.hectorhalpizar.kavop.domain.Audio


internal class AndroidMediaFetcher : MediaFetcher {
    override fun fetchAudio(): Set<Audio> {

        val result : MutableSet<Audio> = mutableSetOf()

        val uri = MediaStore.Audio.Media.INTERNAL_CONTENT_URI

        val projection = arrayOf<String?>(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.RELATIVE_PATH,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.DATA
        )

        val cursor: Cursor? = ApplicationContextHolder.context.contentResolver.query(
            uri,
            projection,
            null,
            null,
            MediaStore.Audio.Media.TITLE + " ASC"
        )

        cursor?.let {
            while (cursor.moveToNext()) {
                val title = cursor.getString(
                    cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE)
                )
                val artist = cursor.getString(
                    cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST)
                )
                val album = cursor.getString(
                    cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM)
                )

                val audio = Audio(0, title, artist, album, 0)
                result.add(audio)
            }
        }

        cursor?.close()

        return result
    }

}

actual fun getMediaFetcher(): MediaFetcher = AndroidMediaFetcher()