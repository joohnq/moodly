package com.joohnq.sleep_quality.ui.presentation.sleep_quality_history

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.sleep_quality.ui.presentation.sleep_quality.viewmodel.SleepQualityViewModel
import com.joohnq.ui.sharedViewModel

@Composable
fun SleepQualityHistoryScreen(
    onGoBack: () -> Unit,
    onNavigateToSleepQuality: () -> Unit,
    onNavigateToAddSleepQuality: () -> Unit,
) {
    val sleepQualityViewModel: SleepQualityViewModel = sharedViewModel<SleepQualityViewModel>()
    val state by sleepQualityViewModel.state.collectAsState()

    fun onEvent(event: SleepQualityHistoryContract.Event) {
        when (event) {
            SleepQualityHistoryContract.Event.OnNavigateToSleepQualityQuality -> onNavigateToSleepQuality()
            SleepQualityHistoryContract.Event.OnGoBack -> onGoBack()
            SleepQualityHistoryContract.Event.OnAddSleepQualityQuality -> onNavigateToAddSleepQuality()
        }
    }

    SleepQualityHistoryUI(
        state = state,
        onEvent = ::onEvent
    )
}