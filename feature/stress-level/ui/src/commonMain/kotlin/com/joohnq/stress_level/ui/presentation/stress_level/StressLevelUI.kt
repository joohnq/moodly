package com.joohnq.stress_level.ui.presentation.stress_level

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.joohnq.domain.entity.UiState
import com.joohnq.domain.mapper.foldComposable
import com.joohnq.shared_resources.components.DecoratedConvexPanelList
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.stress_level.ui.component.StressContent
import com.joohnq.stress_level.ui.component.StressPanel
import com.joohnq.stress_level.ui.mapper.getTodayStressLevelRecord
import com.joohnq.stress_level.ui.presentation.stress_level.event.StressLevelEvent
import com.joohnq.stress_level.ui.resource.StressLevelRecordResource
import com.joohnq.stress_level.ui.resource.StressLevelResource
import com.joohnq.stress_level.ui.resource.StressorResource
import com.joohnq.stress_level.ui.viewmodel.StressLevelState
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun StressLevelUI(
    state: StressLevelState,
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
    StressLevelUI(
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
    StressLevelUI(
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
    StressLevelUI(
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
    StressLevelUI(
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
    StressLevelUI(
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
    StressLevelUI(
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