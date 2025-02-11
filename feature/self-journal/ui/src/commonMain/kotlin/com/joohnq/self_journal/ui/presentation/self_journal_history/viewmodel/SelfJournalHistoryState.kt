package com.joohnq.self_journal.ui.presentation.self_journal_history.viewmodel

import com.joohnq.domain.getNow
import kotlinx.datetime.LocalDate

data class SelfJournalHistoryState(
    val selectedDateTime: LocalDate = getNow().date,
    val openDeleteDialog: Boolean = false,
    val currentDeleteId: Int = -1,
)
