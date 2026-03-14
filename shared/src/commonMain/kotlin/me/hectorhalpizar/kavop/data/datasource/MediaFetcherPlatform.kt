package me.hectorhalpizar.kavop.data.datasource

internal expect fun getMediaFetcher() : MediaFetcher

internal val audioFileExtensionFilter = listOf(
    "mp3",
    "aac",
    "m4a",
    "flac",
    "ogg",
    "wav",
    "opus",
    "amr",
    "3gp",
)

internal val audioMimeTypes = arrayOf(
    "audio/mpeg",
    "audio/aac",
    "audio/mp4",
    "audio/flac",
    "audio/ogg",
    "audio/wav",
    "audio/opus",
    "audio/amr",
    "audio/3gpp"
)
