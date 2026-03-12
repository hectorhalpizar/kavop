package me.hectorhalpizar.kavop

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import junit.framework.TestCase.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ApplicationContextHolderInstrumentationTest {

    @Test
    fun context_is_not_null() {

        // Given and when
        ApplicationContextHolder.context =  InstrumentationRegistry
                                                .getInstrumentation()
                                                .context
                                                .applicationContext

        // Then
        assertNotNull(ApplicationContextHolder.context)
    }
}