package me.hectorhalpizar.kavop.domain.usecase

import kotlinx.coroutines.test.runTest
import me.hectorhalpizar.kavop.data.datasource.MediaFetcher
import me.hectorhalpizar.kavop.domain.Audio
import me.hectorhalpizar.kavop.util.MediaPath
import mockedMediaPathDirectoryIsFalse
import mockedMediaPathDirectoryIsTrue
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

class LoadAudioUseCaseTest {

    @Test
    fun load_audio_with_a_mocked_is_file_directory_false() = runTest {
        // Given
        val testInstance = LoadAudioUseCase(mockMediaFetcher)

        // When
        val result = testInstance.invoke(mockedMediaPathDirectoryIsFalse)

        // Then
        assertIs<LoadAudioResult.FilePathMustBeFolder>(result)

    }

    @Test
    fun load_audio_with_a_mocked_is_file_directory_true() = runTest {
        // Given
        val testInstance = LoadAudioUseCase(mockMediaFetcher)

        // When
        val result = testInstance.invoke(mockedMediaPathDirectoryIsTrue)

        // Then
        assertIs<LoadAudioResult.Success>(result)
        assertEquals(6, result.audioLoaded?.size)
    }

    @Test
    fun load_audio_with_a_error_state() = runTest {
        // Given
        val testInstance = LoadAudioUseCase(mockMediaFetcherWithException)

        // When
        val result = testInstance.invoke(mockedMediaPathDirectoryIsTrue)

        // Then
        assertIs<LoadAudioResult.Error>(result)
    }

    private val mockMediaFetcherWithException: MediaFetcher
        get() = object : MediaFetcher {
            override suspend fun loadAudio(mediaPath: MediaPath): Set<Audio> {
                throw Exception("This is a mocked Exception",
                                    IllegalStateException("Test this an illegal state exception"))
            }

        }


    private val mockMediaFetcher: MediaFetcher
        get() = object : MediaFetcher {

            override suspend fun loadAudio(mediaPath: MediaPath): Set<Audio> {
                return mutableSetOf(
                    Audio(0, "Pawn", "The Grey Room", null, 185496),
                    Audio(0, "The Reins", "Blue Deer", null, 197616),
                    Audio(0, null, "Blue Deer", null, null),
                    Audio(0, "Fake Long Track", "Fake Artist", null,Long.MAX_VALUE),
                    Audio(0, "Super Fake Long Track Name ", "Fake Artist", null,Long.MIN_VALUE),
                    Audio(0, "Futile", "The Grey Room", null, 192444),
                    // Repeated Audios
                    Audio(0, "Futile", "The Grey Room", null, 192444),
                    Audio(0, "Pawn", "The Grey Room", null, 185496),
                )
            }
        }
}