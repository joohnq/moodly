package com.joohnq.health_journal.ui.presentation.add_journaling_screen.viewmodel

import com.joohnq.mood.ui.MoodResource
import com.joohnq.stress_level.ui.StressorResource

data class AddingJournalingViewModelState(
    val mood: MoodResource? = null,
    val title: String = "",
    val titleError: String? = null,
    val description: String = "",
    val selectedStressStressors: List<StressorResource> = mutableListOf(),
    val sliderValue: Float = 0f,
)