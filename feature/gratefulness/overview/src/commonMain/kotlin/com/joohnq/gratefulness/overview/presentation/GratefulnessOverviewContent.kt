package com.joohnq.gratefulness.overview.presentation

import androidx.compose.runtime.Composable
import com.joohnq.gratefulness.overview.presentation.component.GratefulnessOverviewBody
import com.joohnq.gratefulness.overview.presentation.component.GratefulnessOverviewPanel
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.layout.ConvexGroupLazyLayout
import com.joohnq.shared_resources.gratefulness
import com.joohnq.shared_resources.theme.Colors
import com.kizitonwose.calendar.compose.weekcalendar.WeekCalendarState
import com.kizitonwose.calendar.compose.weekcalendar.rememberWeekCalendarState

@Composable
fun GratefulnessOverviewContent(
    state: GratefulnessOverviewContract.State,
    weekCalendarState: WeekCalendarState = rememberWeekCalendarState(),
    onEvent: (GratefulnessOverviewContract.Event) -> Unit = {},
    onIntent: (GratefulnessOverviewContract.Intent) -> Unit = {},
) {
    when {
        state.isLoading -> Unit
        state.isError != null -> Unit
        else ->
            SuccessView(
                state = state,
                weekCalendarState = weekCalendarState,
                onEvent = onEvent,
                onIntent = onIntent
            )
    }
}

@Composable
private fun SuccessView(
    state: GratefulnessOverviewContract.State,
    weekCalendarState: WeekCalendarState,
    onEvent: (GratefulnessOverviewContract.Event) -> Unit,
    onIntent: (GratefulnessOverviewContract.Intent) -> Unit,
) {
    ConvexGroupLazyLayout(
        containerColor = Colors.White,
        panelBackgroundColor = Colors.Brown5,
        title = Res.string.gratefulness,
        isDark = true,
        onAddButton = { onEvent(GratefulnessOverviewContract.Event.NavigateToAddGratefulness) },
        onGoBack = { onEvent(GratefulnessOverviewContract.Event.GoBack) },
        panel = { modifier ->
            GratefulnessOverviewPanel(
                modifier = modifier,
                weekCalendarState = weekCalendarState,
                items = state.items,
                selectedDate = state.selectedDate,
                iAmGratefulFor = state.selectedGratefulness?.iAmGratefulFor,
                onSelectDate = { date ->
                    onIntent(
                        GratefulnessOverviewContract.Intent.ChangeSelectedDate(
                            date
                        )
                    )
                }
            )
        },
        body = { modifier ->
            GratefulnessOverviewBody(
                modifier = modifier,
                items = state.items,
                quote = state.quote,
                insight = state.insight,
                onDelete = { id -> onIntent(GratefulnessOverviewContract.Intent.Delete(id)) },
                onCreate = { onEvent(GratefulnessOverviewContract.Event.NavigateToAddGratefulness) },
                onSeeMore = { onEvent(GratefulnessOverviewContract.Event.NavigateToGratefulnessHistory) }
            )
        }
    )
}
