package me.hectorhalpizar.kavop.domain.usecase

import me.hectorhalpizar.kavop.data.repository.StoreAudioPathsRepository
import me.hectorhalpizar.kavop.data.repository.getStoreAudioRepository
import me.hectorhalpizar.kavop.domain.UseCase
import me.hectorhalpizar.kavop.util.MediaPath
import me.hectorhalpizar.kavop.util.getThrowableMessage

class StoreAudioPathsUseCase(
    private val repository: StoreAudioPathsRepository = getStoreAudioRepository()
): UseCase<MediaPath, StoreAudioPathsResult> {
    override suspend fun invoke(newPath: MediaPath?): StoreAudioPathsResult {
        return try {
            if (newPath == null) {
                StoreAudioPathsResult.FilePathValueIsEmpty
            } else if (!newPath.isDirectory) {
                StoreAudioPathsResult.FilePathMustBeFolder
            } else {
                repository.addNewPath(newPath)
                StoreAudioPathsResult.Success
            }
        } catch (e: Exception) {
            val errorMessage = e.getThrowableMessage()
            StoreAudioPathsResult.Error(errorMessage)
        }
    }
}