package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.toArgb

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
