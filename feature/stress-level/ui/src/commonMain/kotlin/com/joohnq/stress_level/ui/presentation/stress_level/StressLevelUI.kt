package com.joohnq.stress_level.ui.presentation.stress_level

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.joohnq.core.ui.entity.UiState
import com.joohnq.core.ui.mapper.foldComposable
import com.joohnq.shared_resources.components.DecoratedConvexPanelList
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.stress_level.domain.entity.StressLevel
import com.joohnq.stress_level.domain.entity.StressLevelRecord
import com.joohnq.stress_level.domain.entity.Stressor
import com.joohnq.stress_level.domain.mapper.getTodayStressLevelRecord
import com.joohnq.stress_level.ui.components.StressItems
import com.joohnq.stress_level.ui.components.StressPanel
import com.joohnq.stress_level.ui.components.StressTriggersSection
import com.joohnq.stress_level.ui.mapper.toResource
import com.joohnq.stress_level.ui.presentation.stress_level.event.StressLevelEvent
import com.joohnq.stress_level.ui.viewmodel.StressLevelState
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun StressLevelUI(
    state: StressLevelState,
    onEvent: (StressLevelEvent) -> Unit = {},
) {
    state.stressLevelRecords.foldComposable(
        onSuccess = { records ->
            val record = records.getTodayStressLevelRecord()
            val resource = record?.stressLevel?.toResource()
            val hasToday = resource != null

            DecoratedConvexPanelList(
                containerColor = Colors.White,
                isDark = !hasToday,
                image = Drawables.Images.StressLevelBackground,
                color = if (hasToday) resource.palette.imageColor else Colors.Brown10,
                panelBackgroundColor = if (hasToday) resource.palette.color else Colors.Brown10,
                panel = { modifier ->
                    VerticalSpacer(10.dp)
                    StressPanel(
                        resource = resource
                    )
                },
                onAddButton = { onEvent(StressLevelEvent.onAddStressLevel) },
                onGoBack = { onEvent(StressLevelEvent.OnGoBack) },
                content = { modifier ->
                    StressTriggersSection(
                        modifier = modifier,
                        records = records,
                        onAddStressLevel = { onEvent(StressLevelEvent.onAddStressLevel) },
                    )
                    StressItems(
                        modifier = modifier,
                        records = records,
                        onAddStressLevel = { onEvent(StressLevelEvent.onAddStressLevel) },
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
            stressLevelRecords = UiState.Success(
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
            stressLevelRecords = UiState.Success(
                listOf(
                    StressLevelRecord(
                        stressLevel = StressLevel.One,
                        stressors = listOf(Stressor.Work)
                    ),
                    StressLevelRecord(
                        stressors = listOf(Stressor.Work, Stressor.Kids)
                    ),
                    StressLevelRecord(
                        stressors = listOf(Stressor.Finances, Stressor.Loneliness)
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
            stressLevelRecords = UiState.Success(
                listOf(
                    StressLevelRecord(
                        stressLevel = StressLevel.Two,
                        stressors = listOf(Stressor.Work)
                    ),
                    StressLevelRecord(
                        stressors = listOf(Stressor.Work, Stressor.Kids)
                    ),
                    StressLevelRecord(
                        stressors = listOf(Stressor.Finances, Stressor.Loneliness)
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
            stressLevelRecords = UiState.Success(
                listOf(
                    StressLevelRecord(
                        stressLevel = StressLevel.Three,
                        stressors = listOf(Stressor.Work)
                    ),
                    StressLevelRecord(
                        stressors = listOf(Stressor.Work, Stressor.Kids)
                    ),
                    StressLevelRecord(
                        stressors = listOf(Stressor.Finances, Stressor.Loneliness)
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
            stressLevelRecords = UiState.Success(
                listOf(
                    StressLevelRecord(
                        stressLevel = StressLevel.Four,
                        stressors = listOf(Stressor.Work)
                    ),
                    StressLevelRecord(
                        stressors = listOf(Stressor.Work, Stressor.Kids)
                    ),
                    StressLevelRecord(
                        stressors = listOf(Stressor.Finances, Stressor.Loneliness)
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
            stressLevelRecords = UiState.Success(
                listOf(
                    StressLevelRecord(
                        stressLevel = StressLevel.Five,
                        stressors = listOf(Stressor.Work)
                    ),
                    StressLevelRecord(
                        stressors = listOf(Stressor.Work, Stressor.Kids)
                    ),
                    StressLevelRecord(
                        stressors = listOf(Stressor.Finances, Stressor.Loneliness)
                    )
                )
            )
        )
    )
}