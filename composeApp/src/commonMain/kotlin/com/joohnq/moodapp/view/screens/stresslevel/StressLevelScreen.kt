package com.joohnq.moodapp.view.screens.stresslevel

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.joohnq.moodapp.entities.StressLevel
import com.joohnq.moodapp.entities.StressLevelRecord
import com.joohnq.moodapp.entities.Stressors
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.view.components.SharedPanelComponent
import com.joohnq.moodapp.view.components.StressLevelCard
import com.joohnq.moodapp.view.components.StressLevelChart
import com.joohnq.moodapp.view.routes.onNavigateToAddStressLevel
import com.joohnq.moodapp.view.state.UiState.Companion.getValue
import com.joohnq.moodapp.view.ui.Colors
import com.joohnq.moodapp.view.ui.Dimens
import com.joohnq.moodapp.view.ui.Drawables
import com.joohnq.moodapp.view.ui.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.moodapp.view.ui.TextStyles
import com.joohnq.moodapp.viewmodel.StressLevelViewModel
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.life_impact
import moodapp.composeapp.generated.resources.stress_analysis
import moodapp.composeapp.generated.resources.stress_level
import moodapp.composeapp.generated.resources.stressor
import org.jetbrains.compose.resources.stringResource

@Composable
fun StressLevelScreenUI(
    stressLevelRecords: List<StressLevelRecord>,
    onAction: (StressLevelAction) -> Unit = {}
) {
    val stressLevelRecord = stressLevelRecords.last()
    SharedPanelComponent(
        containerColor = Colors.White,
        isDark = false,
        onGoBack = { onAction(StressLevelAction.OnGoBack) },
        backgroundColor = stressLevelRecord.stressLevel.palette.color,
        backgroundImage = Drawables.Images.StressLevelBackground,
        panelTitle = Res.string.stress_level,
        bodyTitle = Res.string.stress_analysis,
        color = stressLevelRecord.stressLevel.palette.backgroundColor,
        onAdd = { onAction(StressLevelAction.OnAdd) },
        panelContent = {
            Column(
                modifier = Modifier.paddingHorizontalMedium()
                    .padding(top = it.calculateTopPadding()).fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stressLevelRecord.stressLevel.level.toString(),
                    style = TextStyles.DisplayMdExtraBold(),
                    color = Colors.White
                )
                Text(
                    text = stringResource(stressLevelRecord.stressLevel.text),
                    style = TextStyles.TextXlSemiBold(),
                    color = Colors.White
                )
            }
        },
        content = {
            item {
                Row(
                    modifier = Modifier.paddingHorizontalMedium(),
                    horizontalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    StressLevelCard(
                        modifier = Modifier.weight(1f).fillMaxHeight(),
                        icon = Drawables.Icons.WarningOutlined,
                        title = Res.string.stressor,
                        value = Stressors.getText(stressLevelRecord.stressors),
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth().paddingHorizontalMedium(),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                Box(
                                    modifier = Modifier.width(24.dp).height(20.dp)
                                        .background(
                                            color = Colors.Green50,
                                            shape = Dimens.Shape.Circle
                                        )
                                )
                                Box(
                                    modifier = Modifier.weight(1f).height(20.dp)
                                        .background(
                                            color = Colors.Green30,
                                            shape = Dimens.Shape.Circle
                                        )
                                )
                            }
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                Box(
                                    modifier = Modifier.weight(0.4f).height(20.dp)
                                        .background(
                                            color = Colors.Green30,
                                            shape = Dimens.Shape.Circle
                                        )
                                )
                                Box(
                                    modifier = Modifier.weight(0.6f).height(20.dp)
                                        .background(
                                            color = Colors.Green50,
                                            shape = Dimens.Shape.Circle
                                        )
                                )
                            }
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                Box(
                                    modifier = Modifier.weight(0.6f).height(20.dp)
                                        .background(
                                            color = Colors.Green50,
                                            shape = Dimens.Shape.Circle
                                        )
                                )
                                Box(
                                    modifier = Modifier.weight(0.4f).height(20.dp)
                                        .background(
                                            color = Colors.Green30,
                                            shape = Dimens.Shape.Circle
                                        )
                                )
                            }
                        }
                    }
                    StressLevelCard(
                        modifier = Modifier.weight(1f).fillMaxHeight(),
                        icon = Drawables.Icons.Flag,
                        title = Res.string.life_impact,
                        value = stringResource(stressLevelRecord.stressLevel.lifeImpact),
                    ) {
                        StressLevelChart(stressLevelRecords = stressLevelRecords.takeLast(8))
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
    val stressLevelState by stressLevelViewModel.stressLevelState.collectAsState()

    StressLevelScreenUI(
        stressLevelRecords = stressLevelState.stressLevelRecords.getValue(),
        onAction = { action ->
            when (action) {
                is StressLevelAction.OnAdd -> navigation.onNavigateToAddStressLevel()
                is StressLevelAction.OnGoBack -> navigation.popBackStack()
            }
        }
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

