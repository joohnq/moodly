package com.joohnq.sleep_quality.ui.presentation.add_sleep_quality.viewmodel

import com.joohnq.mood.ui.resource.MoodResource
import com.joohnq.sleep_quality.ui.resource.SleepInfluencesResource
import com.joohnq.sleep_quality.ui.resource.SleepQualityRecordResource

sealed interface AddSleepQualityContract {
    sealed interface Intent : AddSleepQualityContract {
        data class UpdateMood(val mood: MoodResource) : Intent
        data class UpdateSelectedSleepInfluence(val sleepInfluence: SleepInfluencesResource) :
            Intent

        data class UpdateShowStartTimePickerDialog(val value: Boolean) :
            Intent

        data class UpdateShowEndTimePickerDialog(val value: Boolean) :
            Intent

        data class UpdateStartTime(val hour: Int, val minute: Int) : Intent
        data class UpdateEndTime(val hour: Int, val minute: Int) : Intent
        data object ResetState : Intent
    }

    data class State(
        val record: SleepQualityRecordResource = SleepQualityRecordResource(),
        val showStartTimePickerDialog: Boolean = false,
        val showEndTimePickerDialog: Boolean = false,
    ) : AddSleepQualityContract

    sealed interface Event : AddSleepQualityContract {
        data object GoBack : Event
        data object Add : Event
        data object NavigateToSleepQuality : Event
    }
}