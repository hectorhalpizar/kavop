import me.hectorhalpizar.kavop.util.MediaPath

val mockedMediaPathDirectoryIsFalse: MediaPath
    get() = object : MediaPath {
        override val isDirectory: Boolean
            get() = false
    }


val mockedMediaPathDirectoryIsTrue: MediaPath
    get() = object : MediaPath {
        override val isDirectory: Boolean
            get() = true
    }
