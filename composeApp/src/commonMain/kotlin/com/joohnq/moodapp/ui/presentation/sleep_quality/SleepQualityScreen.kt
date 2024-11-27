package com.joohnq.moodapp.ui.presentation.sleep_quality

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.ui.CustomScreen
import com.joohnq.moodapp.ui.presentation.add_sleep_quality.AddSleepQualityScreen
import com.joohnq.moodapp.ui.presentation.sleep_quality.event.SleepQualityEvent
import com.joohnq.moodapp.ui.presentation.sleep_quality.state.SleepQualityState
import com.joohnq.moodapp.ui.state.UiState.Companion.getValue
import com.joohnq.moodapp.viewmodel.SleepQualityViewModel

class SleepQualityScreen : CustomScreen<SleepQualityState>() {
    @Composable
    override fun Screen(): SleepQualityState {
        val sleepQualityViewModel = sharedViewModel<SleepQualityViewModel>()
        val sleepQualityState by sleepQualityViewModel.sleepQualityState.collectAsState()

        fun onEvent(event: SleepQualityEvent) =
            when (event) {
                SleepQualityEvent.OnAdd ->
                    onNavigate(AddSleepQualityScreen())

                SleepQualityEvent.OnGoBack -> onGoBack()
            }


        return SleepQualityState(
            sleepQualityRecords = sleepQualityState.sleepQualityRecords.getValue(),
            onEvent = ::onEvent
        )
    }

    @Composable
    override fun UI(state: SleepQualityState) = SleepQualityUI(state)
}