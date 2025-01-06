package com.joohnq.freud_score.ui.presentation.freud_score.state

import com.joohnq.freud_score.ui.presentation.freud_score.event.FreudScoreEvent
import com.joohnq.freud_score.ui.resource.FreudScoreResource
import com.joohnq.mood.domain.entity.StatsRecord
import com.joohnq.core.ui.entity.UiState

data class FreudScoreState(
    val freudScore: FreudScoreResource,
    val statsRecords: UiState<List<StatsRecord>>,
    val onEvent: (FreudScoreEvent) -> Unit = {},
)