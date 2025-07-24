package com.joohnq.stress_level.impl.ui.presentation.stress_level

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.joohnq.ui.mapper.foldComposable
import com.joohnq.shared_resources.components.ConvexGroupLazyLayout
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.stress_level.impl.ui.component.StressContent
import com.joohnq.stress_level.impl.ui.component.StressPanel
import com.joohnq.stress_level.impl.ui.mapper.getTodayStressLevelRecord
import com.joohnq.stress_level.impl.ui.presentation.stress_level.event.StressLevelEvent
import com.joohnq.stress_level.impl.ui.viewmodel.StressLevelIntent
import com.joohnq.stress_level.impl.ui.viewmodel.StressLevelState

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