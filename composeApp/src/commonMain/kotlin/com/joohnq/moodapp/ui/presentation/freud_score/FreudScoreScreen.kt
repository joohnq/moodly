package com.joohnq.moodapp.ui.presentation.freud_score

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.ui.CustomScreen
import com.joohnq.moodapp.ui.presentation.add_stats.AddStatScreen
import com.joohnq.moodapp.ui.presentation.freud_score.event.FreudScoreEvent
import com.joohnq.moodapp.ui.presentation.freud_score.state.FreudScoreState
import com.joohnq.moodapp.ui.presentation.mood.MoodScreen
import com.joohnq.moodapp.ui.state.UiState.Companion.getValue
import com.joohnq.moodapp.util.helper.StatsManager
import com.joohnq.moodapp.viewmodel.StatsViewModel

class FreudScoreScreen : CustomScreen<FreudScoreState>() {
    @Composable
    override fun Screen(): FreudScoreState {
        val statsViewModel: StatsViewModel = sharedViewModel()
        val statsState by statsViewModel.statsState.collectAsState()
        val mapStatsRecords =
            remember { StatsManager.getGroupByDate(statsState.statsRecords.getValue()) }

        fun onEvent(event: FreudScoreEvent) =
            when (event) {
                is FreudScoreEvent.OnGoBack -> onGoBack()
                is FreudScoreEvent.OnNavigateToMoodScreen -> onNavigate(MoodScreen(event.statsRecord.id))

                is FreudScoreEvent.OnAdd -> onNavigate(AddStatScreen())
            }

        return FreudScoreState(
            freudScore = statsState.freudScore,
            mapStatsRecords = mapStatsRecords,
            onAction = ::onEvent
        )
    }

    @Composable
    override fun UI(state: FreudScoreState) = FreudScoreUI(state)
}
