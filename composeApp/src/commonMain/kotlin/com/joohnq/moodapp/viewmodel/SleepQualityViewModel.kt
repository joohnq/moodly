package com.joohnq.moodapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.moodapp.domain.Mood
import com.joohnq.moodapp.domain.SleepInfluences
import com.joohnq.moodapp.domain.SleepQuality
import com.joohnq.moodapp.domain.SleepQualityRecord
import com.joohnq.moodapp.util.helper.DatetimeManager
import com.joohnq.moodapp.data.repository.SleepQualityRepository
import com.joohnq.moodapp.ui.state.UiState
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

sealed class SleepQualityIntent {
    data object GetSleepQualityRecords : SleepQualityIntent()
    data class AddSleepQualityRecord(val sleepQualityRecord: SleepQualityRecord? = null) :
        SleepQualityIntent()

    data class UpdateAddingMood(val mood: Mood?) : SleepQualityIntent()
    data class UpdateSelectedSleepInfluence(val sleepInfluence: SleepInfluences) :
        SleepQualityIntent()

    data class UpdateShowStartTimePickerDialog(val value: Boolean) :
        SleepQualityIntent()

    data class UpdateShowEndTimePickerDialog(val value: Boolean) :
        SleepQualityIntent()

    data class UpdateStartTime(val hour: Int, val minute: Int) : SleepQualityIntent()
    data class UpdateEndTime(val hour: Int, val minute: Int) : SleepQualityIntent()
    data object ResetAdding : SleepQualityIntent()
}

class SleepQualityViewModel(
    private val sleepQualityRepository: SleepQualityRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _sleepQualityState = MutableStateFlow(SleepQualityState())
    val sleepQualityState: StateFlow<SleepQualityState> = _sleepQualityState.asStateFlow()

    fun onAction(intent: SleepQualityIntent) {
        when (intent) {
            SleepQualityIntent.GetSleepQualityRecords -> getSleepQualityRecords()
            is SleepQualityIntent.AddSleepQualityRecord ->
                if (intent.sleepQualityRecord != null) addSleepQualityRecord(intent.sleepQualityRecord) else addSleepQualityRecord()

            is SleepQualityIntent.UpdateAddingMood -> updateAddingMood(intent.mood)
            is SleepQualityIntent.UpdateSelectedSleepInfluence -> updateSelectedSleepInfluences(
                intent.sleepInfluence
            )

            is SleepQualityIntent.UpdateShowStartTimePickerDialog -> updateShowStartTimePickerDialog(
                intent.value
            )

            is SleepQualityIntent.UpdateShowEndTimePickerDialog -> updateShowEndTimePickerDialog(
                intent.value
            )

            is SleepQualityIntent.UpdateStartTime -> updateStartTime(
                intent.hour,
                intent.minute
            )

            is SleepQualityIntent.UpdateEndTime -> updateEndTime(
                intent.hour,
                intent.minute
            )

            SleepQualityIntent.ResetAdding -> resetAdding()
        }
    }

    private fun getSleepQualityRecords() {
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

    private fun addSleepQualityRecord() =
        viewModelScope.launch(dispatcher) {
            val adding = sleepQualityState.value.adding
            changeAddingStatus(UiState.Loading)
            val res = sleepQualityRepository.addSleepQuality(
                sleepQuality = SleepQuality.fromMood(adding.mood!!),
                startSleeping = DatetimeManager.formatTime(
                    adding.startHour,
                    adding.startMinute
                ),
                endSleeping = DatetimeManager.formatTime(
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


    private fun addSleepQualityRecord(sleepQualityRecord: SleepQualityRecord) =
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

    private fun updateAddingMood(mood: Mood?) {
        _sleepQualityState.update {
            it.copy(
                adding = it.adding.copy(
                    mood = mood
                )
            )
        }
    }

    private fun updateSelectedSleepInfluences(sleepInfluences: SleepInfluences) {
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

    private fun updateShowStartTimePickerDialog(value: Boolean) {
        _sleepQualityState.update {
            it.copy(
                adding = it.adding.copy(
                    showStartTimePickerDialog = value
                )
            )
        }
    }

    private fun updateShowEndTimePickerDialog(value: Boolean) {
        _sleepQualityState.update {
            it.copy(
                adding = it.adding.copy(
                    showEndTimePickerDialog = value
                )
            )
        }
    }

    private fun updateStartTime(hour: Int, minute: Int) {
        _sleepQualityState.update {
            it.copy(
                adding = it.adding.copy(
                    startHour = hour,
                    startMinute = minute
                )
            )
        }
    }

    private fun updateEndTime(hour: Int, minute: Int) {
        _sleepQualityState.update {
            it.copy(
                adding = it.adding.copy(
                    endHour = hour,
                    endMinute = minute
                )
            )
        }
    }

    private fun resetAdding() {
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