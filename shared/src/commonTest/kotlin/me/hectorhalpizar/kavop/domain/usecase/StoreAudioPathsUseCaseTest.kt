package me.hectorhalpizar.kavop.domain.usecase

import kotlinx.coroutines.test.runTest
import me.hectorhalpizar.kavop.data.repository.StoreAudioPathsRepository
import me.hectorhalpizar.kavop.util.MediaPath
import mockedMediaPathDirectoryIsFalse
import mockedMediaPathDirectoryIsTrue
import kotlin.test.Test
import kotlin.test.assertIs

class StoreAudioPathsUseCaseTest {

    @Test
    fun store_path_with_a_blank_path() = runTest {
        // Given
        val testInstance = StoreAudioPathsUseCase(storeAudioPathsRepository)

        // When
        val result = testInstance.invoke(null)

        // Then
        assertIs<StoreAudioPathsResult.FilePathValueIsEmpty>(result)
    }

    @Test
    fun store_path_with_a_mocked_is_file_directory_false() = runTest {
        // Given
        val testInstance = StoreAudioPathsUseCase( storeAudioPathsRepository)

        // When
        val result = testInstance.invoke(mockedMediaPathDirectoryIsFalse)

        // Then
        assertIs<StoreAudioPathsResult.FilePathMustBeFolder>(result)
    }

    @Test
    fun store_path_with_a_mocked_is_file_directory_true() = runTest {
        // Given
        val testInstance = StoreAudioPathsUseCase( storeAudioPathsRepository)
        val fakeFilePath = "/fake/file/path"

        // When
        val result = testInstance.invoke(mockedMediaPathDirectoryIsTrue)

        // Then
        assertIs<StoreAudioPathsResult.Success>(result)
    }

    @Test
    fun store_pat_with_a_error_state() = runTest {
        // Given
        val testInstance = StoreAudioPathsUseCase( storeAudioPathsRepositoryWithException)

        // When
        val result = testInstance.invoke(mockedMediaPathDirectoryIsTrue)

        // Then
        assertIs<StoreAudioPathsResult.Error>(result)
    }

    private val storeAudioPathsRepository = object : StoreAudioPathsRepository {

        override fun addNewPath(newPath: MediaPath) { }
    }

    private val storeAudioPathsRepositoryWithException = object : StoreAudioPathsRepository {

        override fun addNewPath(newPath: MediaPath) {
            throw Exception("This is a test exception")
        }

    }
}