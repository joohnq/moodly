package com.joohnq.moodapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.moodapp.data.repository.SleepQualityRepository
import com.joohnq.moodapp.domain.SleepQualityRecord
import com.joohnq.moodapp.ui.state.UiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class SleepQualityState(
    val sleepQualityRecords: UiState<List<SleepQualityRecord>> = UiState.Idle,
    val adding: UiState<Boolean> = UiState.Idle,
)

sealed class SleepQualityIntent {
    data object GetSleepQualityRecords : SleepQualityIntent()
    data class AddSleepQualityRecord(val sleepQualityRecord: SleepQualityRecord) :
        SleepQualityIntent()

    data object ResetAddingStatus : SleepQualityIntent()
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
                addSleepQualityRecord(intent.sleepQualityRecord)

            SleepQualityIntent.ResetAddingStatus -> changeAddingStatus(UiState.Idle)
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

    private fun addSleepQualityRecord(sleepQualityRecord: SleepQualityRecord) =
        viewModelScope.launch(dispatcher) {
            changeAddingStatus(UiState.Loading)
            val res = sleepQualityRepository.addSleepQuality(sleepQualityRecord)
            changeAddingStatus(
                if (res) UiState.Success(true) else UiState.Error(
                    "Fail to add sleep quality record"
                )
            )
        }

    private fun changeAddingStatus(status: UiState<Boolean>) {
        _sleepQualityState.update { it.copy(adding = status) }
    }
}