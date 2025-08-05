package com.joohnq.sleep_quality.overview.presentation

import androidx.compose.runtime.Composable
import com.joohnq.shared_resources.components.layout.ConvexGroupLazyLayout
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.sleep_quality.impl.ui.mapper.SleepQualityResourceMapper.getTodaySleepQualityRecord
import com.joohnq.sleep_quality.overview.component.SleepQualityOverviewBody
import com.joohnq.sleep_quality.overview.component.SleepQualityOverviewPanel
import com.joohnq.ui.mapper.UiStateMapper.foldComposable

@Composable
fun SleepQualityOverviewContent(
    state: SleepQualityOverviewContract.State,
    onEvent: (SleepQualityOverviewContract.Event) -> Unit = {},
    onIntent: (SleepQualityOverviewContract.Intent) -> Unit = {},
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
                    SleepQualityOverviewPanel(
                        modifier = modifier,
                        record = record
                    )
                },
                onAddButton = { onEvent(SleepQualityOverviewContract.Event.NavigateToAddSleepQuality) },
                onGoBack = { onEvent(SleepQualityOverviewContract.Event.GoBack) },
                body = { modifier ->
                    SleepQualityOverviewBody(
                        modifier = modifier,
                        records = records,
                        onEvent = onEvent,
                        onIntent = onIntent
                    )
                }
            )
        }
    )
}