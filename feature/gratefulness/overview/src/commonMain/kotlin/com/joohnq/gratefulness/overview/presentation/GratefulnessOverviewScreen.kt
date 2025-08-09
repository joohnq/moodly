package com.joohnq.gratefulness.overview.presentation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import com.joohnq.api.getNow
import com.joohnq.ui.observe
import com.kizitonwose.calendar.compose.weekcalendar.rememberWeekCalendarState
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun GratefulnessOverviewScreen(
    snackBarState: SnackbarHostState,
    goBack: () -> Unit,
    navigateToAddGratefulness: () -> Unit,
    navigateToGratefulnessHistory: () -> Unit,
    viewModel: GratefulnessOverviewViewModel = koinViewModel(),
) {
    val weekCalendarState =
        rememberWeekCalendarState(
            endDate = getNow().date
        )
    val (state, onIntent) =
        viewModel.observe { sideEffect ->
            when (sideEffect) {
                is GratefulnessOverviewContract.SideEffect.ShowError ->
                    launch { snackBarState.showSnackbar(sideEffect.message) }
            }
        }

    fun onEvent(event: GratefulnessOverviewContract.Event) {
        when (event) {
            GratefulnessOverviewContract.Event.GoBack -> goBack()
            GratefulnessOverviewContract.Event.NavigateToAddGratefulness -> navigateToAddGratefulness()
            GratefulnessOverviewContract.Event.NavigateToGratefulnessHistory -> navigateToGratefulnessHistory()
        }
    }

    GratefulnessOverviewContent(
        state = state,
        weekCalendarState = weekCalendarState,
        onEvent = ::onEvent,
        onIntent = onIntent
    )
}
