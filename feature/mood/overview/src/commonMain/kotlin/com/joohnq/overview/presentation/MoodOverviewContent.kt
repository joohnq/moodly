package com.joohnq.overview.presentation

import androidx.compose.runtime.Composable
import com.joohnq.overview.component.MoodOverviewContentBody
import com.joohnq.overview.component.MoodOverviewContentPanel
import com.joohnq.shared_resources.components.layout.ConvexGroupLazyLayout
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables

@Composable
fun MoodOverviewContent(
    state: MoodOverviewContract.State,
    onIntent: (MoodOverviewContract.Intent) -> Unit = {},
    onEvent: (MoodOverviewContract.Event) -> Unit = {},
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
    state: MoodOverviewContract.State,
    onEvent: (MoodOverviewContract.Event) -> Unit,
    onIntent: (MoodOverviewContract.Intent) -> Unit,
) {
    val hasToday = state.todayMood != null

    ConvexGroupLazyLayout(
        containerColor = Colors.White,
        panelBackgroundColor = if (hasToday) state.todayMood.mood.palette.color else Colors.Brown10,
        isDark = !hasToday,
        image = Drawables.Images.MoodBackground,
        color = if (hasToday) state.todayMood.mood.palette.imageColor else Colors.Brown10,
        onAddButton = { onEvent(MoodOverviewContract.Event.NavigateToAddMood) },
        onGoBack = { onEvent(MoodOverviewContract.Event.GoBack) },
        panel = { modifier ->
            MoodOverviewContentPanel(
                modifier = modifier,
                item = state.todayMood
            )
        },
        body = { modifier ->
            MoodOverviewContentBody(
                modifier = modifier,
                item = state.todayMood,
                items = state.items,
                onEvent = onEvent,
                onIntent = onIntent
            )
        }
    )
}