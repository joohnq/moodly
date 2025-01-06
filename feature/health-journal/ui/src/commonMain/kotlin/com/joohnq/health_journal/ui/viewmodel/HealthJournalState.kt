package com.joohnq.health_journal.ui.viewmodel

import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.shared.domain.entity.UiState

data class HealthJournalState(
    val healthJournalRecords: UiState<List<HealthJournalRecord>> = UiState.Idle,
    val adding: UiState<Boolean> = UiState.Idle,
    val editing: UiState<Boolean> = UiState.Idle,
    val deleting: UiState<Boolean> = UiState.Idle,
)