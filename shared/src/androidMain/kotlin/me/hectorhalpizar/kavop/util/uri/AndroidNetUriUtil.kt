@file:JvmName("AndroidNetUriUtil")
package me.hectorhalpizar.kavop.util.uri

import android.net.Uri

internal val Uri.pathSafe : String
    get() {
        return if (this.path.isNullOrEmpty()) ""
        else this.path as String
    }
