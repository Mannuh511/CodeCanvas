package com.example.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = NeonCyan,
    background = DeepBlue,
    surface = DarkSurface,
    surfaceVariant = DarkSurfaceVariant,
    onPrimary = DeepBlue,
    onBackground = SoftWhite,
    onSurface = SoftWhite,
    onSurfaceVariant = SoftGray,
    secondary = CodePurple,
    tertiary = CodeGreen,
    error = CodeRed
)

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = DarkColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = androidx.compose.material3.Typography(),
        content = content
    )
}
