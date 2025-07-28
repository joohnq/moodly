package com.joohnq.sleep_quality.impl.ui.presentation.sleep_quality

import androidx.lifecycle.viewModelScope
import com.joohnq.sleep_quality.api.entity.SleepQualityRecord
import com.joohnq.sleep_quality.api.use_case.AddSleepQualityUseCase
import com.joohnq.sleep_quality.api.use_case.DeleteSleepQualityUseCase
import com.joohnq.sleep_quality.api.use_case.GetSleepQualitiesUseCase
import com.joohnq.sleep_quality.impl.ui.mapper.SleepQualityResourceMapper.toResource
import com.joohnq.ui.BaseViewModel
import com.joohnq.ui.entity.UiState
import com.joohnq.ui.mapper.ResultMapper.toResultResource
import com.joohnq.ui.mapper.ResultMapper.toUiState
import com.joohnq.ui.mapper.UiStateMapper.getValueOrEmpty
import com.joohnq.ui.mapper.UiStateMapper.onFailure
import com.joohnq.ui.mapper.UiStateMapper.onSuccess
import kotlinx.coroutines.launch

class SleepQualityViewModel(
    private val addSleepQualityUseCase: AddSleepQualityUseCase,
    private val getSleepQualitiesUseCase: GetSleepQualitiesUseCase,
    private val deleteSleepQualityUseCase: DeleteSleepQualityUseCase,
    initialState: SleepQualityContract.State = SleepQualityContract.State(),
) : BaseViewModel<SleepQualityContract.State, SleepQualityContract.Intent, SleepQualityContract.SideEffect>(
        initialState = initialState
    ),
    SleepQualityContract.ViewModel {
    override fun onIntent(intent: SleepQualityContract.Intent) {
        when (intent) {
            SleepQualityContract.Intent.GetAll -> getAll()
            is SleepQualityContract.Intent.Add ->
                addSleepQualityRecord(intent.record)

            is SleepQualityContract.Intent.Delete -> delete(intent.id)
        }
    }

    private fun getAll() =
        viewModelScope.launch {
            updateState { it.copy(UiState.Loading) }
            val res =
                getSleepQualitiesUseCase()
                    .toResultResource { it.toResource() }
                    .toUiState()
            updateState { it.copy(res) }
        }

    private fun delete(id: Int) =
        viewModelScope.launch {
            val res = deleteSleepQualityUseCase(id).toUiState()
            res
                .onSuccess {
                    emitEffect(SleepQualityContract.SideEffect.Deleted)
                    updateState {
                        it.copy(
                            UiState.Success(
                                state.value.records
                                    .getValueOrEmpty()
                                    .filter { item -> item.id != id }
                            )
                        )
                    }
                }.onFailure {
                    emitEffect(SleepQualityContract.SideEffect.ShowError(it))
                }
        }

    private fun addSleepQualityRecord(sleepQualityRecord: SleepQualityRecord) =
        viewModelScope.launch {
            val res = addSleepQualityUseCase(sleepQualityRecord).toUiState()
            res
                .onSuccess {
                    emitEffect(SleepQualityContract.SideEffect.Added)
                }.onFailure {
                    emitEffect(SleepQualityContract.SideEffect.ShowError(it))
                }
        }
}
