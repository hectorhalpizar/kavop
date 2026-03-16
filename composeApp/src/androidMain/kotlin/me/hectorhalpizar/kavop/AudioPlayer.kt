package me.hectorhalpizar.kavop

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import me.hectorhalpizar.kavop.domain.Audio

@Composable
fun AudioPlayer(modifier: Modifier = Modifier, audioSet: Set<Audio> = emptySet()) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "audioPlayer") {
        composable("audioPlayer") { AudioPlayer(modifier, navController, audioSet) }
        composable("audioPlayerSettings") { AudioPlayerSettings(modifier, navController) }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AudioPlayer(
    modifier: Modifier = Modifier,
    navController: NavController,
    audioSet : Set<Audio>
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { AudioTopBar(modifier, navController) })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (audioSet.isEmpty()) {
                Column(
                    modifier = modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Button(onClick = {}) {
                        Text("Reload Media")
                    }
                }
            } else {
                AudioPlayerTrackList(audioSet.toSet(), modifier)
            }
        }
    }
}

@Composable
private fun AudioTopBar(modifier: Modifier = Modifier, navController: NavController) {
    Row {
        Column(modifier = modifier.weight(1f)) {
            Text(text = "Kavop")
        }
        Spacer(modifier = modifier)
        Column {
            Button(
                onClick = {
                    navController.navigate("audioPlayerSettings")
                },
                colors = KavopStyle.AudioPlayer.buttonColorTransparent
            ) {
                Text("...")
            }
        }
    }
}

@Composable
@Preview(showBackground = true, widthDp = 240)
fun AudioPlayerEmptyPreview() {
    AudioPlayer()
}

@Composable
@Preview(showBackground = true, widthDp = 240)
fun AudioPlayerNotEmptyPreview() {

    val audioSet = mutableSetOf<Audio>()
    repeat(200) { index ->
        audioSet.addAll(
            setOf(
                Audio(index.toLong(), "Pawn", "The Grey Room", null, 185496),
                Audio(index.toLong(), "The Reins", "Blue Deer", null, 197616),
                Audio(index.toLong(), null, "Blue Deer", null, null),
                Audio(index.toLong(), "Fake Long Track", "Fake Artist", null, Long.MAX_VALUE),
                Audio(
                    index.toLong(),
                    "Super Fake Long Track Name ",
                    "Fake Artist",
                    null,
                    Long.MIN_VALUE
                ),
                Audio(index.toLong(), "Futile", "The Grey Room", null, 192444),
            )
        )
    }

    AudioPlayer(audioSet = audioSet)
}
