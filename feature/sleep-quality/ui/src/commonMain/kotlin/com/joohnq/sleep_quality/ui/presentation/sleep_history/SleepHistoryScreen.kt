package com.joohnq.sleep_quality.ui.presentation.sleep_history

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.core.ui.sharedViewModel
import com.joohnq.sleep_quality.ui.presentation.sleep_history.event.SleepHistoryEvent
import com.joohnq.sleep_quality.ui.viewmodel.SleepQualityViewModel

@Composable
fun SleepHistoryScreen(
    onGoBack: () -> Unit,
    onNavigateToSleepQuality: () -> Unit,
    onNavigateToAddSleepQuality: () -> Unit,
) {
    val sleepQualityViewModel: SleepQualityViewModel = sharedViewModel<SleepQualityViewModel>()
    val state by sleepQualityViewModel.state.collectAsState()

    fun onEvent(event: SleepHistoryEvent) {
        when (event) {
            SleepHistoryEvent.OnNavigateToSleepQuality -> onNavigateToSleepQuality()
            SleepHistoryEvent.OnGoBack -> onGoBack()
            SleepHistoryEvent.OnAddSleepQuality -> onNavigateToAddSleepQuality()
        }
    }

    SleepHistoryUI(
        state = state,
        onEvent = ::onEvent
    )
}