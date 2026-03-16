package me.hectorhalpizar.kavop

import android.app.Application

class KavopApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        ApplicationContextHolder.context = applicationContext
    }
}