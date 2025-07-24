package com.joohnq.sleep_quality.impl.ui.presentation.sleep_quality

import androidx.compose.runtime.Composable
import com.joohnq.shared_resources.components.ConvexGroupLazyLayout
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.sleep_quality.impl.ui.component.SleepContent
import com.joohnq.sleep_quality.impl.ui.component.SleepPanel
import com.joohnq.sleep_quality.impl.ui.mapper.getTodaySleepQualityRecord
import com.joohnq.sleep_quality.impl.ui.presentation.sleep_quality.event.SleepQualityEvent
import com.joohnq.sleep_quality.impl.ui.viewmodel.SleepQualityIntent
import com.joohnq.sleep_quality.impl.ui.viewmodel.SleepQualityState
import com.joohnq.ui.mapper.foldComposable

@Composable
fun SleepQualityContent(
    state: SleepQualityState,
    onEvent: (SleepQualityEvent) -> Unit = {},
    onAction: (SleepQualityIntent) -> Unit = {},
) {
    state.records.foldComposable(
        onSuccess = { records ->
            val record = records.getTodaySleepQualityRecord()
            val hasToday = record != null

            ConvexGroupLazyLayout(
                containerColor = Colors.White,
                isDark = !hasToday,
                image = Drawables.Images.SleepQualityBackground,
                color = record?.sleepQuality?.palette?.imageColor ?: Colors.Brown10,
                panelBackgroundColor = record?.sleepQuality?.palette?.color ?: Colors.Brown10,
                panel = { modifier ->
                    SleepPanel(
                        modifier = modifier,
                        record = record,
                    )
                },
                onAddButton = { onEvent(SleepQualityEvent.OnNavigateToAddSleepQuality) },
                onGoBack = { onEvent(SleepQualityEvent.OnGoBack) },
                content = { modifier ->
                    SleepContent(
                        modifier = modifier,
                        records = records,
                        onEvent = onEvent,
                        onAction = onAction
                    )
                }
            )
        }
    )
}