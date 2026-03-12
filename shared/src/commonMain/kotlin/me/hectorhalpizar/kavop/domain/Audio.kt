package me.hectorhalpizar.kavop.domain

data class Audio(
    override val id: Long,
    val title: String?,
    val artist: String?,
    val album: String?,
    val duration: Long?,
    override val uri: String? = null,
    override val filePath: String? = null,
) : Media
