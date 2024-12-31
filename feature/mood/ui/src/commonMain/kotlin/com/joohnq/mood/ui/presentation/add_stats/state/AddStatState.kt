package com.joohnq.mood.ui.presentation.add_stats.state

import com.joohnq.mood.domain.entity.Mood
import com.joohnq.mood.ui.presentation.add_stats.event.AddStatEvent
import com.joohnq.mood.ui.presentation.add_stats.viewmodel.AddStatIntent

data class AddStatState(
    val username: String,
    val selectedMood: Mood,
    val onAddAction: (AddStatIntent) -> Unit,
    val onEvent: (AddStatEvent) -> Unit,
)