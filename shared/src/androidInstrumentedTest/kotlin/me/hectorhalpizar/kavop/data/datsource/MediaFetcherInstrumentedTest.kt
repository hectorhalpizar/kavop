package me.hectorhalpizar.kavop.data.datsource

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import me.hectorhalpizar.kavop.ApplicationContextHolder
import me.hectorhalpizar.kavop.data.datasource.getMediaFetcher
import org.junit.Assert.assertNotEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MediaFetcherInstrumentedTest {

    @Test
    fun fetchAudio_with_media_on_device() {
        // Given
        ApplicationContextHolder.context = InstrumentationRegistry.getInstrumentation().context.applicationContext
        val testInstance = getMediaFetcher()

        // When
        val result = testInstance.fetchAudio()

        // Then
        assertNotEquals(0, result.size)
    }

    companion object {
        private const val TAG = "MediaFetcherInstrumentedTest"
    }
}