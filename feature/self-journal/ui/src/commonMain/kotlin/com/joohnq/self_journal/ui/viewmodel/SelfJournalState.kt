package com.joohnq.self_journal.ui.viewmodel

import com.joohnq.core.ui.entity.UiState
import com.joohnq.self_journal.ui.SelfJournalRecordResource

data class SelfJournalState(
    val records: UiState<List<SelfJournalRecordResource>> = UiState.Idle,
)