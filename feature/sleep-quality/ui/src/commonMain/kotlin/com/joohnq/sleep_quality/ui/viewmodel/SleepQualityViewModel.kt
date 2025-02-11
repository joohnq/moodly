package com.joohnq.sleep_quality.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.domain.entity.UiState
import com.joohnq.domain.mapper.onFailure
import com.joohnq.domain.mapper.onSuccess
import com.joohnq.domain.mapper.toResultResource
import com.joohnq.domain.mapper.toUiState
import com.joohnq.sleep_quality.domain.entity.SleepQualityRecord
import com.joohnq.sleep_quality.domain.use_case.AddSleepQualityUseCase
import com.joohnq.sleep_quality.domain.use_case.GetSleepQualitiesUseCase
import com.joohnq.sleep_quality.ui.mapper.toResource
import com.joohnq.sleep_quality.ui.resource.SleepQualityRecordResource
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SleepQualityViewModel(
    private val addSleepQualityUseCase: AddSleepQualityUseCase,
    private val getSleepQualitiesUseCase: GetSleepQualitiesUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(SleepQualityState())
    val state: StateFlow<SleepQualityState> = _state.asStateFlow()

    private val _sideEffect = Channel<SleepQualitySideEffect>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    fun onAction(intent: SleepQualityIntent) {
        when (intent) {
            SleepQualityIntent.GetSleepQualityRecords -> getSleepQualityRecords()
            is SleepQualityIntent.AddSleepQualityRecord ->
                addSleepQualityRecord(intent.sleepQualityRecord)
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

    private fun addSleepQualityRecord(sleepQualityRecord: SleepQualityRecord) =
        viewModelScope.launch {
            val res = addSleepQualityUseCase(sleepQualityRecord).toUiState()
            res.onSuccess {
                _sideEffect.send(SleepQualitySideEffect.SleepQualityAdded)
            }.onFailure {
                _sideEffect.send(SleepQualitySideEffect.ShowError(it))
            }
        }

    private fun changeRecordsStatus(status: UiState<List<SleepQualityRecordResource>>) {
        _state.update { it.copy(sleepQualityRecords = status) }
    }
}