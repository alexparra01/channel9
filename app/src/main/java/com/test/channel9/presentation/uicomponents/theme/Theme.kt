package com.test.channel9.presentation.uicomponents.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController


private val LightColorScheme = lightColorScheme(
    primary = DarkBlue,
    secondary = MediumBlue,
    tertiary = LightBlue,
    onSurface = WhiteGray
    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun Channel9Theme(
    content: @Composable () -> Unit
) {
    val colorScheme = LightColorScheme
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = !isSystemInDarkTheme()

    DisposableEffect(systemUiController, useDarkIcons) {
        systemUiController.setSystemBarsColor(
            color = DarkBlue,
            darkIcons = useDarkIcons
        )
        onDispose {}
    }
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )

}