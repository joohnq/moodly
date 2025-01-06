package com.joohnq.sleep_quality.ui.presentation.sleep_quality

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.shared.ui.CustomScreen
import com.joohnq.shared.ui.sharedViewModel
import com.joohnq.sleep_quality.ui.presentation.sleep_quality.event.SleepQualityEvent
import com.joohnq.sleep_quality.ui.presentation.sleep_quality.state.SleepQualityState
import com.joohnq.sleep_quality.ui.viewmodel.SleepQualityViewModel

class SleepQualityScreen(
    private val onNavigateAddSleepQuality: () -> Unit,
    private val onGoBack: () -> Unit,
) : CustomScreen<SleepQualityState>() {

    @Composable
    override fun Screen(): SleepQualityState {
        val sleepQualityViewModel = sharedViewModel<SleepQualityViewModel>()
        val sleepQualityState by sleepQualityViewModel.state.collectAsState()

        fun onEvent(event: SleepQualityEvent) =
            when (event) {
                SleepQualityEvent.Add -> onNavigateAddSleepQuality()
                SleepQualityEvent.GoBack -> onGoBack()
            }

        return SleepQualityState(
            sleepQualityRecords = sleepQualityState.sleepQualityRecords,
            onEvent = ::onEvent
        )
    }

    @Composable
    override fun UI(state: SleepQualityState) = SleepQualityUI(state)
}