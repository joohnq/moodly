package com.joohnq.sleep_quality.ui.presentation.sleep_quality

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.mood.CustomScreen
import com.joohnq.mood.sharedViewModel
import com.joohnq.sleep_quality.ui.presentation.sleep_quality.event.SleepQualityEvent
import com.joohnq.sleep_quality.ui.presentation.sleep_quality.state.SleepQualityState
import com.joohnq.sleep_quality.ui.viewmodel.SleepQualityViewModel

class SleepQualityScreen : CustomScreen<SleepQualityState>() {
    @Composable
    override fun Screen(): SleepQualityState {
        val sleepQualityViewModel = sharedViewModel<SleepQualityViewModel>()
        val sleepQualityState by sleepQualityViewModel.sleepQualityState.collectAsState()

        fun onEvent(event: SleepQualityEvent) =
            when (event) {
                SleepQualityEvent.OnAdd -> {}
//                    onNavigate(AddSleepQualityScreen())

                SleepQualityEvent.OnGoBack -> onGoBack()
            }


        return SleepQualityState(
            sleepQualityRecords = sleepQualityState.sleepQualityRecords,
            onEvent = ::onEvent
        )
    }

    @Composable
    override fun UI(state: SleepQualityState) = SleepQualityUI(state)
}