package com.joohnq.stress_level.ui.presentation.stress_level

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.mood.components.SharedPanelComponent
import com.joohnq.mood.components.StressLevelCard
import com.joohnq.mood.components.StressLevelChart
import com.joohnq.mood.state.UiState.Companion.foldComposable
import com.joohnq.mood.theme.Colors
import com.joohnq.mood.theme.Dimens
import com.joohnq.mood.theme.Drawables
import com.joohnq.mood.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.mood.theme.TextStyles
import com.joohnq.mood.ui.presentation.loading.LoadingUI
import com.joohnq.shared.ui.Res
import com.joohnq.shared.ui.life_impact
import com.joohnq.shared.ui.stress_analysis
import com.joohnq.shared.ui.stress_level
import com.joohnq.shared.ui.stressor
import com.joohnq.stress_level.domain.entity.Stressor
import com.joohnq.stress_level.ui.presentation.stress_level.event.StressLevelEvent
import com.joohnq.stress_level.ui.presentation.stress_level.state.StressLevelState
import org.jetbrains.compose.resources.stringResource

@Composable
fun StressLevelUI(
    state: StressLevelState,
) {
    state.stressLevelRecords.foldComposable(
        onLoading = { LoadingUI() },
        onSuccess = { stressLevelRecords ->
            val stressLevelRecord = stressLevelRecords.last()
            SharedPanelComponent(
                containerColor = Colors.White,
                isDark = false,
                onGoBack = { state.onEvent(StressLevelEvent.OnGoBack) },
                backgroundColor = stressLevelRecord.stressLevel.palette.color,
                backgroundImage = Drawables.Images.StressLevelBackground,
                panelTitle = Res.string.stress_level,
                bodyTitle = Res.string.stress_analysis,
                color = stressLevelRecord.stressLevel.palette.backgroundColor,
                onAdd = { state.onEvent(StressLevelEvent.OnAdd) },
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
                                value = Stressor.getText(stressLevelRecord.stressors),
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
                                StressLevelChart(
                                    stressLevelRecords = stressLevelRecords.takeLast(8)
                                )
                            }
                        }
                    }
                }
            )
        }
    )
}