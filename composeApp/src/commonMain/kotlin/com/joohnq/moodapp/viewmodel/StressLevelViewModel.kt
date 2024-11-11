package com.joohnq.moodapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.moodapp.entities.StressLevel
import com.joohnq.moodapp.entities.StressLevelRecord
import com.joohnq.moodapp.entities.StressLevelRecord.Companion.toStressLevelRecord
import com.joohnq.moodapp.model.repository.StressLevelRepository
import com.joohnq.moodapp.view.state.UiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StressLevelViewModel(
    private val stressLevelRepository: StressLevelRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _items = MutableStateFlow<UiState<List<StressLevelRecord>>>(UiState.Idle)
    val items: StateFlow<UiState<List<StressLevelRecord>>> = _items.asStateFlow()

    private val _addingStatus = MutableStateFlow<UiState<Boolean>>(UiState.Idle)
    val addingStatus: StateFlow<UiState<Boolean>> = _addingStatus.asStateFlow()

    fun getStressLevelRecords() {
        viewModelScope.launch(dispatcher) {
            _items.value = UiState.Loading

            try {
                val res = stressLevelRepository.getStressLevels()
                _items.value = UiState.Success(res)
            } catch (e: Exception) {
                _items.value = UiState.Error(e.message.toString())
            }
        }
    }

    fun addStressLevelRecord(stressLevel: StressLevel) = viewModelScope.launch(dispatcher) {
        _addingStatus.value = UiState.Loading
        val res = stressLevelRepository.addStressLevel(
            stressLevel.toStressLevelRecord()
        )

        _addingStatus.value = if (res) UiState.Success(true) else UiState.Error(
            "Fail to add sleep quality record"
        )
    }


    fun resetAddingStatus() {
        _addingStatus.value = UiState.Idle
    }
}