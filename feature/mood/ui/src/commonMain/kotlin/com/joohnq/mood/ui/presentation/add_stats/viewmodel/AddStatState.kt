package com.joohnq.mood.ui.presentation.add_stats.viewmodel

import com.joohnq.mood.ui.MoodResource

data class AddStatState(
    val mood: MoodResource = MoodResource.Depressed,
    val description: String = "",
)
