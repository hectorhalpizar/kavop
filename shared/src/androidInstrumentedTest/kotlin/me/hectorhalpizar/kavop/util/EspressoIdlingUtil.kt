package me.hectorhalpizar.kavop.util

import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.idling.CountingIdlingResource

object EspressoIdlingUtil : IdlingUtil {

    private val resource = CountingIdlingResource("EspressoIdlingUtil")

    override fun increment() {
        resource.increment()
    }

    override fun decrement() {
        if (resource.isIdleNow) {
            resource.decrement()
        }
    }

    override fun register() {
        IdlingRegistry.getInstance().register(resource)
    }

    override fun unregister() {
        IdlingRegistry.getInstance().unregister(resource)
    }
}