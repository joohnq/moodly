package com.joohnq.moodapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.moodapp.entities.Mood
import com.joohnq.moodapp.entities.SleepInfluences
import com.joohnq.moodapp.entities.SleepQuality
import com.joohnq.moodapp.entities.SleepQualityRecord
import com.joohnq.moodapp.helper.DatetimeHelper
import com.joohnq.moodapp.model.repository.SleepQualityRepository
import com.joohnq.moodapp.view.state.UiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class AddingSleepQuality(
    val status: UiState<Boolean> = UiState.Idle,
    val startHour: Int = 12,
    val startMinute: Int = 0,
    val endHour: Int = 12,
    val endMinute: Int = 0,
    val showStartTimePickerDialog: Boolean = false,
    val showEndTimePickerDialog: Boolean = false,
    val mood: Mood? = null,
    val selectedSleepInfluences: List<SleepInfluences> = mutableListOf(),
)

data class SleepQualityState(
    val sleepQualityRecords: UiState<List<SleepQualityRecord>> = UiState.Idle,
    val adding: AddingSleepQuality = AddingSleepQuality(),
)

class SleepQualityViewModel(
    private val sleepQualityRepository: SleepQualityRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _sleepQualityState = MutableStateFlow(SleepQualityState())
    val sleepQualityState: StateFlow<SleepQualityState> = _sleepQualityState.asStateFlow()

    fun getSleepQualityRecords() {
        viewModelScope.launch(dispatcher) {
            _sleepQualityState.update { it.copy(sleepQualityRecords = UiState.Loading) }
            try {
                val res = sleepQualityRepository.getSleepQualities()
                _sleepQualityState.update { it.copy(sleepQualityRecords = UiState.Success(res)) }
            } catch (e: Exception) {
                _sleepQualityState.update { it.copy(sleepQualityRecords = UiState.Error(e.message.toString())) }
            }
        }
    }

    fun addSleepQualityRecord() =
        viewModelScope.launch(dispatcher) {
            val adding = sleepQualityState.value.adding
            changeAddingStatus(UiState.Loading)
            val res = sleepQualityRepository.addSleepQuality(
                sleepQuality = SleepQuality.fromMood(adding.mood!!),
                startSleeping = DatetimeHelper.formatTime(
                    adding.startHour,
                    adding.startMinute
                ),
                endSleeping = DatetimeHelper.formatTime(
                    adding.endHour,
                    adding.endMinute
                ),
                sleepInfluences = adding.selectedSleepInfluences
            )
            changeAddingStatus(
                if (res) UiState.Success(true) else UiState.Error(
                    "Fail to add sleep quality record"
                )
            )
        }


    fun addSleepQualityRecord(sleepQualityRecord: SleepQualityRecord) =
        viewModelScope.launch(dispatcher) {
            changeAddingStatus(UiState.Loading)
            val res = sleepQualityRepository.addSleepQuality(
                sleepQuality = sleepQualityRecord.sleepQuality,
                startSleeping = sleepQualityRecord.startSleeping,
                endSleeping = sleepQualityRecord.endSleeping,
                sleepInfluences = sleepQualityRecord.sleepInfluences
            )
            changeAddingStatus(
                if (res) UiState.Success(true) else UiState.Error(
                    "Fail to add sleep quality record"
                )
            )
        }

    fun updateAddingMood(mood: Mood?) {
        _sleepQualityState.update {
            it.copy(
                adding = it.adding.copy(
                    mood = mood
                )
            )
        }
    }

    fun updateSelectedSleepInfluences(sleepInfluences: SleepInfluences) {
        val list = _sleepQualityState.value.adding.selectedSleepInfluences
        _sleepQualityState.update {
            it.copy(
                adding = it.adding.copy(
                    selectedSleepInfluences = if (list.contains(sleepInfluences)) list.minus(
                        sleepInfluences
                    ) else list.plus(sleepInfluences)
                )
            )
        }
    }

    fun updateOnSetStartTimePickerVisibility(value: Boolean) {
        _sleepQualityState.update {
            it.copy(
                adding = it.adding.copy(
                    showStartTimePickerDialog = value
                )
            )
        }
    }

    fun updateOnSetEndTimePickerVisibility(value: Boolean) {
        _sleepQualityState.update {
            it.copy(
                adding = it.adding.copy(
                    showEndTimePickerDialog = value
                )
            )
        }
    }

    fun updateStartTime(hour: Int, minute: Int) {
        _sleepQualityState.update {
            it.copy(
                adding = it.adding.copy(
                    startHour = hour,
                    startMinute = minute
                )
            )
        }
    }

    fun updateEndTime(hour: Int, minute: Int) {
        _sleepQualityState.update {
            it.copy(
                adding = it.adding.copy(
                    endHour = hour,
                    endMinute = minute
                )
            )
        }
    }

    fun resetAddingStatus() {
        _sleepQualityState.update {
            it.copy(
                adding = AddingSleepQuality()
            )
        }
    }

    private fun changeAddingStatus(status: UiState<Boolean>) {
        _sleepQualityState.update { it.copy(adding = it.adding.copy(status = status)) }
    }
}