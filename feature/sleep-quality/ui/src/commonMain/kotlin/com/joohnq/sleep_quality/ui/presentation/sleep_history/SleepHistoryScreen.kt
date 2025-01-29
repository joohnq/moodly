package com.joohnq.sleep_quality.ui.presentation.sleep_history

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.core.ui.sharedViewModel
import com.joohnq.shared_resources.animateScrollToNextMonth
import com.joohnq.shared_resources.animateScrollToPreviousMonth
import com.joohnq.sleep_quality.ui.presentation.sleep_history.event.SleepHistoryEvent
import com.joohnq.sleep_quality.ui.viewmodel.SleepQualityViewModel
import com.kizitonwose.calendar.compose.rememberCalendarState

@Composable
fun SleepHistoryScreen(
    onGoBack: () -> Unit,
    onNavigateToSleepQuality: (Int) -> Unit,
    onNavigateToAddSleepQuality: () -> Unit,
) {
    val sleepQualityViewModel: SleepQualityViewModel = sharedViewModel<SleepQualityViewModel>()
    val state by sleepQualityViewModel.state.collectAsState()
    val calendarState = rememberCalendarState()
    val scope = rememberCoroutineScope()

    fun onEvent(event: SleepHistoryEvent) {
        when (event) {
            is SleepHistoryEvent.OnNavigateToSleepQuality -> onNavigateToSleepQuality(event.id)
            SleepHistoryEvent.OnGoBack -> onGoBack()
            SleepHistoryEvent.OnNextMonth -> scope.animateScrollToNextMonth(calendarState)
            SleepHistoryEvent.OnPreviousMonth -> scope.animateScrollToPreviousMonth(calendarState)
            SleepHistoryEvent.OnAddSleepQuality -> onNavigateToAddSleepQuality()
        }
    }

    SleepHistoryUI(
        state = state,
        calendarState = calendarState,
        onEvent = ::onEvent
    )
}