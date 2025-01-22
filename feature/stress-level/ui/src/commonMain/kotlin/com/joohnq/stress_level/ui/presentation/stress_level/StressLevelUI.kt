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
import com.joohnq.core.ui.DatetimeProvider
import com.joohnq.core.ui.entity.UiState
import com.joohnq.core.ui.mapper.foldComposable
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.SharedPanelComponent
import com.joohnq.shared_resources.components.StressLevelCard
import com.joohnq.shared_resources.components.TextWithBackground
import com.joohnq.shared_resources.life_impact
import com.joohnq.shared_resources.stress_analysis
import com.joohnq.shared_resources.stress_level
import com.joohnq.shared_resources.stressor
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.splash.ui.presentation.splash_screen.SplashScreenUI
import com.joohnq.stress_level.domain.entity.StressLevelRecord
import com.joohnq.stress_level.ui.components.StressLevelChart
import com.joohnq.stress_level.ui.mapper.getText
import com.joohnq.stress_level.ui.mapper.toResource
import com.joohnq.stress_level.ui.presentation.stress_level.event.StressLevelEvent
import org.jetbrains.compose.resources.stringResource

@Composable
fun StressLevelUI(
    stressLevelRecords: UiState<List<StressLevelRecord>>,
    onEvent: (StressLevelEvent) -> Unit = {},
) {
    stressLevelRecords.foldComposable(
        onLoading = { SplashScreenUI() },
        onSuccess = { stressLevelRecords ->
            val record = stressLevelRecords.first()
            val resource = record.stressLevel.toResource()

            SharedPanelComponent(
                containerColor = Colors.White,
                isDark = false,
                onGoBack = { onEvent(StressLevelEvent.GoBack) },
                backgroundColor = resource.palette.color,
                backgroundImage = Drawables.Images.StressLevelBackground,
                panelTitle = Res.string.stress_level,
                bodyTitle = Res.string.stress_analysis,
                color = resource.palette.backgroundColor,
                onAdd = { onEvent(StressLevelEvent.Add) },
                topBarContent = {
                    TextWithBackground(
                        text = DatetimeProvider.formatDate(record.createdAt.date),
                        textColor = resource.palette.color,
                        backgroundColor = resource.palette.backgroundColor,
                    )
                },
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
                                value = record.stressors.toResource().getText(),
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