package me.hectorhalpizar.kavop.data.datsource

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.GrantPermissionRule
import kotlinx.coroutines.test.runTest
import me.hectorhalpizar.kavop.ApplicationContextHolder
import me.hectorhalpizar.kavop.data.datasource.getMediaFetcher
import me.hectorhalpizar.kavop.shared.test.ui.SelectFileActivityBaseTest
import me.hectorhalpizar.kavop.util.AndroidMediaPath
import me.hectorhalpizar.kavop.util.EspressoIdlingUtil
import me.hectorhalpizar.kavop.util.createMediaPath
import org.junit.After
import org.junit.Before
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertEquals

@RunWith(AndroidJUnit4::class)
@Ignore("MediaFetcher verifications are on LoadAudioUseCaseInstrumentedTest.")
class MediaFetcherInstrumentedTest: SelectFileActivityBaseTest() {

    @get:Rule
    val permissionRule: GrantPermissionRule =
        GrantPermissionRule.grant(android.Manifest.permission.READ_EXTERNAL_STORAGE)

    @Before
    fun setup() {
        idlingUtil = EspressoIdlingUtil
        idlingUtil?.register()
    }

    @After
    fun tearDown() {
        idlingUtil?.unregister()
    }

    @Test
    fun load_audio_on_sd_card_music_path() {
        // Given
        ApplicationContextHolder.context =
            InstrumentationRegistry.getInstrumentation().context.applicationContext

        folderPickerCallback = { dir ->
            // Given
            val testInstance = getMediaFetcher()
            val mediaPath = createMediaPath() as AndroidMediaPath
            dir?.let { dir ->
                mediaPath.path = dir
            }

            runTest {
                // When
                val result = testInstance.loadAudio(mediaPath)

                // Then
                assertEquals(15, result.size, "===> Amount of songs ${result.size}")
            }
        }
    }

    companion object {
        private const val TAG = "MediaFetcherInstrumentedTest"
    }
}