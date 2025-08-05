package com.joohnq.sleep_quality.history.presentation

import androidx.lifecycle.viewModelScope
import com.joohnq.sleep_quality.api.use_case.DeleteSleepQualityUseCase
import com.joohnq.ui.BaseViewModel
import com.joohnq.ui.entity.UiState
import com.joohnq.ui.mapper.UiStateMapper.getValueOrEmpty
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
                emitEffect(SleepQualityHistoryContract.SideEffect.ShowError(e.message.toString()))
            }
        }
}
