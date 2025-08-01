package org.oto.theory.ui.module.learn

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import org.koin.compose.koinInject
import org.oto.theory.theme.AppColor
import org.oto.theory.theme.LocalCustomColors
import org.oto.theory.theme.appTypography
import org.oto.theory.ui.module.home.HomeViewModel

@Composable
fun LearnTheory(
    modifier: Modifier,
    navController: NavController,
    navBackStackEntry: NavBackStackEntry,
    viewModel: LearnTheoryViewModel = koinInject()
) {
    val state by viewModel.stateLearn.collectAsState()
    val colors = LocalCustomColors.current

    Box(
        modifier = modifier.fillMaxSize()
            .background(colors.background)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            LazyColumn {
                items(state.chapter1) { item ->
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = item.text,
                            style = appTypography().bodyLarge,
                            color = colors.textTitle,
                            modifier = Modifier.padding(vertical = 5.dp)
                        )

                        item.answers.forEach { answer ->
                            Text(
                                text = answer.text,
                                style = appTypography().bodyMedium,
                                color = if(answer.isCorrect) AppColor.BlueDarkCerulean else colors.textPrimary,
                                modifier = Modifier.padding(vertical = 3.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}