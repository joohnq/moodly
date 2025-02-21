package com.joohnq.mood.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.domain.entity.UiState
import com.joohnq.domain.mapper.getValueOrEmpty
import com.joohnq.domain.mapper.onFailure
import com.joohnq.domain.mapper.onSuccess
import com.joohnq.domain.mapper.toResultResource
import com.joohnq.domain.mapper.toUiState
import com.joohnq.mood.domain.entity.MoodRecord
import com.joohnq.mood.domain.use_case.AddMoodUseCase
import com.joohnq.mood.domain.use_case.DeleteMoodUseCase
import com.joohnq.mood.domain.use_case.GetMoodsUseCase
import com.joohnq.mood.ui.mapper.toResource
import com.joohnq.mood.ui.resource.MoodRecordResource
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MoodViewModel(
    private val getMoodsUseCase: GetMoodsUseCase,
    private val deleteMoodUseCase: DeleteMoodUseCase,
    private val addMoodUseCase: AddMoodUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(MoodState())
    val state: StateFlow<MoodState> = _state.asStateFlow()

    private val _sideEffect = Channel<MoodSideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    fun onAction(intent: MoodIntent) {
        when (intent) {
            is MoodIntent.GetAll -> get()
            is MoodIntent.Add -> add(intent.record)
            is MoodIntent.Delete -> delete(intent.id)
        }
    }

    private fun get() =
        viewModelScope.launch {
            changeRecordsStatus(UiState.Loading)
            val res = getMoodsUseCase()
                .toResultResource { it.toResource() }
                .toUiState()
            changeRecordsStatus(res)
        }

    private fun add(record: MoodRecord) = viewModelScope.launch {
        val res = addMoodUseCase(record).toUiState()
        res.onSuccess {
            _sideEffect.send(MoodSideEffect.StatsAdded)
        }.onFailure {
            _sideEffect.send(MoodSideEffect.ShowError(it))
        }
    }

    private fun delete(id: Int) = viewModelScope.launch {
        val res = deleteMoodUseCase(id).toUiState()
        res.onSuccess {
            _sideEffect.send(MoodSideEffect.StatsDeleted)
            changeRecordsStatus(
                UiState.Success(
                    state.value.records.getValueOrEmpty()
                        .filter { item -> item.id != id }
                )
            )
        }.onFailure {
            _sideEffect.send(MoodSideEffect.ShowError(it))
        }
    }

    private fun changeRecordsStatus(status: UiState<List<MoodRecordResource>>) {
        _state.update { it.copy(records = status) }
    }
}