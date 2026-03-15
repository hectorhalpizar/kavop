package me.hectorhalpizar.kavop.domain.usecase

import android.os.Environment
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.GrantPermissionRule
import kotlinx.coroutines.test.runTest
import me.hectorhalpizar.kavop.ApplicationContextHolder
import me.hectorhalpizar.kavop.data.datasource.getMediaFetcher
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@Ignore("This is replaced with LoadAudioUseCaseEspressoTest")
class LoadAudioUseCaseInstrumentedTest {

    @get:Rule
    val permissionRule: GrantPermissionRule =
        GrantPermissionRule.grant(android.Manifest.permission.READ_EXTERNAL_STORAGE)

    private val mediaFetcher = getMediaFetcher()
    private val testInstance = LoadAudioUseCase(mediaFetcher)

    @Test
    fun load_audio_with_a_mocked_is_file_directory_false() = runTest {
        // Given
        ApplicationContextHolder.context = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext
        val fakeFilePath = "/fake/file/path"

        //// When
        // val result = testInstance.invoke(fakeFilePath)

        //// Then
        // assertIs<LoadAudioResult.FilePathMustBeFolder>(result)
    }


    @Test
    fun load_audio_with_a_mocked_is_file_directory_true() = runTest {
        // Given
        ApplicationContextHolder.context = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext
        val musicFolderPath =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).absolutePath

        //// When
        // val result = testInstance.invoke(musicFolderPath)

        //// Then
        // assertIs<LoadAudioResult.Success>(result)
        // assertEquals(15, result.audioLoaded?.size)
    }
}