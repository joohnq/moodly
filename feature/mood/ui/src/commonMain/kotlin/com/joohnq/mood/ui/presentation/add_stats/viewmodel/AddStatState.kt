package com.joohnq.mood.ui.presentation.add_stats.viewmodel

import com.joohnq.mood.domain.entity.Mood

data class AddStatState(
    val mood: Mood = Mood.Depressed,
    val description: String = "",
)
