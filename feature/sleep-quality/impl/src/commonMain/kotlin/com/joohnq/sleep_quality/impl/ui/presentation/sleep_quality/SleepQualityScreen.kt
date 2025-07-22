package com.joohnq.sleep_quality.ui.presentation.sleep_quality

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.sleep_quality.ui.presentation.sleep_quality.event.SleepQualityEvent
import com.joohnq.sleep_quality.ui.viewmodel.SleepQualityViewModel
import com.joohnq.ui.sharedViewModel

@Composable
fun SleepQualityScreen(
    onNavigateAddSleepQuality: () -> Unit,
    onGoBack: () -> Unit,
    onNavigateToSleepHistory: () -> Unit,
) {
    val sleepQualityViewModel = sharedViewModel<SleepQualityViewModel>()
    val state by sleepQualityViewModel.state.collectAsState()

    fun onEvent(event: SleepQualityEvent) =
        when (event) {
            SleepQualityEvent.OnGoBack -> onGoBack()
            SleepQualityEvent.OnNavigateToAddSleepQuality -> onNavigateAddSleepQuality()
            SleepQualityEvent.OnNavigateToSleepHistory -> onNavigateToSleepHistory()
        }

    return SleepQualityUI(
        state = state,
        onEvent = ::onEvent,
        onAction = sleepQualityViewModel::onAction
    )
}
