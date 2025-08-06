package com.joohnq.sleep_quality.overview.presentation

import androidx.compose.runtime.Composable
import com.joohnq.shared_resources.components.layout.ConvexGroupLazyLayout
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.sleep_quality.overview.component.SleepQualityOverviewBody
import com.joohnq.sleep_quality.overview.component.SleepQualityOverviewPanel

@Composable
fun SleepQualityOverviewContent(
    state: SleepQualityOverviewContract.State,
    onEvent: (SleepQualityOverviewContract.Event) -> Unit = {},
    onIntent: (SleepQualityOverviewContract.Intent) -> Unit = {},
) {
    when {
        state.isLoading -> Unit
        state.isError != null -> Unit
        else ->
            SuccessView(
                state = state,
                onEvent = onEvent,
                onIntent = onIntent
            )
    }
}

@Composable
private fun SuccessView(
    state: SleepQualityOverviewContract.State,
    onEvent: (SleepQualityOverviewContract.Event) -> Unit,
    onIntent: (SleepQualityOverviewContract.Intent) -> Unit,
) {
    val hasToday = state.todaySleepQuality != null

    ConvexGroupLazyLayout(
        containerColor = Colors.White,
        isDark = !hasToday,
        image = Drawables.Images.SleepQualityBackground,
        color =
            state.todaySleepQuality
                ?.sleepQuality
                ?.palette
                ?.imageColor ?: Colors.Brown10,
        panelBackgroundColor =
            state.todaySleepQuality
                ?.sleepQuality
                ?.palette
                ?.color ?: Colors.Brown10,
        panel = { modifier ->
            SleepQualityOverviewPanel(
                modifier = modifier,
                item = state.todaySleepQuality
            )
        },
        onAddButton = { onEvent(SleepQualityOverviewContract.Event.NavigateToAddSleepQuality) },
        onGoBack = { onEvent(SleepQualityOverviewContract.Event.GoBack) },
        body = { modifier ->
            SleepQualityOverviewBody(
                modifier = modifier,
                items = state.items,
                onEvent = onEvent,
                onIntent = onIntent
            )
        }
    )
}