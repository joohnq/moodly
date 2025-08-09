package com.joohnq.overview.presentation

import androidx.compose.runtime.Composable
import com.joohnq.overview.component.MoodOverviewContentBody
import com.joohnq.overview.component.MoodOverviewContentPanel
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.layout.ConvexGroupLazyLayout
import com.joohnq.shared_resources.mood
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
    ConvexGroupLazyLayout(
        containerColor = Colors.White,
        panelBackgroundColor = state.todayMood?.mood?.palette?.color ?: Colors.Brown10,
        title = Res.string.mood,
        image = Drawables.Images.MoodBackground,
        color = state.todayMood?.mood?.palette?.imageColor ?: Colors.Brown10,
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
                state = state,
                onEvent = onEvent,
                onIntent = onIntent
            )
        }
    )
}