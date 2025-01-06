package com.joohnq.health_journal.ui.presentation.all_journals.viewmodel

import com.joohnq.core.ui.DatetimeProvider
import kotlinx.datetime.LocalDate

data class AllJournalViewModelState(
    val selectedDateTime: LocalDate = DatetimeProvider.getCurrentDateTime().date,
    val openDeleteDialog: Boolean = false,
    val currentDeleteId: Int = -1,
)
