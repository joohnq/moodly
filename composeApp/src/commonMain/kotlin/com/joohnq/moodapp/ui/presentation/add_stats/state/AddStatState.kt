package com.joohnq.moodapp.ui.presentation.add_stats.state

import com.joohnq.moodapp.domain.Mood
import com.joohnq.moodapp.ui.presentation.add_stats.event.AddStatEvent
import com.joohnq.moodapp.ui.presentation.add_stats.AddStatIntent

data class AddStatState(
    val username: String,
    val selectedMood: Mood,
    val onAddAction: (AddStatIntent) -> Unit,
    val onEvent: (AddStatEvent) -> Unit,
)