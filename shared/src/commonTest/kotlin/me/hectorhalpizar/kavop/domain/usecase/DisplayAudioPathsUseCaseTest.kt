package me.hectorhalpizar.kavop.domain.usecase

import kotlinx.coroutines.test.runTest
import me.hectorhalpizar.kavop.util.mockedGetAudioPathsRepositoryWithException
import me.hectorhalpizar.kavop.util.mockedGetAudioPathsRepositoryAsSuccess
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

class DisplayAudioPathsUseCaseTest {

    @Test
    fun getAudioPaths_with_error() = runTest {
        // Given
        val testInstance = DisplayAudioPathsUseCase(mockedGetAudioPathsRepositoryWithException)

        // When
        val result = testInstance()

        // Then
        assertIs<DisplayAudioPathsResult.Error>(result)
    }

    @Test
    fun getAudioPaths_successfully() = runTest {
        // Given
        val testInstance = DisplayAudioPathsUseCase(mockedGetAudioPathsRepositoryAsSuccess)

        // When
        val result = testInstance()

        // Then
        assertIs<DisplayAudioPathsResult.Success>(result)
        assertEquals(3, result.audioPaths.size)
    }
}