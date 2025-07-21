package com.joohnq.self_journal.impl.ui.viewmodel

import com.joohnq.ui.entity.UiState
import com.joohnq.self_journal.ui.resource.SelfJournalRecordResource

data class SelfJournalState(
    val records: UiState<List<SelfJournalRecordResource>> = UiState.Idle,
)