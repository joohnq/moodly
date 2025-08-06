package com.joohnq.stress_level.overview.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.components.layout.ConvexGroupLazyLayout
import com.joohnq.shared_resources.components.spacer.VerticalSpacer
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.stress_level.impl.ui.component.StressLevelHistory
import com.joohnq.stress_level.overview.component.StressLevelOverviewInsight
import com.joohnq.stress_level.overview.component.StressLevelOverviewPanel
import com.joohnq.stress_level.overview.component.StressLevelOverviewTriggers

@Composable
fun StressLevelOverviewContent(
    state: StressLevelOverviewContract.State,
    onIntent: (StressLevelOverviewContract.Intent) -> Unit = {},
    onEvent: (StressLevelOverviewContract.Event) -> Unit = {},
) {
    when {
        state.items.isNotEmpty() && !state.isLoading ->
            SuccessView(
                state = state,
                onEvent = onEvent,
                onIntent = onIntent
            )
    }
}

@Composable
private fun SuccessView(
    state: StressLevelOverviewContract.State,
    onEvent: (StressLevelOverviewContract.Event) -> Unit,
    onIntent: (StressLevelOverviewContract.Intent) -> Unit,
) {
    val hasToday = state.todayStressLevel != null

    ConvexGroupLazyLayout(
        containerColor = Colors.White,
        isDark = !hasToday,
        image = Drawables.Images.StressLevelBackground,
        color = if (hasToday) state.todayStressLevel.stressLevel.palette.imageColor else Colors.Brown10,
        panelBackgroundColor = if (hasToday) state.todayStressLevel.stressLevel.palette.color else Colors.Brown10,
        panel = { modifier ->
            VerticalSpacer(10.dp)
            StressLevelOverviewPanel(
                record = state.todayStressLevel
            )
        },
        onAddButton = { onEvent(StressLevelOverviewContract.Event.NavigateToAddStressLevel) },
        onGoBack = { onEvent(StressLevelOverviewContract.Event.GoBack) },
        body = { modifier ->
            StressLevelOverviewTriggers(
                modifier = modifier,
                records = state.items,
                onAddStressLevel = { onEvent(StressLevelOverviewContract.Event.NavigateToAddStressLevel) }
            )
            StressLevelOverviewInsight(
                modifier = modifier,
                records = state.items,
                onCreate = { onEvent(StressLevelOverviewContract.Event.NavigateToAddStressLevel) }
            )
            StressLevelHistory(
                modifier = modifier,
                records = state.items.take(7),
                onDelete = { id -> onIntent(StressLevelOverviewContract.Intent.Delete(id)) },
                onAddStressLevel = { onEvent(StressLevelOverviewContract.Event.NavigateToAddStressLevel) }
            )
        }
    )
}
