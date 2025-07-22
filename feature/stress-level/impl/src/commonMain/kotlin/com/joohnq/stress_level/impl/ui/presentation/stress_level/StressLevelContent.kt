package com.joohnq.stress_level.impl.ui.presentation.stress_level

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.joohnq.ui.entity.UiState
import com.joohnq.ui.mapper.foldComposable
import com.joohnq.shared_resources.components.DecoratedConvexPanelList
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.stress_level.impl.ui.component.StressContent
import com.joohnq.stress_level.impl.ui.component.StressPanel
import com.joohnq.stress_level.impl.ui.mapper.getTodayStressLevelRecord
import com.joohnq.stress_level.impl.ui.presentation.stress_level.event.StressLevelEvent
import com.joohnq.stress_level.impl.ui.resource.StressLevelRecordResource
import com.joohnq.stress_level.impl.ui.resource.StressLevelResource
import com.joohnq.stress_level.impl.ui.resource.StressorResource
import com.joohnq.stress_level.impl.ui.viewmodel.StressLevelIntent
import com.joohnq.stress_level.impl.ui.viewmodel.StressLevelState
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun StressLevelContent(
    state: StressLevelState,
    onAction: (StressLevelIntent) -> Unit = {},
    onEvent: (StressLevelEvent) -> Unit = {},
) {
    state.records.foldComposable(
        onSuccess = { records ->
            val record = records.getTodayStressLevelRecord()
            val hasToday = record != null

            DecoratedConvexPanelList(
                containerColor = Colors.White,
                isDark = !hasToday,
                image = Drawables.Images.StressLevelBackground,
                color = if (hasToday) record.stressLevel.palette.imageColor else Colors.Brown10,
                panelBackgroundColor = if (hasToday) record.stressLevel.palette.color else Colors.Brown10,
                panel = { modifier ->
                    VerticalSpacer(10.dp)
                    StressPanel(
                        record = record
                    )
                },
                onAddButton = { onEvent(StressLevelEvent.onAddStressLevel) },
                onGoBack = { onEvent(StressLevelEvent.OnGoBack) },
                content = { modifier ->
                    StressContent(
                        modifier = modifier,
                        records = records,
                        onAction = onAction,
                        onEvent = onEvent
                    )
                }
            )
        }
    )
}

@Preview
@Composable
fun StressLevelUIPreviewEmpty() {
    StressLevelContent(
        state = StressLevelState(
            records = UiState.Success(
                listOf(
                )
            )
        )
    )
}

@Preview
@Composable
fun StressLevelUIPreviewOne() {
    StressLevelContent(
        state = StressLevelState(
            records = UiState.Success(
                listOf(
                    StressLevelRecordResource(
                        stressLevel = StressLevelResource.One,
                        stressors = listOf(StressorResource.Work)
                    ),
                    StressLevelRecordResource(
                        stressors = listOf(
                            StressorResource.Work,
                            StressorResource.Kids,
                            StressorResource.Relationship
                        )
                    ),
                    StressLevelRecordResource(
                        stressors = listOf(StressorResource.Finances, StressorResource.Loneliness)
                    )
                )
            )
        )
    )
}

@Preview
@Composable
fun StressLevelUIPreviewTwo() {
    StressLevelContent(
        state = StressLevelState(
            records = UiState.Success(
                listOf(
                    StressLevelRecordResource(
                        stressLevel = StressLevelResource.Two,
                        stressors = listOf(StressorResource.Work)
                    ),
                    StressLevelRecordResource(
                        stressors = listOf(StressorResource.Work, StressorResource.Kids)
                    ),
                    StressLevelRecordResource(
                        stressors = listOf(StressorResource.Finances, StressorResource.Loneliness)
                    )
                )
            )
        )
    )
}

@Preview
@Composable
fun StressLevelUIPreviewThree() {
    StressLevelContent(
        state = StressLevelState(
            records = UiState.Success(
                listOf(
                    StressLevelRecordResource(
                        stressLevel = StressLevelResource.Three,
                        stressors = listOf(StressorResource.Work)
                    ),
                    StressLevelRecordResource(
                        stressors = listOf(StressorResource.Work, StressorResource.Kids)
                    ),
                    StressLevelRecordResource(
                        stressors = listOf(StressorResource.Finances, StressorResource.Loneliness)
                    )
                )
            )
        )
    )
}

@Preview
@Composable
fun StressLevelUIPreviewFour() {
    StressLevelContent(
        state = StressLevelState(
            records = UiState.Success(
                listOf(
                    StressLevelRecordResource(
                        stressLevel = StressLevelResource.Four,
                        stressors = listOf(StressorResource.Work)
                    ),
                    StressLevelRecordResource(
                        stressors = listOf(StressorResource.Work, StressorResource.Kids)
                    ),
                    StressLevelRecordResource(
                        stressors = listOf(StressorResource.Finances, StressorResource.Loneliness)
                    )
                )
            )
        )
    )
}

@Preview
@Composable
fun StressLevelUIPreviewFive() {
    StressLevelContent(
        state = StressLevelState(
            records = UiState.Success(
                listOf(
                    StressLevelRecordResource(
                        stressLevel = StressLevelResource.Five,
                        stressors = listOf(StressorResource.Work)
                    ),
                    StressLevelRecordResource(
                        stressors = listOf(StressorResource.Work, StressorResource.Kids)
                    ),
                    StressLevelRecordResource(
                        stressors = listOf(StressorResource.Finances, StressorResource.Loneliness)
                    )
                )
            )
        )
    )
}