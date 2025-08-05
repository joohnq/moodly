package com.joohnq.sleep_quality.history.presentation

import androidx.lifecycle.viewModelScope
import com.joohnq.sleep_quality.api.use_case.DeleteSleepQualityUseCase
import com.joohnq.ui.BaseViewModel
import com.joohnq.ui.entity.UiState
import com.joohnq.ui.mapper.ResultMapper.toUiState
import com.joohnq.ui.mapper.UiStateMapper.getValueOrEmpty
import com.joohnq.ui.mapper.UiStateMapper.onFailure
import com.joohnq.ui.mapper.UiStateMapper.onSuccess
import kotlinx.coroutines.launch

class SleepQualityHistoryViewModel(
    private val deleteSleepQualityUseCase: DeleteSleepQualityUseCase,
    initialState: SleepQualityHistoryContract.State = SleepQualityHistoryContract.State(),
) : BaseViewModel<SleepQualityHistoryContract.State, SleepQualityHistoryContract.Intent, SleepQualityHistoryContract.SideEffect>(
        initialState = initialState
    ),
    SleepQualityHistoryContract.ViewModel {
    override fun onIntent(intent: SleepQualityHistoryContract.Intent) {
        when (intent) {
            is SleepQualityHistoryContract.Intent.Delete -> delete(intent.id)
        }
    }

    private fun delete(id: Int) =
        viewModelScope.launch {
            val res = deleteSleepQualityUseCase(id).toUiState()
            res
                .onSuccess {
                    emitEffect(SleepQualityHistoryContract.SideEffect.Deleted)
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
                    emitEffect(SleepQualityHistoryContract.SideEffect.ShowError(it))
                }
        }
}
