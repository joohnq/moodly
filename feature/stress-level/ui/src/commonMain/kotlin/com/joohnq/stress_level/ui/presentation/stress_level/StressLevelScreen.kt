package com.joohnq.stress_level.ui.presentation.stress_level

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.shared.ui.CustomScreen
import com.joohnq.shared.ui.sharedViewModel
import com.joohnq.stress_level.ui.presentation.add_stress_level.AddStressLevelScreen
import com.joohnq.stress_level.ui.presentation.stress_level.event.StressLevelEvent
import com.joohnq.stress_level.ui.presentation.stress_level.state.StressLevelState
import com.joohnq.stress_level.ui.viewmodel.StressLevelViewModel

class StressLevelScreen : CustomScreen<StressLevelState>() {
    @Composable
    override fun Screen(): StressLevelState {
        val stressLevelViewModel: StressLevelViewModel = sharedViewModel()
        val stressLevelState by stressLevelViewModel.stressLevelState.collectAsState()

        fun onEvent(event: StressLevelEvent) =
            when (event) {
                is StressLevelEvent.OnAdd -> {
                    onNavigate(AddStressLevelScreen())
                }

                is StressLevelEvent.OnGoBack -> onGoBack()
            }

        return StressLevelState(
            stressLevelRecords = stressLevelState.stressLevelRecords,
            onEvent = ::onEvent
        )
    }

    @Composable
    override fun UI(state: StressLevelState) = StressLevelUI(state)
}