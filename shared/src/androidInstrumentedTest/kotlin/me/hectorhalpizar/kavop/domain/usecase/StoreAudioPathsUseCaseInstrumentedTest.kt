package me.hectorhalpizar.kavop.domain.usecase

import android.content.Context
import android.os.Environment
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.test.runTest
import me.hectorhalpizar.kavop.ApplicationContextHolder
import me.hectorhalpizar.kavop.data.repository.AudioKeyValue.AUDIO_PATHS_KEY
import me.hectorhalpizar.kavop.data.repository.AudioKeyValue.KAVOP_AUDIO_PLAYER_PREFERENCES
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertEquals
import kotlin.test.assertIs

@RunWith(AndroidJUnit4::class)
@Ignore("Refactoring in Progress")
class StoreAudioPathsUseCaseInstrumentedTest {

    @Before
    fun setup() {
        ApplicationContextHolder.context =
            InstrumentationRegistry.getInstrumentation().targetContext.applicationContext
    }

    @Test
    fun store_path_with_a_mocked_is_file_directory_false() = runTest {
        // Given
        val testInstance = StoreAudioPathsUseCase()
        val fakeFilePath = "/fake/file/path"

//        // When
//        val result = testInstance.invoke(fakeFilePath)
//
//        // Then
//        assertIs<StoreAudioPathsResult.FilePathMustBeFolder>(result)
    }

    @Test
    fun store_path_with_a_mocked_is_file_directory_true() = runTest {
        // Given
        val testInstance = StoreAudioPathsUseCase()
        val musicFolderPath =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).absolutePath
        val expectedPath = setOf<String>(musicFolderPath)

//        // When
//        val result1 = testInstance.invoke(musicFolderPath)
//        val result2 = testInstance.invoke(musicFolderPath)
//        val result3 = testInstance.invoke(musicFolderPath)
//
//        // Then
//        assertIs<StoreAudioPathsResult.Success>(result1)
//        assertIs<StoreAudioPathsResult.Success>(result2)
//        assertIs<StoreAudioPathsResult.Success>(result3)
//
//        assertEquals(expectedPath, storedPaths)
    }

    private val storedPaths : Set<String?>?
        get() {
            val context = ApplicationContextHolder.context
            val prefs = context.getSharedPreferences(KAVOP_AUDIO_PLAYER_PREFERENCES,
                                                     Context.MODE_PRIVATE)
            return prefs.getStringSet(AUDIO_PATHS_KEY, emptySet())
        }
}