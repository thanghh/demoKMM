package org.oto.theory.theme

import androidx.compose.ui.graphics.Color

object AppColor {
    val Primary = Color(0xFF1976D2)
    val MediumBlue = Color(0xFFEAF1FB)

    val MediumGray = Color(0xFF8A8A8D)
    val DarkGray = Color(0xFF54565B)
    val BgBlack = Color(0xFF36383E)
    val BgGray = Color(0xFFF3F3F3)
    val SmallGray = Color(0xFFC0C0C0)
    val BlueDarkCerulean = Color(0xFF1F8BE0)

}


data class Colors (
    val background : Color,
    val textPrimary : Color,
    val textTitle : Color,
)