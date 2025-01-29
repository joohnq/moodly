package com.joohnq.stress_level.ui.presentation.stress_level

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.core.ui.mapper.toFormattedDateString
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.SharedPanelComponent
import com.joohnq.shared_resources.components.StressLevelCard
import com.joohnq.shared_resources.components.TextWithBackground
import com.joohnq.shared_resources.stress_level
import com.joohnq.shared_resources.stressor
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.stress_level.domain.entity.StressLevelRecord
import com.joohnq.stress_level.ui.mapper.getText
import com.joohnq.stress_level.ui.mapper.toResource
import com.joohnq.stress_level.ui.presentation.stress_level.event.StressLevelEvent
import com.joohnq.stress_level.ui.presentation.stress_level.event.toStressLevelEvent
import org.jetbrains.compose.resources.stringResource

@Composable
fun StressLevelUI(
    record: StressLevelRecord,
    onEvent: (StressLevelEvent) -> Unit = {},
) {
    val resource = record.stressLevel.toResource()

    SharedPanelComponent(
        containerColor = Colors.White,
        isDark = false,
        paddingValues = Dimens.Padding.HorizontalMedium,
        backgroundColor = resource.palette.color,
        image = Drawables.Images.StressLevelBackground,
        title = Res.string.stress_level,
        color = resource.palette.imageColor,
        onEvent = { event -> onEvent(event.toStressLevelEvent()) },
        topBar = {
            TextWithBackground(
                text = record.createdAt.date.toFormattedDateString(),
                textColor = resource.palette.color,
                backgroundColor = resource.palette.backgroundColor,
            )
        },
        panel = {
            Column(
                modifier = Modifier.paddingHorizontalMedium().fillMaxSize(),
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
//                    StressLevelCard(
//                        modifier = Modifier.weight(1f).fillMaxHeight(),
//                        icon = Drawables.Icons.Flag,
//                        title = Res.string.life_impact,
//                        value = stringResource(resource.lifeImpact),
//                    ) {
//                        StressLevelChart(stressLevelRecords = stressLevelRecords)
//                    }
            }
        }
    )
}