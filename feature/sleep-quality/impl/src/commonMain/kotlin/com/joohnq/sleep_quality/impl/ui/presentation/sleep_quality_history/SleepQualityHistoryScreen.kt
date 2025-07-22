package com.joohnq.sleep_quality.impl.ui.presentation.sleep_quality_history

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.ui.sharedViewModel
import com.joohnq.sleep_quality.impl.ui.presentation.sleep_quality_history.event.SleepQualityHistoryEvent
import com.joohnq.sleep_quality.impl.ui.viewmodel.SleepQualityViewModel

@Composable
fun SleepQualityHistoryScreen(
    onGoBack: () -> Unit,
    onNavigateToSleepQuality: () -> Unit,
    onNavigateToAddSleepQuality: () -> Unit,
) {
    val sleepQualityViewModel: SleepQualityViewModel = sharedViewModel<SleepQualityViewModel>()
    val state by sleepQualityViewModel.state.collectAsState()

    fun onEvent(event: SleepQualityHistoryEvent) {
        when (event) {
            SleepQualityHistoryEvent.OnNavigateToSleepQualityQuality -> onNavigateToSleepQuality()
            SleepQualityHistoryEvent.OnGoBack -> onGoBack()
            SleepQualityHistoryEvent.OnAddSleepQualityQuality -> onNavigateToAddSleepQuality()
        }
    }

    SleepQualityHistoryUI(
        state = state,
        onEvent = ::onEvent
    )
}