package org.oto.theory.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kotlinproject.composeapp.generated.resources.Lora_Medium
import kotlinproject.composeapp.generated.resources.Lora_Regular
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.lora_bold
import org.jetbrains.compose.resources.Font


@Composable
fun appTypography() = Typography().let {
        it.copy(
            displayLarge = it.displayLarge.copy(fontFamily = FontFamily(Font(Res.font.lora_bold))),
            displayMedium = it.displayMedium.copy(fontFamily = FontFamily(Font(Res.font.Lora_Medium))),
            displaySmall = it.displaySmall.copy(fontFamily = FontFamily(Font(Res.font.Lora_Regular))),
            headlineLarge = it.headlineLarge.copy(fontFamily = FontFamily(Font(Res.font.lora_bold))),
            headlineMedium = it.headlineMedium.copy(fontFamily = FontFamily(Font(Res.font.Lora_Medium))),
            headlineSmall = it.headlineSmall.copy(fontFamily = FontFamily(Font(Res.font.Lora_Regular))),
            titleLarge = it.titleLarge.copy(fontFamily = FontFamily(Font(Res.font.lora_bold))),
            titleMedium = it.titleMedium.copy(fontFamily = FontFamily(Font(Res.font.Lora_Medium))),
            titleSmall = it.titleSmall.copy(fontFamily = FontFamily(Font(Res.font.Lora_Regular))),
            bodyLarge = it.bodyLarge.copy(fontFamily = FontFamily(Font(Res.font.lora_bold))),
            bodyMedium = it.bodyMedium.copy(fontFamily = FontFamily(Font(Res.font.Lora_Regular))),
            bodySmall = it.bodySmall.copy(fontFamily = FontFamily(Font(Res.font.Lora_Regular))),
            labelLarge = it.labelLarge.copy(fontFamily = FontFamily(Font(Res.font.lora_bold))),
            labelMedium = it.labelMedium.copy(fontFamily = FontFamily(Font(Res.font.Lora_Regular))),
            labelSmall = it.labelSmall.copy(fontFamily = FontFamily(Font(Res.font.Lora_Regular)))
        )
    }


