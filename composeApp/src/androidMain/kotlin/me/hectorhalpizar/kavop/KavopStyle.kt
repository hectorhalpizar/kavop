package me.hectorhalpizar.kavop

import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object KavopStyle {

    object AudioPlayer {

        val buttonColorTransparent : ButtonColors
            @Composable
            get() {
                return ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.DarkGray,
                    disabledContainerColor = Color.Transparent,
                    disabledContentColor = Color.LightGray
                )
            }
    }
}

