package com.joohnq.sleep_quality.ui.presentation.add_sleep_quality.viewmodel

import com.joohnq.mood.domain.entity.Mood
import com.joohnq.sleep_quality.domain.entity.SleepInfluences

sealed class AddSleepQualityIntent {
    data class UpdateMood(val mood: Mood?) : AddSleepQualityIntent()
    data class UpdateSelectedSleepInfluence(val sleepInfluence: SleepInfluences) :
        AddSleepQualityIntent()

    data class UpdateShowStartTimePickerDialog(val value: Boolean) :
        AddSleepQualityIntent()

    data class UpdateShowEndTimePickerDialog(val value: Boolean) :
        AddSleepQualityIntent()

    data class UpdateStartTime(val hour: Int, val minute: Int) : AddSleepQualityIntent()
    data class UpdateEndTime(val hour: Int, val minute: Int) : AddSleepQualityIntent()
    data object ResetState : AddSleepQualityIntent()
}