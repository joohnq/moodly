package com.joohnq.moodapp.ui.presentation.add_sleep_quality

import androidx.lifecycle.ViewModel
import com.joohnq.moodapp.domain.Mood
import com.joohnq.moodapp.domain.SleepInfluences
import com.joohnq.moodapp.util.mappers.toggle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

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

class AddSleepQualityViewModel : ViewModel() {
    private val _addSleepQualityStateViewModel = MutableStateFlow(AddSleepQualityStateViewModel())
    val addSleepQualityStateViewModel: StateFlow<AddSleepQualityStateViewModel> =
        _addSleepQualityStateViewModel.asStateFlow()

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

    private fun updateMood(mood: Mood?) {
        _addSleepQualityStateViewModel.update {
            it.copy(
                mood = mood
            )
        }
    }

    private fun updateSelectedSleepInfluences(sleepInfluences: SleepInfluences) {
        _addSleepQualityStateViewModel.update {
            it.copy(
                selectedSleepInfluences = addSleepQualityStateViewModel.value.selectedSleepInfluences.toggle(
                    sleepInfluences
                )
            )
        }
    }

    private fun updateShowStartTimePickerDialog(value: Boolean) {
        _addSleepQualityStateViewModel.update {
            it.copy(showStartTimePickerDialog = value)
        }
    }

    private fun updateShowEndTimePickerDialog(value: Boolean) {
        _addSleepQualityStateViewModel.update {
            it.copy(showEndTimePickerDialog = value)
        }
    }

    private fun updateStartTime(hour: Int, minute: Int) {
        _addSleepQualityStateViewModel.update {
            it.copy(
                startHour = hour,
                startMinute = minute
            )
        }
    }

    private fun updateEndTime(hour: Int, minute: Int) {
        _addSleepQualityStateViewModel.update {
            it.copy(
                endHour = hour,
                endMinute = minute
            )
        }
    }

    private fun resetAdding() {
        _addSleepQualityStateViewModel.update { AddSleepQualityStateViewModel() }
    }
}