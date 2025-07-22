package com.joohnq.sleep_quality.ui.presentation.add_sleep_quality.viewmodel

import androidx.lifecycle.ViewModel
import com.joohnq.domain.mapper.toggle
import com.joohnq.mood.impl.ui.mapper.toSleepQuality
import com.joohnq.mood.impl.ui.resource.MoodResource
import com.joohnq.sleep_quality.ui.mapper.toResource
import com.joohnq.sleep_quality.ui.resource.SleepInfluencesResource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AddSleepQualityViewModel : ViewModel() {
    private val _state = MutableStateFlow(AddSleepQualityState())
    val state: StateFlow<AddSleepQualityState> =
        _state.asStateFlow()

    fun onAction(intent: AddSleepQualityIntent) {
        when (intent) {
            is AddSleepQualityIntent.UpdateMood -> updateMood(intent.mood)
            is AddSleepQualityIntent.UpdateSelectedSleepInfluence ->
                updateSelectedSleepInfluences(intent.sleepInfluence)

            is AddSleepQualityIntent.UpdateShowStartTimePickerDialog ->
                updateShowStartTimePickerDialog(intent.value)

            is AddSleepQualityIntent.UpdateShowEndTimePickerDialog ->
                updateShowEndTimePickerDialog(intent.value)

            is AddSleepQualityIntent.UpdateStartTime ->
                updateStartTime(intent.hour, intent.minute)

            is AddSleepQualityIntent.UpdateEndTime ->
                updateEndTime(intent.hour, intent.minute)

            AddSleepQualityIntent.ResetState -> resetAdding()
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
        _state.update { AddSleepQualityState() }
    }
}