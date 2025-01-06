package com.joohnq.home.ui.presentation.home.state

import com.joohnq.domain.entity.User
import com.joohnq.freud_score.ui.resource.FreudScoreResource
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.home.ui.presentation.home.event.HomeEvent
import com.joohnq.mood.domain.entity.StatsRecord
import com.joohnq.core.ui.entity.UiState
import com.joohnq.sleep_quality.domain.entity.SleepQualityRecord
import com.joohnq.stress_level.domain.entity.StressLevelRecord

data class HomeState(
    val today: String = "",
    val user: UiState<User>,
    val statsRecord: UiState<List<StatsRecord>>,
    val sleepQuality: UiState<List<SleepQualityRecord>>,
    val stressLevel: UiState<List<StressLevelRecord>>,
    val freudScore: FreudScoreResource? = null,
    val healthJournal: UiState<List<HealthJournalRecord>>,
    val onEvent: (HomeEvent) -> Unit = {},
)