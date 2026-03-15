package me.hectorhalpizar.kavop.util

fun Throwable.getThrowableMessage(): String {
    return buildString {
        var current: Throwable? = this@getThrowableMessage

        while (current != null) {
            appendLine("${current::class.simpleName}: ${current.message}")

            val stack = current.stackTraceToString()
            if (stack.isNotBlank()) {
                appendLine(stack)
            }

            current = current.cause
            if (current != null) {
                appendLine("=== CAUSED BY ===")
            }
        }
    }
}
