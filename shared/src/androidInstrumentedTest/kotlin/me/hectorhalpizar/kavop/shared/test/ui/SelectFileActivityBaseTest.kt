package me.hectorhalpizar.kavop.shared.test.ui


import android.view.View
import android.view.ViewGroup
import androidx.documentfile.provider.DocumentFile
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import me.hectorhalpizar.kavop.shared.test.R
import me.hectorhalpizar.kavop.util.IdlingUtil
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule

abstract class SelectFileActivityBaseTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(SelectFileActivity::class.java)

    internal var idlingUtil: IdlingUtil? = null
    internal var folderPickerCallback : (DocumentFile?) -> Unit = {}
    internal var filePickerCallback : (DocumentFile?) -> Unit = {}

    fun selectMp3File() {

        mActivityScenarioRule.scenario.onActivity { activity ->
            activity.filePickerCallback = filePickerCallback
        }

        val materialButton = onView(
            allOf(
                withId(R.id.select_file),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        materialButton.perform(click())

        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        var element = device.findObject(
            UiSelector().text("sdk_gphone64_arm64")
        )
        element.click()

        element = device.findObject(
            UiSelector()
                .index(1)
                .resourceId("android:id/title")
                .text("Music")
        )
        element.click()

        element = device.findObject(
            UiSelector()
                .index(0)
                .resourceId("android:id/title")
                .text("On The Flip - The Grey Room _ Density & Time.mp3")
        )
        element.click()
    }

    fun selectMusicFolder() {

        mActivityScenarioRule.scenario.onActivity { activity ->
            activity.folderPickerCallback = folderPickerCallback
            activity.idlingUtil = idlingUtil
        }

        val materialButton = onView(
            allOf(
                withId(R.id.select_folder),
                withText("Select folder"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    3
                ),
                isDisplayed()
            )
        )
        materialButton.perform(click())

        val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

        var element = device.findObject(
            UiSelector().text("sdk_gphone64_arm64")
        )
        element.click()

        element = device.findObject(
            UiSelector()
                .index(1)
                .resourceId("android:id/title")
                .text("Music")
        )
        element.click()

        element = device.findObject(
            UiSelector()
                .index(0)
                .resourceId("android:id/button1")
                .text("USE THIS FOLDER")
        )
        element.click()

        element = device.findObject(
            UiSelector()
                .index(1)
                .resourceId("android:id/button1")
                .text("ALLOW")
        )
        element.click()
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
