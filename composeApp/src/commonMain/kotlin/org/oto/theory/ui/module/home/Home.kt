package org.oto.theory.ui.module.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import org.koin.compose.koinInject
import org.oto.theory.common.Screens
import org.oto.theory.theme.AppColor
import org.oto.theory.theme.LocalCustomColors
import org.oto.theory.theme.appTypography

@Composable
fun Home(
    modifier: Modifier,
    navController: NavController,
    navBackStackEntry: NavBackStackEntry,
    viewModel: HomeViewModel = koinInject()
) {
    val state by viewModel.stateHome.collectAsState()
    val colors = LocalCustomColors.current

    Box(
        modifier = modifier.fillMaxSize()
            .background(colors.background)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
//                    .background(AppColor.Primary)
                    .fillMaxWidth()
                    .height(130.dp)
                    .drawBehind {
                        val curveHeight = 30.dp.value * density
                        val path = Path()
                        path.lineTo(size.width, 0f)
                        path.lineTo(size.width, size.height - curveHeight)
                        path.arcTo(
                            rect = Rect(
                                offset = Offset(0f, size.height - curveHeight),
                                size = Size(size.width, curveHeight)
                            ),
                            startAngleDegrees = 0f,
                            sweepAngleDegrees = 180f,
                            forceMoveTo = false
                        )
                        path.lineTo(0f, 0f)
                        drawPath(
                            path = path,
                            color = AppColor.Primary
                        )
                    }
                    .statusBarsPadding(),
            ) {
                Text(
                    text = "Theory",
                    style = appTypography().headlineMedium,
                    color = Color.White,
                    modifier = Modifier.padding(vertical = 10.dp)
                        .align(Alignment.TopCenter)
                )
            }


            ElevatedButton(
                modifier = Modifier.padding(24.dp).fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                elevation = ButtonDefaults.elevatedButtonElevation(
                    pressedElevation = 30.dp,
                    defaultElevation = 10.dp
                ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colors.button
                ),
                onClick = {
                    navController.navigate(Screens.LearnTheory.route)
                }
            ) {
                Text(
                    text = "LEARN",
                    style = appTypography().titleLarge,
                    color = Color.White,
                    modifier = Modifier.padding(vertical = 10.dp)
                )
            }



            LazyColumn {
                items(state.listQuestion) { item ->
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
                                color = colors.textPrimary,
                                modifier = Modifier.padding(vertical = 3.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

