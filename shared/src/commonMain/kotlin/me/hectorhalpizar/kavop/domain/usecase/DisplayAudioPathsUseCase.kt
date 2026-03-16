package me.hectorhalpizar.kavop.domain.usecase

import me.hectorhalpizar.kavop.data.repository.GetAudioPathsRepository
import me.hectorhalpizar.kavop.data.repository.getAudioPathsRepository
import me.hectorhalpizar.kavop.domain.UseCase
import me.hectorhalpizar.kavop.util.getThrowableMessage

class DisplayAudioPathsUseCase(
    private val repository: GetAudioPathsRepository = getAudioPathsRepository()
): UseCase<Unit, DisplayAudioPathsResult>  {
    override suspend fun invoke(input: Unit?): DisplayAudioPathsResult {
        return try {
            val audioPaths = repository.getAudioPaths()
            DisplayAudioPathsResult.Success(audioPaths)
        } catch (e: Exception) {
            val errorMessage = e.getThrowableMessage()
            DisplayAudioPathsResult.Error(errorMessage)
        }
    }
}
