package com.joohnq.stress_level.overview.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.layout.ConvexGroupLazyLayout
import com.joohnq.shared_resources.components.spacer.VerticalSpacer
import com.joohnq.shared_resources.stress_level
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
    val palette = state.todayStressLevel?.stressLevel?.palette

    ConvexGroupLazyLayout(
        containerColor = Colors.White,
        title = Res.string.stress_level,
        isDark = state.todayStressLevel == null,
        image = Drawables.Images.StressLevelBackground,
        color = palette?.imageColor ?: Colors.Brown5,
        panelBackgroundColor = palette?.color ?: Colors.Brown5,
        panel = { modifier ->
            VerticalSpacer(10.dp)
            StressLevelOverviewPanel(
                item = state.todayStressLevel
            )
        },
        onAddButton = { onEvent(StressLevelOverviewContract.Event.NavigateToAddStressLevel) },
        onGoBack = { onEvent(StressLevelOverviewContract.Event.GoBack) },
        body = { modifier ->
            StressLevelOverviewTriggers(
                modifier = modifier,
                stressors = state.stressorsTriggers,
                onAddStressLevel = { onEvent(StressLevelOverviewContract.Event.NavigateToAddStressLevel) }
            )
            StressLevelOverviewInsight(
                modifier = modifier,
                stressors = state.stressorsInsight,
                onCreate = { onEvent(StressLevelOverviewContract.Event.NavigateToAddStressLevel) }
            )
            StressLevelHistory(
                modifier = modifier,
                items = state.historyItems,
                onDelete = { id -> onIntent(StressLevelOverviewContract.Intent.Delete(id)) },
                onAddStressLevel = { onEvent(StressLevelOverviewContract.Event.NavigateToAddStressLevel) },
                onSeeMore = { onEvent(StressLevelOverviewContract.Event.NavigateToStressLevelHistory) }
            )
        }
    )
}
