package com.joohnq.moodapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.moodapp.entities.SleepQuality
import com.joohnq.moodapp.entities.SleepQualityRecord
import com.joohnq.moodapp.model.repository.SleepQualityRepository
import com.joohnq.moodapp.view.state.UiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class SleepQualityState(
    val items: UiState<List<SleepQualityRecord>> = UiState.Idle,
    val addingStatus: UiState<Boolean> = UiState.Idle
)

class SleepQualityViewModel(
    private val sleepQualityRepository: SleepQualityRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _sleepQualityState = MutableStateFlow(SleepQualityState())
    val sleepQualityState: StateFlow<SleepQualityState> = _sleepQualityState.asStateFlow()

    fun getSleepQualityRecords() {
        viewModelScope.launch(dispatcher) {
            _sleepQualityState.update { it.copy(items = UiState.Loading) }

            try {
                val res = sleepQualityRepository.getSleepQualities()
                _sleepQualityState.update {
                    it.copy(
                        items = UiState.Success(res),
                    )
                }
            } catch (e: Exception) {
                _sleepQualityState.update {
                    it.copy(
                        items = UiState.Error(e.message.toString()),
                    )
                }
            }
        }
    }

    fun addSleepQualityRecord(sleepQuality: SleepQuality) = viewModelScope.launch(dispatcher) {
        _sleepQualityState.update { it.copy(addingStatus = UiState.Loading) }
        val res = sleepQualityRepository.addSleepQuality(
            SleepQualityRecord.init().copy(
                sleepQuality = sleepQuality
            )
        )

        _sleepQualityState.update {
            it.copy(
                addingStatus = if (res) UiState.Success(true) else UiState.Error(
                    "Fail to add sleep quality record"
                )
            )
        }
    }
}