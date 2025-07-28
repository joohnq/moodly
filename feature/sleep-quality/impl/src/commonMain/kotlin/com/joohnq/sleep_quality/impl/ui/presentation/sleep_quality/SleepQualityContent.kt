package com.joohnq.sleep_quality.impl.ui.presentation.sleep_quality

import androidx.compose.runtime.Composable
import com.joohnq.shared_resources.components.layout.ConvexGroupLazyLayout
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.sleep_quality.impl.ui.component.SleepContent
import com.joohnq.sleep_quality.impl.ui.component.SleepPanel
import com.joohnq.sleep_quality.impl.ui.mapper.SleepQualityResourceMapper.getTodaySleepQualityRecord
import com.joohnq.ui.mapper.UiStateMapper.foldComposable

@Composable
fun SleepQualityContent(
    state: SleepQualityContract.State,
    onEvent: (SleepQualityContract.Event) -> Unit = {},
    onAction: (SleepQualityContract.Intent) -> Unit = {},
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
                        record = record
                    )
                },
                onAddButton = { onEvent(SleepQualityContract.Event.OnNavigateToAddSleepQuality) },
                onGoBack = { onEvent(SleepQualityContract.Event.OnGoBack) },
                body = { modifier ->
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
