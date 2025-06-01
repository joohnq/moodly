package com.joohnq.sleep_quality.ui.presentation.sleep_quality.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.domain.entity.UiState
import com.joohnq.domain.mapper.getValueOrEmpty
import com.joohnq.domain.mapper.toResultResource
import com.joohnq.domain.mapper.toUiState
import com.joohnq.sleep_quality.domain.entity.SleepQualityRecord
import com.joohnq.sleep_quality.domain.use_case.AddSleepQualityUseCase
import com.joohnq.sleep_quality.domain.use_case.DeleteSleepQualityUseCase
import com.joohnq.sleep_quality.domain.use_case.GetSleepQualitiesUseCase
import com.joohnq.sleep_quality.ui.resource.SleepQualityRecordResource
import com.joohnq.sleep_quality.ui.resource.mapper.toResource
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SleepQualityViewModel(
    private val addSleepQualityUseCase: AddSleepQualityUseCase,
    private val getSleepQualitiesUseCase: GetSleepQualitiesUseCase,
    private val deleteSleepQualityUseCase: DeleteSleepQualityUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(SleepQualityContract.State())
    val state: StateFlow<SleepQualityContract.State> = _state.asStateFlow()

    private val _sideEffect = Channel<SleepQualityContract.SideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    fun onIntent(intent: SleepQualityContract.Intent) {
        when (intent) {
            SleepQualityContract.Intent.GetAll -> getSleepQualityRecords()
            is SleepQualityContract.Intent.Add ->
                add(intent.record)

            is SleepQualityContract.Intent.Delete -> delete(intent.id)
        }
    }

    private fun delete(id: Int) =
        viewModelScope.launch {
            deleteSleepQualityUseCase(id)
                .onSuccess {
                    _sideEffect.send(SleepQualityContract.SideEffect.SleepQualityDeleted)
                    changeRecordsStatus(
                        UiState.Success(
                            state.value.records.getValueOrEmpty()
                                .filter { item -> item.id != id }
                        )
                    )
                }.onFailure {
                    _sideEffect.send(SleepQualityContract.SideEffect.ShowError(it))
                }
        }

    private fun getSleepQualityRecords() =
        viewModelScope.launch {
            changeRecordsStatus(UiState.Loading)
            val res = getSleepQualitiesUseCase()
                .toResultResource { it.toResource() }
                .toUiState()
            changeRecordsStatus(res)
        }

    private fun add(sleepQualityRecord: SleepQualityRecord) =
        viewModelScope.launch {
            addSleepQualityUseCase(sleepQualityRecord)
                .onSuccess {
                    _sideEffect.send(SleepQualityContract.SideEffect.SleepQualityAdded)
                }.onFailure {
                    _sideEffect.send(SleepQualityContract.SideEffect.ShowError(it))
                }
        }

    private fun changeRecordsStatus(status: UiState<List<SleepQualityRecordResource>>) {
        _state.update { it.copy(records = status) }
    }
}