package me.hectorhalpizar.kavop.domain.usecase

sealed class StoreAudioPathsResult {
    data object Init : StoreAudioPathsResult()
    data object Success : StoreAudioPathsResult()
    data object FilePathValueIsEmpty : StoreAudioPathsResult()
    data object FilePathMustBeFolder : StoreAudioPathsResult()
    data class Error(val errorMessage: String): StoreAudioPathsResult()
}