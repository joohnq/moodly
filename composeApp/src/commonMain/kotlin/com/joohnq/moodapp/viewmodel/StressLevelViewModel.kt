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
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class StressLevelState(
    val items: UiState<List<StressLevelRecord>> = UiState.Idle,
    val addingStatus: UiState<Boolean> = UiState.Idle
)

class StressLevelViewModel(
    private val stressLevelRepository: StressLevelRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _stressLevelState = MutableStateFlow(StressLevelState())
    val stressLevelState: StateFlow<StressLevelState> = _stressLevelState.asStateFlow()

    fun getStressLevelRecords() {
        viewModelScope.launch(dispatcher) {
            _stressLevelState.update { it.copy(items = UiState.Loading) }

            try {
                val res = stressLevelRepository.getStressLevels()
                _stressLevelState.update {
                    it.copy(
                        items = UiState.Success(res),
                    )
                }
            } catch (e: Exception) {
                _stressLevelState.update {
                    it.copy(
                        items = UiState.Error(e.message.toString()),
                    )
                }
            }
        }
    }

    fun addStressLevelRecord(stressLevel: StressLevel) = viewModelScope.launch(dispatcher) {
        _stressLevelState.update { it.copy(addingStatus = UiState.Loading) }
        val res = stressLevelRepository.addStressLevel(
            stressLevel.toStressLevelRecord()
        )

        _stressLevelState.update {
            it.copy(
                addingStatus = if (res) UiState.Success(true) else UiState.Error(
                    "Fail to add sleep quality record"
                )
            )
        }
    }
}