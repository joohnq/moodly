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
import com.joohnq.core.ui.presentation.loading.LoadingUI
import com.joohnq.shared.ui.Res
import com.joohnq.shared.ui.components.SharedPanelComponent
import com.joohnq.shared.ui.components.StressLevelCard
import com.joohnq.shared.ui.life_impact
import com.joohnq.shared.ui.state.UiState.Companion.foldComposable
import com.joohnq.shared.ui.stress_analysis
import com.joohnq.shared.ui.stress_level
import com.joohnq.shared.ui.stressor
import com.joohnq.shared.ui.theme.Colors
import com.joohnq.shared.ui.theme.Dimens
import com.joohnq.shared.ui.theme.Drawables
import com.joohnq.shared.ui.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared.ui.theme.TextStyles
import com.joohnq.stress_level.ui.StressLevelResource.Companion.toResource
import com.joohnq.stress_level.ui.StressorResource
import com.joohnq.stress_level.ui.StressorResource.Companion.toResource
import com.joohnq.stress_level.ui.components.StressLevelChart
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
            val record = stressLevelRecords.last()
            val resource = record.stressLevel.toResource()

            SharedPanelComponent(
                containerColor = Colors.White,
                isDark = false,
                onGoBack = { state.onEvent(StressLevelEvent.GoBack) },
                backgroundColor = resource.palette.color,
                backgroundImage = Drawables.Images.StressLevelBackground,
                panelTitle = Res.string.stress_level,
                bodyTitle = Res.string.stress_analysis,
                color = resource.palette.backgroundColor,
                onAdd = { state.onEvent(StressLevelEvent.Add) },
                panelContent = {
                    Column(
                        modifier = Modifier.paddingHorizontalMedium()
                            .padding(top = it.calculateTopPadding()).fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = resource.level.toString(),
                            style = TextStyles.DisplayMdExtraBold(),
                            color = Colors.White
                        )
                        Text(
                            text = stringResource(resource.text),
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
                                value = StressorResource.getText(record.stressors.map { it.toResource() }),
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
                                value = stringResource(resource.lifeImpact),
                            ) {
                                StressLevelChart(stressLevelRecords = stressLevelRecords)
                            }
                        }
                    }
                }
            )
        }
    )
}