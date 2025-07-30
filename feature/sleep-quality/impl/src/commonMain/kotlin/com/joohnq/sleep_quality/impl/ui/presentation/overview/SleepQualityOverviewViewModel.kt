package com.joohnq.sleep_quality.impl.ui.presentation.overview

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

class SleepQualityOverviewViewModel(
    private val addSleepQualityUseCase: AddSleepQualityUseCase,
    private val getSleepQualitiesUseCase: GetSleepQualitiesUseCase,
    private val deleteSleepQualityUseCase: DeleteSleepQualityUseCase,
    initialState: SleepQualityOverviewContract.State = SleepQualityOverviewContract.State(),
) : BaseViewModel<SleepQualityOverviewContract.State, SleepQualityOverviewContract.Intent, SleepQualityOverviewContract.SideEffect>(
        initialState = initialState
    ),
    SleepQualityOverviewContract.ViewModel {
    override fun onIntent(intent: SleepQualityOverviewContract.Intent) {
        when (intent) {
            SleepQualityOverviewContract.Intent.GetAll -> getAll()
            is SleepQualityOverviewContract.Intent.Add ->
                add(intent.record)

            is SleepQualityOverviewContract.Intent.Delete -> delete(intent.id)
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
                    emitEffect(SleepQualityOverviewContract.SideEffect.Deleted)
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
                    emitEffect(SleepQualityOverviewContract.SideEffect.ShowError(it))
                }
        }

    private fun add(sleepQualityRecord: SleepQualityRecord) =
        viewModelScope.launch {
            val res = addSleepQualityUseCase(sleepQualityRecord).toUiState()
            res
                .onSuccess {
                    emitEffect(SleepQualityOverviewContract.SideEffect.Added)
                }.onFailure {
                    emitEffect(SleepQualityOverviewContract.SideEffect.ShowError(it))
                }
        }
}
