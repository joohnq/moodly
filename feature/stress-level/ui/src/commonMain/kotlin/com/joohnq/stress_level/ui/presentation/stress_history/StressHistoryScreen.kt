package com.joohnq.stress_level.ui.presentation.stress_history

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.core.ui.sharedViewModel
import com.joohnq.stress_level.ui.presentation.stress_history.event.StressHistoryEvent
import com.joohnq.stress_level.ui.viewmodel.StressLevelViewModel
import com.kizitonwose.calendar.compose.rememberCalendarState


@Composable
fun StressHistoryScreen(
    onGoBack: () -> Unit,
    onNavigateStressLevel: (Int) -> Unit,
) {
    val stressLevelViewModel: StressLevelViewModel = sharedViewModel()
    val state by stressLevelViewModel.state.collectAsState()
    val calendarState = rememberCalendarState()

    fun onEvent(event: StressHistoryEvent) {
        when (event) {
            StressHistoryEvent.OnGoBack -> onGoBack()
            is StressHistoryEvent.OnNavigateToStressLevel -> onNavigateStressLevel(event.id)
        }
    }

    StressHistoryUI(
        state = state,
        onEvent = ::onEvent
    )
}