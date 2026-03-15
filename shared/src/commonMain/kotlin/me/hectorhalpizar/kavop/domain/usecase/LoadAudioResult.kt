package me.hectorhalpizar.kavop.domain.usecase

import me.hectorhalpizar.kavop.domain.Audio

sealed class LoadAudioResult {
    data class Success(val audioLoaded: Set<Audio>?) : LoadAudioResult()
    data object FilePathValueIsEmpty : LoadAudioResult()
    data object FilePathMustBeFolder : LoadAudioResult()
    data class Error(val errorMessage: String?) : LoadAudioResult()
}