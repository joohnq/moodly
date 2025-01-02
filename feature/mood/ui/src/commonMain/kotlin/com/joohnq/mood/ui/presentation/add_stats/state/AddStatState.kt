package com.joohnq.mood.ui.presentation.add_stats.state

import com.joohnq.mood.ui.MoodResource
import com.joohnq.mood.ui.presentation.add_stats.event.AddStatEvent
import com.joohnq.mood.ui.presentation.add_stats.viewmodel.AddStatIntent

data class AddStatState(
    val username: String,
    val selectedMood: MoodResource,
    val onAddAction: (AddStatIntent) -> Unit,
    val onEvent: (AddStatEvent) -> Unit,
)