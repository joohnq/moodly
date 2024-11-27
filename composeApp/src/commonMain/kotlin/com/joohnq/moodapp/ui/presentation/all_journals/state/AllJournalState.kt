package com.joohnq.moodapp.ui.presentation.all_journals.state

import com.joohnq.moodapp.domain.HealthJournalRecord
import com.joohnq.moodapp.ui.presentation.all_journals.AllJournalIntent
import com.joohnq.moodapp.ui.presentation.all_journals.event.AllJournalEvent
import com.joohnq.moodapp.viewmodel.HealthJournalIntent
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

data class AllJournalState(
    val selectedDateTime: LocalDate,
    val dateCreated: LocalDateTime,
    val healthJournalRecords: List<HealthJournalRecord>,
    val openDeleteDialog: Boolean,
    val onAction: (HealthJournalIntent) -> Unit,
    val onAllAction: (AllJournalIntent) -> Unit,
    val onEvent: (AllJournalEvent) -> Unit
)
