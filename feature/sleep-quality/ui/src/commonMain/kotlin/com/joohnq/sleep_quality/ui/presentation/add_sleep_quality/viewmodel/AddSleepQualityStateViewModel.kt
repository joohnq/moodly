package com.joohnq.sleep_quality.ui.presentation.add_sleep_quality.viewmodel

import com.joohnq.mood.domain.entity.Mood
import com.joohnq.sleep_quality.domain.entity.SleepInfluences

data class AddSleepQualityStateViewModel(
    val startHour: Int = 12,
    val startMinute: Int = 0,
    val endHour: Int = 12,
    val endMinute: Int = 0,
    val showStartTimePickerDialog: Boolean = false,
    val showEndTimePickerDialog: Boolean = false,
    val mood: Mood? = null,
    val selectedSleepInfluences: List<SleepInfluences> = mutableListOf(),
)