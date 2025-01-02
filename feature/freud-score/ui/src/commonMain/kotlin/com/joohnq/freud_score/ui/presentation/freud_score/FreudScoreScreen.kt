package com.joohnq.freud_score.ui.presentation.freud_score

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.freud_score.ui.FreudScoreResource.Companion.toResource
import com.joohnq.freud_score.ui.presentation.freud_score.event.FreudScoreEvent
import com.joohnq.freud_score.ui.presentation.freud_score.state.FreudScoreState
import com.joohnq.mood.domain.use_case.CalculateStatsFreudScore
import com.joohnq.mood.ui.viewmodel.StatsState
import com.joohnq.mood.ui.viewmodel.StatsViewModel
import com.joohnq.shared.ui.sharedViewModel
import com.joohnq.shared.ui.state.UiState.Companion.getValue
import org.koin.compose.koinInject

@Composable
fun FreudScoreScreen() {
    val statsViewModel: StatsViewModel = sharedViewModel()
    val statsState: StatsState by statsViewModel.state.collectAsState()
    val calculateStatsFreudScore: CalculateStatsFreudScore = koinInject()
    val freudScore = calculateStatsFreudScore(statsState.statsRecords.getValue())

    fun onEvent(event: FreudScoreEvent) =
        when (event) {
            is FreudScoreEvent.GoBack -> {}
            is FreudScoreEvent.NavigateToMoodScreen -> {}
//                    onNavigate(MoodScreen(event.statsRecord.id))
            is FreudScoreEvent.Add -> {}
//                    onNavigate(AddStatScreen())
        }

    FreudScoreUI(
        FreudScoreState(
            freudScore = freudScore.toResource(),
            statsRecords = statsState.statsRecords,
            onEvent = ::onEvent,
        )
    )
}
