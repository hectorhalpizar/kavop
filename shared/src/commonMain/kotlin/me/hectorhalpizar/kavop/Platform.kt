package me.hectorhalpizar.kavop

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform