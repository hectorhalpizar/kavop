package me.hectorhalpizar.kavop.domain.usecase

import android.os.Environment
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.test.runTest
import me.hectorhalpizar.kavop.ApplicationContextHolder
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertEquals
import kotlin.test.assertIs

@RunWith(AndroidJUnit4::class)
@Ignore("Refactoring in Progress")
class DisplayAudioPathsUseCaseInstrumentedTestCase {

    @Before
    fun setup() {
        ApplicationContextHolder.context =
            InstrumentationRegistry.getInstrumentation().targetContext.applicationContext
    }

    @Test
    fun getAudioPaths_successfully() = runTest {
        // Given
        val musicFolderPath =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).absolutePath
        val storePath = StoreAudioPathsUseCase()
        // storePath(musicFolderPath)

        val testInstance = DisplayAudioPathsUseCase()

        // When
        val result = testInstance()

        // Then
        assertIs<DisplayAudioPathsResult.Success>(result)
        assertEquals(1, result.audioPaths.size)
    }
}