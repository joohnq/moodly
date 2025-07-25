package com.joohnq.mood.impl.ui.presentation.mood

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.ui.entity.UiState
import com.joohnq.ui.mapper.getValueOrEmpty
import com.joohnq.ui.mapper.onFailure
import com.joohnq.ui.mapper.onSuccess
import com.joohnq.ui.mapper.toResultResource
import com.joohnq.ui.mapper.toUiState
import com.joohnq.mood.api.entity.MoodRecord
import com.joohnq.mood.api.use_case.AddMoodUseCase
import com.joohnq.mood.api.use_case.DeleteMoodUseCase
import com.joohnq.mood.api.use_case.GetMoodsUseCase
import com.joohnq.mood.impl.ui.mapper.toResource
import com.joohnq.mood.impl.ui.resource.MoodRecordResource
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
    private val _state = MutableStateFlow(MoodContract.State())
    val state: StateFlow<MoodContract.State> = _state.asStateFlow()

    private val _sideEffect = Channel<MoodContract.SideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    fun onAction(intent: MoodContract.Intent) {
        when (intent) {
            is MoodContract.Intent.GetAll -> get()
            is MoodContract.Intent.Add -> add(intent.record)
            is MoodContract.Intent.Delete -> delete(intent.id)
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
            _sideEffect.send(MoodContract.SideEffect.StatsAdded)
        }.onFailure {
            _sideEffect.send(MoodContract.SideEffect.ShowError(it))
        }
    }

    private fun delete(id: Int) = viewModelScope.launch {
        val res = deleteMoodUseCase(id).toUiState()
        res.onSuccess {
            _sideEffect.send(MoodContract.SideEffect.StatsDeleted)
            changeRecordsStatus(
                UiState.Success(
                    state.value.records.getValueOrEmpty()
                        .filter { item -> item.id != id }
                )
            )
        }.onFailure {
            _sideEffect.send(MoodContract.SideEffect.ShowError(it))
        }
    }

    private fun changeRecordsStatus(status: UiState<List<MoodRecordResource>>) {
        _state.update { it.copy(records = status) }
    }
}