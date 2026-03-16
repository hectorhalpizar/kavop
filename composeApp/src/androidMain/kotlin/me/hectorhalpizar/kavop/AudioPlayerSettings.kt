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
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AudioPlayerSettings(modifier: Modifier, navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(title = { AudioPlayerSettingsTopBar(modifier, navController) })
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row {
                Button(
                    onClick = { },
                    colors = KavopStyle.AudioPlayer.buttonColorTransparent
                ) {
                    Text(
                        text = "Set Audio Path Folders",
                        fontSize = TextUnit(24f, TextUnitType.Sp)
                    )
                }
            }
        }
    }
}

@Composable
private fun AudioPlayerSettingsTopBar(modifier: Modifier, navController: NavController) {
    Row (verticalAlignment = Alignment.CenterVertically) {
        Column {
            Button(
                onClick = {
                    navController.navigateUp()
                },
                colors = KavopStyle.AudioPlayer.buttonColorTransparent
            ) {
                Text("<-")
            }
        }
        Column(
            modifier = modifier.weight(1f)
        ) {
            Text(
                text = "Settings",
                fontSize = TextUnit(24f, TextUnitType.Sp)
            )
        }
        Spacer(modifier = modifier)
    }
}

@Composable
@Preview
fun AudioPlayerSettingsPreview() {
    AudioPlayerSettings(modifier = Modifier, navController = rememberNavController())
}
