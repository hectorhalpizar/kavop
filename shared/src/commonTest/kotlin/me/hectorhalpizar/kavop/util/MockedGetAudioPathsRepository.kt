package me.hectorhalpizar.kavop.util

import me.hectorhalpizar.kavop.data.repository.GetAudioPathsRepository

internal val mockedGetAudioPathsRepositoryAsSuccess = object : GetAudioPathsRepository {
    override fun getAudioPaths(): Set<String> {
        return setOf(
            "/fake/path/for/music/1",
            "/fake/path/for/music/2",
            "/fake/path/for/music/3",
        )
    }
}

internal val mockedGetAudioPathsRepositoryWithException = object : GetAudioPathsRepository {
    override fun getAudioPaths(): Set<String> {
        throw Exception("Mocked Exception")
    }
}

