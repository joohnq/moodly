package com.joohnq.moodapp.ui.presentation.journaling.state

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.domain.HealthJournalRecord
import com.joohnq.moodapp.ui.presentation.journaling.event.JournalingEvent

data class JournalingState(
    val padding: PaddingValues = PaddingValues(0.dp),
    val journals: List<HealthJournalRecord>,
    val onEvent: (JournalingEvent) -> Unit = {}
)