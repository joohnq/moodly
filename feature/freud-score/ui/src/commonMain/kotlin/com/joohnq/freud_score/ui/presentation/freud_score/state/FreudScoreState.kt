package com.joohnq.freud_score.ui.presentation.freud_score.state

import com.joohnq.freud_score.ui.FreudScoreResource
import com.joohnq.freud_score.ui.presentation.freud_score.event.FreudScoreEvent
import com.joohnq.mood.domain.entity.StatsRecord
import com.joohnq.shared.ui.state.UiState

data class FreudScoreState(
    val freudScore: FreudScoreResource,
    val statsRecords: UiState<List<StatsRecord>>,
    val onEvent: (FreudScoreEvent) -> Unit = {},
)