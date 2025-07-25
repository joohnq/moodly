package com.joohnq.sleep_quality.impl.ui.presentation.sleep_quality

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joohnq.sleep_quality.api.entity.SleepQualityRecord
import com.joohnq.sleep_quality.api.use_case.AddSleepQualityUseCase
import com.joohnq.sleep_quality.api.use_case.DeleteSleepQualityUseCase
import com.joohnq.sleep_quality.api.use_case.GetSleepQualitiesUseCase
import com.joohnq.sleep_quality.impl.ui.mapper.toResource
import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityRecordResource
import com.joohnq.ui.entity.UiState
import com.joohnq.ui.mapper.getValueOrEmpty
import com.joohnq.ui.mapper.onFailure
import com.joohnq.ui.mapper.onSuccess
import com.joohnq.ui.mapper.toResultResource
import com.joohnq.ui.mapper.toUiState
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

    private val _sideEffect = Channel<SleepQualityContract.SideEffect>(Channel.Factory.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    fun onAction(intent: SleepQualityContract.Intent) {
        when (intent) {
            SleepQualityContract.Intent.GetAll -> getSleepQualityRecords()
            is SleepQualityContract.Intent.Add ->
                addSleepQualityRecord(intent.record)

            is SleepQualityContract.Intent.Delete -> delete(intent.id)
        }
    }

    private fun delete(id: Int) =
        viewModelScope.launch {
            val res = deleteSleepQualityUseCase(id).toUiState()
            res.onSuccess {
                _sideEffect.send(SleepQualityContract.SideEffect.Deleted)
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

    private fun addSleepQualityRecord(sleepQualityRecord: SleepQualityRecord) =
        viewModelScope.launch {
            val res = addSleepQualityUseCase(sleepQualityRecord).toUiState()
            res.onSuccess {
                _sideEffect.send(SleepQualityContract.SideEffect.SleepQualityAdded)
            }.onFailure {
                _sideEffect.send(SleepQualityContract.SideEffect.ShowError(it))
            }
        }

    private fun changeRecordsStatus(status: UiState<List<SleepQualityRecordResource>>) {
        _state.update { it.copy(records = status) }
    }
}