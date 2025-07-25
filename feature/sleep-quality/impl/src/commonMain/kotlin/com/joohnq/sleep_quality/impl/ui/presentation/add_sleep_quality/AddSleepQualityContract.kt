package com.joohnq.sleep_quality.impl.ui.presentation.add_sleep_quality

import com.joohnq.mood.impl.ui.resource.MoodResource
import com.joohnq.sleep_quality.impl.ui.resource.SleepInfluencesResource
import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityRecordResource

interface AddSleepQualityContract {
    sealed interface Event {
        data object OnGoBack : Event
        data object OnAdd : Event
        data object OnNavigateToSleepQuality : Event
    }

    sealed interface Intent {
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
    )
}