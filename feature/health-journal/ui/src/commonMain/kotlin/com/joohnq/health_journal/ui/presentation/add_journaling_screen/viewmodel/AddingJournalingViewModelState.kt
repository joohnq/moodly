package com.joohnq.health_journal.ui.presentation.add_journaling_screen.viewmodel

import com.joohnq.mood.domain.entity.Mood
import com.joohnq.stress_level.domain.entity.Stressor

data class AddingJournalingViewModelState(
    val mood: Mood? = null,
    val title: String = "",
    val titleError: String? = null,
    val description: String = "",
    val selectedStressStressors: List<Stressor> = mutableListOf(),
    val sliderValue: Float = 0f
)