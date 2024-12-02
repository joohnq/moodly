package com.joohnq.moodapp.ui.presentation.freud_score.state

import com.joohnq.moodapp.domain.FreudScore
import com.joohnq.moodapp.domain.StatsRecord
import com.joohnq.moodapp.ui.presentation.freud_score.event.FreudScoreEvent
import com.joohnq.moodapp.ui.state.UiState

data class FreudScoreState(
    val freudScore: FreudScore,
    val statsRecords: UiState<List<StatsRecord>>,
    val onEvent: (FreudScoreEvent) -> Unit = {}
)