package com.joohnq.self_journal.ui.viewmodel

import com.joohnq.domain.entity.UiState
import com.joohnq.self_journal.ui.resource.SelfJournalRecordResource

data class SelfJournalState(
    val records: UiState<List<SelfJournalRecordResource>> = UiState.Idle,
)