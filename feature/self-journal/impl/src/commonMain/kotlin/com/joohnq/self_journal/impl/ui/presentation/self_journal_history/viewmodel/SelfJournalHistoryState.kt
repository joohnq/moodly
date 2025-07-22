package com.joohnq.self_journal.impl.ui.presentation.self_journal_history.viewmodel

import com.joohnq.api.getNow
import kotlinx.datetime.LocalDate

data class SelfJournalHistoryState(
    val selectedDateTime: LocalDate = getNow().date,
    val openDeleteDialog: Boolean = false,
    val currentDeleteId: Int = -1,
)
