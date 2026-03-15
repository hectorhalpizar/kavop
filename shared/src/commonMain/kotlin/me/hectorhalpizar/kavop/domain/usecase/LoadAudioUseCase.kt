package me.hectorhalpizar.kavop.domain.usecase

import me.hectorhalpizar.kavop.data.datasource.MediaFetcher
import me.hectorhalpizar.kavop.domain.UseCase
import me.hectorhalpizar.kavop.util.MediaPath
import me.hectorhalpizar.kavop.util.getThrowableMessage

class LoadAudioUseCase(
    private val mediaFetcher: MediaFetcher
) : UseCase<MediaPath, LoadAudioResult> {

    override suspend fun invoke(path: MediaPath?): LoadAudioResult {

        return try{
            if (path == null) {
                LoadAudioResult.FilePathValueIsEmpty
            } else if (!path.isDirectory) {
                LoadAudioResult.FilePathMustBeFolder
            } else {
                val audios = mediaFetcher.loadAudio(path)
                LoadAudioResult.Success(audios)
            }
        } catch (e: Exception) {
            val errorMessage = e.getThrowableMessage()
            LoadAudioResult.Error(errorMessage)
        }
    }
}