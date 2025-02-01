package com.joohnq.stress_level.ui.presentation.stress_level

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.joohnq.core.ui.entity.UiState
import com.joohnq.core.ui.mapper.foldComposable
import com.joohnq.shared_resources.components.DecoratedConvexPanelList
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.theme.Colors
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
                    StressItems(
                        modifier = modifier,
                        records = records,
                        onAddStressLevel = { onEvent(StressLevelEvent.onAddStressLevel) },
                    )
                    StressTriggersSection(
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
fun StressLevelUIPreview() {
    StressLevelUI(
        state = StressLevelState(
            stressLevelRecords = UiState.Success(
                listOf(
                    StressLevelRecord(
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