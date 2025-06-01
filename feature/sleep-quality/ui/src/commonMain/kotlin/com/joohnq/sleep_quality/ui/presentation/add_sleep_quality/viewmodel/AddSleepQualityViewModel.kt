package com.joohnq.sleep_quality.ui.presentation.add_sleep_quality.viewmodel

import androidx.lifecycle.ViewModel
import com.joohnq.domain.mapper.toggle
import com.joohnq.mood.ui.resource.mapper.toSleepQuality
import com.joohnq.mood.ui.resource.MoodResource
import com.joohnq.sleep_quality.ui.resource.mapper.toResource
import com.joohnq.sleep_quality.ui.resource.SleepInfluencesResource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AddSleepQualityViewModel : ViewModel() {
    private val _state = MutableStateFlow(AddSleepQualityContract.State())
    val state: StateFlow<AddSleepQualityContract.State> =
        _state.asStateFlow()

    fun onIntent(intent: AddSleepQualityContract.Intent) {
        when (intent) {
            is AddSleepQualityContract.Intent.UpdateMood -> updateMood(intent.mood)
            is AddSleepQualityContract.Intent.UpdateSelectedSleepInfluence ->
                updateSelectedSleepInfluences(intent.sleepInfluence)

            is AddSleepQualityContract.Intent.UpdateShowStartTimePickerDialog ->
                updateShowStartTimePickerDialog(intent.value)

            is AddSleepQualityContract.Intent.UpdateShowEndTimePickerDialog ->
                updateShowEndTimePickerDialog(intent.value)

            is AddSleepQualityContract.Intent.UpdateStartTime ->
                updateStartTime(intent.hour, intent.minute)

            is AddSleepQualityContract.Intent.UpdateEndTime ->
                updateEndTime(intent.hour, intent.minute)

            AddSleepQualityContract.Intent.ResetState -> resetAdding()
        }
    }

    private fun updateMood(mood: MoodResource) {
        _state.update {
            it.copy(
                record = it.record.copy(
                    sleepQuality = mood.toSleepQuality().toResource()
                ),
            )
        }
    }

    private fun updateSelectedSleepInfluences(sleepInfluences: SleepInfluencesResource) {
        _state.update {
            val influences = state.value.record.sleepInfluences.toggle(sleepInfluences)
            it.copy(
                record = it.record.copy(sleepInfluences = influences)
            )
        }
    }

    private fun updateShowStartTimePickerDialog(value: Boolean) {
        _state.update {
            it.copy(showStartTimePickerDialog = value)
        }
    }

    private fun updateShowEndTimePickerDialog(value: Boolean) {
        _state.update {
            it.copy(showEndTimePickerDialog = value)
        }
    }

    private fun updateStartTime(hour: Int, minute: Int) {
        _state.update {
            it.copy(
                record = it.record.copy(startSleeping = it.record.startSleeping.copy(hour, minute)),
            )
        }
    }

    private fun updateEndTime(hour: Int, minute: Int) {
        _state.update {
            it.copy(
                record = it.record.copy(endSleeping = it.record.endSleeping.copy(hour, minute)),
            )
        }
    }

    private fun resetAdding() {
        _state.update { AddSleepQualityContract.State() }
    }
}