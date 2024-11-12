package com.joohnq.moodapp.view.screens.stresslevel

import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.tween
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import androidx.navigation.NavController
import com.joohnq.moodapp.entities.StressLevel
import com.joohnq.moodapp.entities.StressLevelRecord
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.view.components.SharedItem
import com.joohnq.moodapp.view.components.TextStyles
import com.joohnq.moodapp.view.constants.Colors
import com.joohnq.moodapp.view.constants.Drawables
import com.joohnq.moodapp.view.state.UiState.Companion.getValue
import com.joohnq.moodapp.viewmodel.StressLevelViewModel
import ir.ehsannarmani.compose_charts.LineChart
import ir.ehsannarmani.compose_charts.models.AnimationMode
import ir.ehsannarmani.compose_charts.models.DividerProperties
import ir.ehsannarmani.compose_charts.models.DrawStyle
import ir.ehsannarmani.compose_charts.models.GridProperties
import ir.ehsannarmani.compose_charts.models.HorizontalIndicatorProperties
import ir.ehsannarmani.compose_charts.models.LabelHelperProperties
import ir.ehsannarmani.compose_charts.models.LabelProperties
import ir.ehsannarmani.compose_charts.models.Line
import ir.ehsannarmani.compose_charts.models.PopupProperties
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.stress_analysis
import moodapp.composeapp.generated.resources.stress_level
import org.jetbrains.compose.resources.stringResource

@Composable
fun StressLevelScreenUI(
    snackBarHostState: SnackbarHostState = remember { SnackbarHostState() },
    stressLevelRecords: List<StressLevelRecord>,
    onAdd: () -> Unit = {},
    onGoBack: () -> Unit = {},
) {
    val first = stressLevelRecords.first().stressLevel
    val list = remember {
        listOf(
            0.0,
            0.0,
            0.0,
            50.0,
            50.0,
            50.0,
            25.0,
            25.0,
            25.0
        )
    }
    SharedItem(
        isDark = false,
        onGoBack = onGoBack,
        backgroundColor = first.palette.pageBackgroundColor,
        backgroundImage = Drawables.Images.StressLevelBackground,
        panelTitle = Res.string.stress_level,
        bodyTitle = Res.string.stress_analysis,
        color = first.palette.pageColor,
        onAdd = onAdd,
        panelContent = {
            Column(
                modifier = Modifier.padding(horizontal = 20.dp)
                    .padding(top = it.calculateTopPadding()).fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = first.level.toString(),
                    style = TextStyles.DisplayMdExtraBold(),
                    color = Colors.White
                )
                Text(
                    text = stringResource(first.text),
                    style = TextStyles.TextXlSemiBold(),
                    color = Colors.White
                )
            }
        },
        content = {
            item {
                LazyRow {
                    item {
                        LineChart(
                            indicatorProperties = HorizontalIndicatorProperties(
                                false,
                                padding = 0.dp
                            ),
                            dividerProperties = DividerProperties(false),
                            labelHelperProperties = LabelHelperProperties(false),
                            maxValue = 100.00,
                            minValue = 0.0,
//                            dotsProperties = DotProperties(
//                                true,
//                                radius = 6.dp,
//                                color = SolidColor(Colors.Brown80)
//                            ),
                            popupProperties = PopupProperties(false),
                            gridProperties = GridProperties(
                                false,
                                xAxisProperties = GridProperties.AxisProperties(false),
                                yAxisProperties = GridProperties.AxisProperties(false)
                            ),
                            labelProperties = LabelProperties(false),
                            modifier = Modifier.fillMaxWidth().height(130.dp)
                                .width(12 * 10.dp),
                            data = remember {
                                listOf(
                                    Line(
                                        label = "",
                                        curvedEdges = false,
                                        values = listOf(
                                            0.0,
                                            0.0,
                                            0.0,
                                            50.0,
                                            50.0,
                                            50.0,
                                            25.0,
                                            25.0,
                                            25.0
                                        ),
                                        color = SolidColor(Colors.Brown80),
                                        strokeAnimationSpec = tween(2000, easing = EaseInOutCubic),
                                        gradientAnimationDelay = 1000,
                                        drawStyle = DrawStyle.Stroke(width = 3.dp),
                                    )
                                )
                            },
                            animationDelay = 0L,
                            animationMode = AnimationMode.Together(delayBuilder = {
                                it * 500L
                            }
                            ),
                        )
                    }
                }
            }
        }
    )
}

@Composable
fun StressLevelScreen(
    navigation: NavController,
    stressLevelViewModel: StressLevelViewModel = sharedViewModel(),
) {
    val snackBarHostState = remember { SnackbarHostState() }
    val stressLevelItems by stressLevelViewModel.items.collectAsState()

    StressLevelScreenUI(
        snackBarHostState = snackBarHostState,
        stressLevelRecords = stressLevelItems.getValue(),
        onGoBack = navigation::popBackStack
    )
}

@Preview
@Composable
fun StressRateScreenPreview() {
    StressLevelScreenUI(
        stressLevelRecords = listOf(
            StressLevelRecord.init().copy(
                stressLevel = StressLevel.One
            )
        )
    )
}

@Preview
@Composable
fun StressRateScreenPreview2() {
    StressLevelScreenUI(
        stressLevelRecords = listOf(
            StressLevelRecord.init().copy(
                stressLevel = StressLevel.Two
            )
        )
    )
}

@Preview
@Composable
fun StressRateScreenPreview3() {
    StressLevelScreenUI(
        stressLevelRecords = listOf(
            StressLevelRecord.init().copy(
                stressLevel = StressLevel.Three
            )
        )
    )
}

@Preview
@Composable
fun StressRateScreenPreview4() {
    StressLevelScreenUI(
        stressLevelRecords = listOf(
            StressLevelRecord.init().copy(
                stressLevel = StressLevel.Four
            )
        )
    )
}

@Preview
@Composable
fun StressRateScreenPreview5() {
    StressLevelScreenUI(
        stressLevelRecords = listOf(
            StressLevelRecord.init().copy(
                stressLevel = StressLevel.Five
            )
        )
    )
}

