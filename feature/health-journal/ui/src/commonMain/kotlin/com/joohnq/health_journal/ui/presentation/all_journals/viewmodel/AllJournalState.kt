package com.joohnq.health_journal.ui.presentation.all_journals.viewmodel

import com.joohnq.core.ui.getNow
import kotlinx.datetime.LocalDate

data class AllJournalState(
    val selectedDateTime: LocalDate = getNow().date,
    val openDeleteDialog: Boolean = false,
    val currentDeleteId: Int = -1,
)
