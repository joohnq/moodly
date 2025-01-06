package com.joohnq.health_journal.ui.presentation.journaling.state

import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.health_journal.ui.presentation.journaling.event.JournalingEvent
import com.joohnq.shared.domain.entity.UiState

data class JournalingState(
    val journals: UiState<List<HealthJournalRecord>>,
    val onEvent: (JournalingEvent) -> Unit = {},
)