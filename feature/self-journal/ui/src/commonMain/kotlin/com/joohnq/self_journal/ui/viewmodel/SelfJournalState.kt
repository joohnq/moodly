package com.joohnq.self_journal.ui.viewmodel

import com.joohnq.core.ui.entity.UiState
import com.joohnq.self_journal.domain.entity.SelfJournalRecord

data class SelfJournalState(
    val records: UiState<List<SelfJournalRecord>> = UiState.Idle,
)