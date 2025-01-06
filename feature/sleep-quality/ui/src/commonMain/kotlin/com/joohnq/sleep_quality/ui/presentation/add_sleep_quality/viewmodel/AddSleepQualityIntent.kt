package com.joohnq.sleep_quality.ui.presentation.add_sleep_quality.viewmodel

import com.joohnq.mood.ui.resource.MoodResource
import com.joohnq.sleep_quality.ui.resource.SleepInfluencesResource

sealed class AddSleepQualityIntent {
    data class UpdateMood(val mood: MoodResource?) : AddSleepQualityIntent()
    data class UpdateSelectedSleepInfluence(val sleepInfluence: SleepInfluencesResource) :
        AddSleepQualityIntent()

    data class UpdateShowStartTimePickerDialog(val value: Boolean) :
        AddSleepQualityIntent()

    data class UpdateShowEndTimePickerDialog(val value: Boolean) :
        AddSleepQualityIntent()

    data class UpdateStartTime(val hour: Int, val minute: Int) : AddSleepQualityIntent()
    data class UpdateEndTime(val hour: Int, val minute: Int) : AddSleepQualityIntent()
    data object ResetState : AddSleepQualityIntent()
}