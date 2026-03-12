package me.hectorhalpizar.kavop

import android.annotation.SuppressLint
import android.content.Context

@SuppressLint("StaticFieldLeak")
object ApplicationContextHolder {
    /**
     * Place only the application [Context].
     * Otherwise, there will be memory leaks.
     */
    lateinit var context: Context
}
