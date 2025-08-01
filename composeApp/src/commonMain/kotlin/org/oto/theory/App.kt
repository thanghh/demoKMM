package org.oto.theory

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.oto.theory.common.Constants
import org.oto.theory.common.Screens
import org.oto.theory.common.extentions.composable
import org.oto.theory.theme.AppTheme
import org.oto.theory.ui.module.home.Home
import org.oto.theory.ui.module.learn.LearnTheory


@Composable
@Preview
fun App() {
    AppTheme {
        val navController = rememberNavController()
        val currentRoute by navController.currentBackStackEntryAsState()

        Crossfade(
            targetState = true,
            animationSpec = tween(500),
            label = "Splash"
        ) { targetState ->
            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .imePadding(),
            ) {
                if (targetState) {
                    MainContent(
                        startDestination = Constants.START_DESTINATION.route,
                        modifier = Modifier.fillMaxSize(),
                        navController = navController,
                    )
                } else {
                }
            }
        }
    }
}

@Composable
fun MainContent(
    startDestination: String,
    modifier: Modifier,
    navController: NavHostController,
) {
//    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(300)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(300)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(300)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(300)
            )
        }
    ) {
        composable(
            navController = navController, route = Screens.Home.route,
        ) { entry ->
            Home(
                navController = navController,
                navBackStackEntry = entry,
                modifier = Modifier.fillMaxSize()
            )
        }
        composable(
            navController = navController, route = Screens.LearnTheory.route,
        ) { entry ->
            LearnTheory(
                navController = navController,
                navBackStackEntry = entry,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}