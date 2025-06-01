package com.joohnq.sleep_quality.ui.presentation.sleep_quality

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.sleep_quality.ui.presentation.sleep_quality.viewmodel.SleepQualityContract
import com.joohnq.sleep_quality.ui.presentation.sleep_quality.viewmodel.SleepQualityViewModel
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
            SleepQualityContract.Event.GoBack -> onGoBack()
            SleepQualityContract.Event.NavigateToAddSleepQuality -> onNavigateAddSleepQuality()
            SleepQualityContract.Event.NavigateToSleepHistory -> onNavigateToSleepHistory()
        }

    return SleepQualityUI(
        state = state,
        onEvent = ::onEvent,
        onIntent = sleepQualityViewModel::onIntent
    )
}
