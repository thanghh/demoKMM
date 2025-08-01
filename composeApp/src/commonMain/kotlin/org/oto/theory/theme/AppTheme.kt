package org.oto.theory.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import org.oto.theory.theme.AppColor.BlueDarkCerulean

//val LocalCustomColors = staticCompositionLocalOf { LightColorPalette }
val LocalCustomColors = compositionLocalOf { CustomColors() }

@Immutable
data class CustomColors(
    val isDarkTheme: Boolean = true,
    val background: Color = AppColor.BgBlack,
    val textTitle: Color = Color.White,
    val textPrimary: Color = Color.White,
    val button: Color = BlueDarkCerulean,

    val border: Brush = Brush.linearGradient(
        listOf(
            Color.White.copy(0.4f),
            Color.White.copy(0.05f),
            Color.White.copy(0.05f),
            Color.White.copy(0.4f)
        )
    ),
) {
    companion object {
        val darkColors = CustomColors()
        val lightColors = CustomColors(
            isDarkTheme = false,
            background = AppColor.MediumBlue,
            textTitle = AppColor.BgBlack,
            textPrimary = AppColor.DarkGray,
            button = BlueDarkCerulean,
            border = Brush.linearGradient(
                listOf(
                    Color.White.copy(0.4f),
                    Color.White.copy(0.05f),
                    Color.White.copy(0.05f),
                    Color.White.copy(0.4f)
                )
            )
        )
    }
}

@Composable
fun AppTheme(content: @Composable () -> Unit) {
//    val colorScheme = if (darkTheme) DarkCO else LightColorPalette
    val colors = if (isSystemInDarkTheme()) CustomColors.darkColors else CustomColors.lightColors

    CompositionLocalProvider(LocalCustomColors provides colors) {
        MaterialTheme(
            typography = appTypography(), shapes = appShapes(), content = content
        )
    }
}

//val LightColorPalette = darkColorScheme(
//    background = Color(0xFFFFFFFF),
//    textTitle = AppColor.BgBlack,
//    textPrimary = AppColor.DarkGray,
//)
//
//val DarkColorPalette = Colors(
//    background = AppColor.BgBlack,
//    textTitle = Color.White,
//    textPrimary = Color.White,
//)
//
//@Composable
//fun appColorScheme() = if (isSystemInDarkTheme()) DarkColorPalette else LightColorPalette