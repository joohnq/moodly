package com.joohnq.freud_score.ui.presentation.freud_score

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.freud_score.ui.presentation.freud_score.event.FreudScoreEvent
import com.joohnq.freud_score.ui.presentation.freud_score.state.FreudScoreState
import com.joohnq.mood.CustomScreen
import com.joohnq.mood.sharedViewModel
import com.joohnq.mood.ui.viewmodel.StatsViewModel

class FreudScoreScreen : CustomScreen<FreudScoreState>() {
    @Composable
    override fun Screen(): FreudScoreState {
        val statsViewModel: StatsViewModel = sharedViewModel()
        val statsState by statsViewModel.statsState.collectAsState()

        fun onEvent(event: FreudScoreEvent) =
            when (event) {
                is FreudScoreEvent.OnGoBack -> onGoBack()
                is FreudScoreEvent.OnNavigateToMoodScreen -> {}
//                    onNavigate(MoodScreen(event.statsRecord.id))
                is FreudScoreEvent.OnAdd -> {}
//                    onNavigate(AddStatScreen())
            }

        return FreudScoreState(
            freudScore = statsState.freudScore,
            statsRecords = statsState.statsRecords,
            onEvent = ::onEvent,
        )
    }

    @Composable
    override fun UI(state: FreudScoreState) = FreudScoreUI(state)
}
