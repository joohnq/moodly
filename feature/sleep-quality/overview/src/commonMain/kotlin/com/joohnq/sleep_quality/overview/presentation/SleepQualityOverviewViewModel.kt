package com.joohnq.sleep_quality.overview.presentation

import androidx.lifecycle.viewModelScope
import com.joohnq.sleep_quality.api.use_case.DeleteSleepQualityUseCase
import com.joohnq.sleep_quality.api.use_case.GetSleepQualitiesUseCase
import com.joohnq.sleep_quality.impl.ui.mapper.SleepQualityResourceMapper.toResource
import com.joohnq.ui.BaseViewModel
import com.joohnq.ui.entity.UiState
import com.joohnq.ui.mapper.ResultMapper.toResultResource
import com.joohnq.ui.mapper.ResultMapper.toUiState
import com.joohnq.ui.mapper.UiStateMapper.getValueOrEmpty
import kotlinx.coroutines.launch

class SleepQualityOverviewViewModel(
    private val getSleepQualitiesUseCase: GetSleepQualitiesUseCase,
    private val deleteSleepQualityUseCase: DeleteSleepQualityUseCase,
    initialState: SleepQualityOverviewContract.State = SleepQualityOverviewContract.State(),
) : BaseViewModel<SleepQualityOverviewContract.State, SleepQualityOverviewContract.Intent, SleepQualityOverviewContract.SideEffect>(
        initialState = initialState
    ),
    SleepQualityOverviewContract.ViewModel {
    override fun onIntent(intent: SleepQualityOverviewContract.Intent) {
        when (intent) {
            is SleepQualityOverviewContract.Intent.Delete -> delete(intent.id)
        }
    }

    init {
        getAll()
    }

    private fun getAll() =
        viewModelScope.launch {
            updateState { it.copy(UiState.Loading) }
            try {
                val res =
                    getSleepQualitiesUseCase()
                        .toResultResource { it.toResource() }
                        .toUiState()
                updateState { it.copy(res) }
            } catch (e: Exception) {
                emitEffect(SleepQualityOverviewContract.SideEffect.ShowError(e.message.toString()))
            }
        }

    private fun delete(id: Int) =
        viewModelScope.launch {
            try {
                deleteSleepQualityUseCase(id).getOrThrow()

                updateState {
                    it.copy(
                        UiState.Success(
                            state.value.records
                                .getValueOrEmpty()
                                .filter { item -> item.id != id }
                        )
                    )
                }
            } catch (e: Exception) {
                emitEffect(SleepQualityOverviewContract.SideEffect.ShowError(e.message.toString()))
            }
        }
}
