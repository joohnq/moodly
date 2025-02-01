package com.joohnq.sleep_quality.ui.presentation.sleep_quality

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.core.ui.sharedViewModel
import com.joohnq.sleep_quality.ui.presentation.sleep_quality.event.SleepQualityEvent
import com.joohnq.sleep_quality.ui.viewmodel.SleepQualityViewModel
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.compose.weekcalendar.rememberWeekCalendarState

@Composable
fun SleepQualityScreen(
    onNavigateAddSleepQuality: () -> Unit,
    onGoBack: () -> Unit,
) {
    val sleepQualityViewModel = sharedViewModel<SleepQualityViewModel>()
    val state by sleepQualityViewModel.state.collectAsState()
    val weekCalendarState = rememberWeekCalendarState()

    fun onEvent(event: SleepQualityEvent) =
        when (event) {
            SleepQualityEvent.OnAdd -> onNavigateAddSleepQuality()
            SleepQualityEvent.OnGoBack -> onGoBack()
            SleepQualityEvent.OnAddSleepQuality -> TODO()
            SleepQualityEvent.OnNextMonth -> TODO()
            SleepQualityEvent.OnPreviousMonth -> TODO()
        }

    return SleepQualityUI(
        state = state,
        weekCalendarState = weekCalendarState,
        onEvent = ::onEvent
    )
}
