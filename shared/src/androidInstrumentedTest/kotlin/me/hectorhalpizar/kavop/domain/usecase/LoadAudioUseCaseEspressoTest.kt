package me.hectorhalpizar.kavop.domain.usecase

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.test.runTest
import me.hectorhalpizar.kavop.ApplicationContextHolder
import me.hectorhalpizar.kavop.data.datasource.getMediaFetcher
import me.hectorhalpizar.kavop.shared.test.ui.SelectFileActivityBaseTest
import me.hectorhalpizar.kavop.util.AndroidMediaPath
import me.hectorhalpizar.kavop.util.createMediaPath
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertNotNull

@RunWith(AndroidJUnit4::class)
class LoadAudioUseCaseEspressoTest : SelectFileActivityBaseTest() {

    @Before
    fun setup() {
        ApplicationContextHolder.context = InstrumentationRegistry
                                            .getInstrumentation()
                                            .targetContext
                                            .applicationContext
    }

    @Test
    fun load_audio_with_music_folder() {

        selectMusicFolder()

        mActivityScenarioRule.scenario.onActivity { activity ->
            assertNotNull(activity.folderSelected)

            runTest {
                // When
                val mediaPath = createMediaPath() as AndroidMediaPath
                activity.folderSelected?.let { folderSelected ->
                    mediaPath.path = folderSelected
                }

                val mediaFetcher = getMediaFetcher()
                val testInstance = LoadAudioUseCase(mediaFetcher)

                val result = testInstance.invoke(mediaPath)

                // Then
                assertIs<LoadAudioResult.Success>(result)
                assertEquals(
                    15,
                    result.audioLoaded?.size,
                    "===> Amount of songs ${result.audioLoaded?.size}"
                )
            }
        }
    }

    @Test
    fun load_audio_with_music_file() {

        selectMp3File()

        mActivityScenarioRule.scenario.onActivity { activity ->
            assertNotNull(activity.fileSelected)

            runTest {
                // When
                val mediaPath = createMediaPath() as AndroidMediaPath
                activity.fileSelected?.let { fileSelected ->
                    mediaPath.path = fileSelected
                }

                val mediaFetcher = getMediaFetcher()
                val testInstance = LoadAudioUseCase(mediaFetcher)

                val result = testInstance.invoke(mediaPath)

                // Then
                assertIs<LoadAudioResult.FilePathMustBeFolder>(result)
            }
        }
    }
}