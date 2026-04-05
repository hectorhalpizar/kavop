package me.hectorhalpizar.kavop.shared.test.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

class UiAutomatorService : Service() {

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Log.w(TAG, "Action ${intent?.action} sent")

        when(intent?.action) {
            ACTION_SCREEN_DUMP -> {
                val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
                val context = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext
                val file = File(context.getExternalFilesDir(null), "screen_dump.xml")

                Log.d(TAG, "===> Dump File: ${file.absolutePath}")

                device.dumpWindowHierarchy(file)

                val r = FileReader(file)
                val br = BufferedReader(r)
                br.readLines().forEach { line ->
                    Log.d(TAG, line)
                }
            }
            else -> {
                Log.w(TAG, "No Action ${intent?.action}")
            }
        }

        return super.onStartCommand(intent, flags, startId)
    }

    companion object {
        private const val TAG = "UiAutomatorService"
        private const val ACTION_SCREEN_DUMP = "me.hectorhalpizar.kavop.shared.test.action.SCREEN_DUMP"
    }
}
