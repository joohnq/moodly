package com.joohnq.moodapp.ui.presentation.add_stats.state

import com.joohnq.moodapp.domain.Mood
import com.joohnq.moodapp.ui.presentation.add_stats.event.AddStatEvent
import com.joohnq.moodapp.viewmodel.StatsIntent

data class AddStatState(
    val username: String,
    val selectedMood: Mood,
    val onAction: (StatsIntent) -> Unit = {},
    val onNavigation: (AddStatEvent) -> Unit = {},
)