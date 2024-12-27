package com.joohnq.mood.ui.presentation.add_stats.state

import com.joohnq.domain.entity.Mood
import com.joohnq.mood.ui.presentation.add_stats.AddStatIntent
import com.joohnq.mood.ui.presentation.add_stats.event.AddStatEvent

data class AddStatState(
    val username: String,
    val selectedMood: Mood,
    val onAddAction: (AddStatIntent) -> Unit,
    val onEvent: (AddStatEvent) -> Unit,
)