package com.joohnq.sleep_quality.ui.presentation.sleep_history

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.core.ui.sharedViewModel
import com.joohnq.sleep_quality.ui.presentation.sleep_history.event.SleepHistoryEvent
import com.joohnq.sleep_quality.ui.viewmodel.SleepQualityViewModel
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.minusMonths
import com.kizitonwose.calendar.core.plusMonths
import kotlinx.coroutines.launch

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

    fun animateScrollToNextMonth() {
        scope.launch {
            calendarState.animateScrollToMonth(
                calendarState.firstVisibleMonth.yearMonth.plusMonths(
                    1
                )
            )
        }
    }

    fun animateScrollToPreviousMonth() {
        scope.launch {
            calendarState.animateScrollToMonth(
                calendarState.firstVisibleMonth.yearMonth.minusMonths(
                    1
                )
            )
        }
    }

    fun onEvent(event: SleepHistoryEvent) {
        when (event) {
            is SleepHistoryEvent.OnNavigateToSleepQuality -> onNavigateToSleepQuality(event.id)
            SleepHistoryEvent.OnGoBack -> onGoBack()
            SleepHistoryEvent.OnNextMonth -> animateScrollToNextMonth()
            SleepHistoryEvent.OnPreviousMonth -> animateScrollToPreviousMonth()
            SleepHistoryEvent.OnCreateSleepQuality -> onNavigateToAddSleepQuality()
        }
    }

    SleepHistoryUI(
        state = state,
        calendarState = calendarState,
        onEvent = ::onEvent
    )
}