package com.joohnq.moodapp.ui.presentation.all_journals.state

import com.joohnq.moodapp.domain.HealthJournalRecord
import com.joohnq.moodapp.ui.presentation.all_journals.event.AllJournalEvent
import com.joohnq.moodapp.viewmodel.HealthJournalIntent
import kotlinx.datetime.LocalDate

data class AllJournalState(
    val selectedDateTime: LocalDate,
    val healthJournals: Map<LocalDate, List<HealthJournalRecord>?>,
    val openDeleteDialog: Boolean,
    val onAction: (HealthJournalIntent) -> Unit,
    val onEvent: (AllJournalEvent) -> Unit
)
