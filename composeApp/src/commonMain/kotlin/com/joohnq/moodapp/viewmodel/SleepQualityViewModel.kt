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
import kotlinx.coroutines.launch

class SleepQualityViewModel(
    private val sleepQualityRepository: SleepQualityRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _items = MutableStateFlow<UiState<List<SleepQualityRecord>>>(UiState.Idle)
    val items: StateFlow<UiState<List<SleepQualityRecord>>> = _items.asStateFlow()

    private val _addingStatus = MutableStateFlow<UiState<Boolean>>(UiState.Idle)
    val addingStatus: StateFlow<UiState<Boolean>> = _addingStatus.asStateFlow()

    fun getSleepQualityRecords() {
        viewModelScope.launch(dispatcher) {
            _items.value = UiState.Loading

            try {
                val res = sleepQualityRepository.getSleepQualities()
                _items.value = UiState.Success(res)
            } catch (e: Exception) {
                _items.value = UiState.Error(e.message.toString())
            }
        }
    }

    fun addSleepQualityRecord(sleepQuality: SleepQuality) = viewModelScope.launch(dispatcher) {
        _addingStatus.value = UiState.Loading
        val res = sleepQualityRepository.addSleepQuality(
            SleepQualityRecord.init().copy(
                sleepQuality = sleepQuality
            )
        )

        _addingStatus.value = if (res) UiState.Success(true) else UiState.Error(
            "Fail to add sleep quality record"
        )
    }

    fun resetAddingStatus() {
        _addingStatus.value = UiState.Idle
    }
}