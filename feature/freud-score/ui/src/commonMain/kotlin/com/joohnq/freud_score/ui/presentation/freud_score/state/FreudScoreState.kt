package com.joohnq.freud_score.ui.presentation.freud_score.state

import com.joohnq.domain.entity.StatsRecord
import com.joohnq.freud_score.domain.entity.FreudScore
import com.joohnq.freud_score.ui.presentation.freud_score.event.FreudScoreEvent
import com.joohnq.mood.state.UiState

data class FreudScoreState(
    val freudScore: FreudScore,
    val statsRecords: UiState<List<StatsRecord>>,
    val onEvent: (FreudScoreEvent) -> Unit = {}
)