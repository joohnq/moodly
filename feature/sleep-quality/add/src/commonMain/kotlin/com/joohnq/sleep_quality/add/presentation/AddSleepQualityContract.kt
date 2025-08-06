package com.joohnq.sleep_quality.add.presentation

import com.joohnq.mood.add.ui.resource.MoodResource
import com.joohnq.sleep_quality.impl.ui.resource.SleepInfluencesResource
import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityRecordResource
import com.joohnq.ui.UnidirectionalViewModel

interface AddSleepQualityContract {
    interface ViewModel : UnidirectionalViewModel<State, Intent, SideEffect>

    sealed interface Event {
        data object GoBack : Event
    }

    sealed interface Intent {
        data class ChangeMood(
            val mood: MoodResource,
        ) : Intent

        data class ChangeSelectedSleepInfluence(
            val sleepInfluence: SleepInfluencesResource,
        ) : Intent

        data class ChangeShowStartTimePickerDialog(
            val value: Boolean,
        ) : Intent

        data class ChangeShowEndTimePickerDialog(
            val value: Boolean,
        ) : Intent

        data class ChangeStartTime(
            val hour: Int,
            val minute: Int,
        ) : Intent

        data class ChangeEndTime(
            val hour: Int,
            val minute: Int,
        ) : Intent

        data object Add : Intent

        data object ResetState : Intent
    }

    sealed interface SideEffect {
        data object NavigateToNext : SideEffect

        data class ShowError(
            val message: String,
        ) : SideEffect
    }

    data class State(
        val item: SleepQualityRecordResource = SleepQualityRecordResource(),
        val showStartTimePickerDialog: Boolean = false,
        val showEndTimePickerDialog: Boolean = false,
    )
}