package me.hectorhalpizar.kavop.domain.usecase

import android.content.Context
import android.util.Log
import androidx.documentfile.provider.DocumentFile
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.test.runTest
import me.hectorhalpizar.kavop.ApplicationContextHolder
import me.hectorhalpizar.kavop.data.repository.AudioKeyValue.AUDIO_PATHS_KEY
import me.hectorhalpizar.kavop.data.repository.AudioKeyValue.KAVOP_AUDIO_PLAYER_PREFERENCES
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
class StoreAudioPathsUseCaseEspressoTest : SelectFileActivityBaseTest() {

    @Before
    fun setup() {
        ApplicationContextHolder.context = InstrumentationRegistry
            .getInstrumentation()
            .targetContext
            .applicationContext
    }

    @Test
    fun store_audio_path_with_folder() {
        selectMusicFolder()

        mActivityScenarioRule.scenario.onActivity { activity ->
            assertNotNull(activity.folderSelected)

            val audioPathToStore = activity.folderSelected as DocumentFile
            val mediaPath = createMediaPath() as AndroidMediaPath
            mediaPath.path = audioPathToStore

            val storeAudioUseCase = StoreAudioPathsUseCase()
            runTest {
                val result = storeAudioUseCase(mediaPath)
                assertIs<StoreAudioPathsResult.Success>(result)
                assertEquals(1, storedPaths?.size)
            }
        }
    }

    private val storedPaths : Set<String?>?
        get() {
            val context = ApplicationContextHolder.context
            val prefs = context.getSharedPreferences(KAVOP_AUDIO_PLAYER_PREFERENCES,
                Context.MODE_PRIVATE)
            return prefs.getStringSet(AUDIO_PATHS_KEY, emptySet())
        }

}