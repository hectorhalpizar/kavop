package me.hectorhalpizar.kavop.domain.usecase

sealed class DisplayAudioPathsResult {

    data object Init : DisplayAudioPathsResult()
    data class Success(val audioPaths: Set<String>) : DisplayAudioPathsResult()
    data class Error(val errorMessage: String) : DisplayAudioPathsResult()
}