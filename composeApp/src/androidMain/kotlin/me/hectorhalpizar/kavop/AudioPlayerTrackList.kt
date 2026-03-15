package me.hectorhalpizar.kavop

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import me.hectorhalpizar.kavop.domain.Audio

private const val MAX_HOURS_TO_DISPLAY = 100

@Composable
fun AudioPlayerTrackList(audioList: Set<Audio>, modifier: Modifier) {
    Column {
        LazyColumn(
            modifier = modifier
        ) {
            items(audioList.toList()) { audio ->
                AudioInformation(audio)
            }
        }
    }
}

@Composable
private fun AudioInformation(audio: Audio, modifier: Modifier = Modifier) {

    Button(
        colors = KavopStyle.AudioPlayer.buttonColorTransparent,
        onClick = {}
    ) {
        Column(modifier = modifier.weight(1f)) {
            Text(audio.title ?: "<unknown>")
            Text(audio.artist ?: "<unknown>")
        }

        Spacer(modifier = modifier)

        Column {
            val duration = formatDuration(audio.duration ?: 0L)
            Text(duration)
        }
    }
}

private fun formatDuration(durationMs: Long): String {

    val durationMsEvaluated = if (durationMs < 0) 0 else durationMs
    val totalSeconds = durationMsEvaluated / 1000
    val hours = totalSeconds / 3600
    val minutes = (totalSeconds % 3600) / 60
    val seconds = totalSeconds % 60

    return if (hours >= MAX_HOURS_TO_DISPLAY)
        "+%02d:%02d:%02d".format(99, 59, 59)
    else if (hours > 0)
        "%02d:%02d:%02d".format(hours, minutes, seconds)
    else
        "%02d:%02d".format(minutes, seconds)
}

@Composable
@Preview(showBackground = true, widthDp = 240)
fun AudioPlayerTrackListPreview() {
    val audioList = mutableSetOf(
        Audio(0, "Pawn", "The Grey Room", null, 185496),
        Audio(0, "The Reins", "Blue Deer", null, 197616),
        Audio(0, null, "Blue Deer", null, null),
        Audio(0, "Fake Long Track", "Fake Artist", null, Long.MAX_VALUE),
        Audio(0, "Super Fake Long Track Name ", "Fake Artist", null, Long.MIN_VALUE),
        Audio(0, "Futile", "The Grey Room", null, 192444),
        // Repeated Audios
        Audio(0, "Futile", "The Grey Room", null, 192444),
        Audio(0, "Pawn", "The Grey Room", null, 185496),
    )
    AudioPlayerTrackList(audioList.toSet(), Modifier.fillMaxWidth())
}

