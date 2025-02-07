package com.joohnq.self_journal.ui.presentation.add_self_journal.viewmodel

import com.joohnq.mood.ui.resource.MoodResource
import com.joohnq.stress_level.ui.resource.StressorResource

data class AddSelfJournalState(
    val mood: MoodResource? = null,
    val title: String = "",
    val titleError: String? = null,
    val description: String = "",
    val selectedStressStressors: List<StressorResource> = mutableListOf(),
    val sliderValue: Float = 0f,
)