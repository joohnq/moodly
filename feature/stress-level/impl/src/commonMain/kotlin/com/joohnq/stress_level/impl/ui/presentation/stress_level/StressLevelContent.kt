package com.joohnq.stress_level.impl.ui.presentation.stress_level

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.components.layout.ConvexGroupLazyLayout
import com.joohnq.shared_resources.components.spacer.VerticalSpacer
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.stress_level.impl.ui.component.StressHistory
import com.joohnq.stress_level.impl.ui.component.StressInsight
import com.joohnq.stress_level.impl.ui.component.StressPanel
import com.joohnq.stress_level.impl.ui.component.StressTriggersSection
import com.joohnq.stress_level.impl.ui.mapper.StressLevelRecordResourceMapper.getTodayStressLevelRecord
import com.joohnq.ui.mapper.UiStateMapper.foldComposable

@Composable
fun StressLevelContent(
    state: StressLevelContract.State,
    onIntent: (StressLevelContract.Intent) -> Unit = {},
    onEvent: (StressLevelContract.Event) -> Unit = {},
) {
    state.records.foldComposable(
        onSuccess = { records ->
            val record = records.getTodayStressLevelRecord()
            val hasToday = record != null

            ConvexGroupLazyLayout(
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
                onAddButton = { onEvent(StressLevelContract.Event.OnAddStressLevel) },
                onGoBack = { onEvent(StressLevelContract.Event.OnGoBack) },
                body = { modifier ->
                    StressTriggersSection(
                        modifier = modifier,
                        records = records,
                        onAddStressLevel = { onEvent(StressLevelContract.Event.OnAddStressLevel) }
                    )
                    StressInsight(
                        modifier = modifier,
                        records = records,
                        onCreate = { onEvent(StressLevelContract.Event.OnAddStressLevel) }
                    )
                    StressHistory(
                        modifier = modifier,
                        records = records.take(7),
                        onDelete = { id -> onIntent(StressLevelContract.Intent.Delete(id)) },
                        onAddStressLevel = { onEvent(StressLevelContract.Event.OnAddStressLevel) }
                    )
                }
            )
        }
    )
}
