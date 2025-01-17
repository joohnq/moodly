package com.joohnq.health_journal.ui.viewmodel

import com.joohnq.core.ui.entity.UiState
import com.joohnq.health_journal.domain.entity.HealthJournalRecord

data class HealthJournalState(
    val healthJournalRecords: UiState<List<HealthJournalRecord>> = UiState.Idle,
)