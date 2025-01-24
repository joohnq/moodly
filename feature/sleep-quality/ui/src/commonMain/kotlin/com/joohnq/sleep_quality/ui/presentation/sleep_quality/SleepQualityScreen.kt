package com.joohnq.sleep_quality.ui.presentation.sleep_quality

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.core.ui.sharedViewModel
import com.joohnq.sleep_quality.ui.presentation.sleep_quality.event.SleepQualityEvent
import com.joohnq.sleep_quality.ui.viewmodel.SleepQualityViewModel

@Composable
fun SleepQualityScreen(
    onNavigateAddSleepQuality: () -> Unit,
    onNavigateSleepHistory: () -> Unit,
    onGoBack: () -> Unit,
) {
    val sleepQualityViewModel = sharedViewModel<SleepQualityViewModel>()
    val sleepQualityState by sleepQualityViewModel.state.collectAsState()

    fun onEvent(event: SleepQualityEvent) =
        when (event) {
            SleepQualityEvent.Add -> onNavigateAddSleepQuality()
            SleepQualityEvent.OnNavigateSleepHistory -> onNavigateAddSleepQuality()
            SleepQualityEvent.GoBack -> onGoBack()
        }

    return SleepQualityUI(
        sleepQualityRecords = sleepQualityState.sleepQualityRecords,
        onEvent = ::onEvent
    )
}
