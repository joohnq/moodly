package com.joohnq.sleep_quality.impl.ui.presentation.sleep_quality

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.ui.sharedViewModel

@Composable
fun SleepQualityScreen(
    onNavigateAddSleepQuality: () -> Unit,
    onGoBack: () -> Unit,
    onNavigateToSleepHistory: () -> Unit,
) {
    val sleepQualityViewModel = sharedViewModel<SleepQualityViewModel>()
    val state by sleepQualityViewModel.state.collectAsState()

    fun onEvent(event: SleepQualityContract.Event) =
        when (event) {
            SleepQualityContract.Event.OnGoBack -> onGoBack()
            SleepQualityContract.Event.OnNavigateToAddSleepQuality -> onNavigateAddSleepQuality()
            SleepQualityContract.Event.OnNavigateToSleepHistory -> onNavigateToSleepHistory()
        }

    return SleepQualityContent(
        state = state,
        onEvent = ::onEvent,
        onAction = sleepQualityViewModel::onAction
    )
}