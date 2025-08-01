package org.oto.theory.common

import androidx.navigation.NavDeepLink
import androidx.navigation.navDeepLink

sealed class Screens(
    val name: String,
    val route: String,
) {
    data object Home : Screens(
        "Home",
        "home",
    )
    data object LearnTheory : Screens(
        "LearnTheory",
        "LearnTheory",
    )
}