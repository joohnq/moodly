package com.joohnq.sleep_quality.ui.presentation.add_sleep_quality.viewmodel

import com.joohnq.mood.ui.MoodResource
import com.joohnq.sleep_quality.ui.SleepInfluencesResource

data class AddSleepQualityStateViewModel(
    val startHour: Int = 12,
    val startMinute: Int = 0,
    val endHour: Int = 12,
    val endMinute: Int = 0,
    val showStartTimePickerDialog: Boolean = false,
    val showEndTimePickerDialog: Boolean = false,
    val mood: MoodResource? = null,
    val selectedSleepInfluences: List<SleepInfluencesResource> = mutableListOf(),
)