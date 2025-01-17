package com.joohnq.mood.ui.presentation.add_stats.state

import com.joohnq.mood.ui.presentation.add_stats.event.AddStatEvent
import com.joohnq.mood.ui.presentation.add_stats.viewmodel.AddStatIntent
import com.joohnq.mood.ui.resource.MoodResource

data class AddStatState(
    val selectedMood: MoodResource,
    val onAddAction: (AddStatIntent) -> Unit,
    val onEvent: (AddStatEvent) -> Unit,
)