package com.joohnq.history.presentation

import androidx.lifecycle.viewModelScope
import com.joohnq.mood.api.use_case.DeleteMoodUseCase
import com.joohnq.mood.api.use_case.GetMoodsUseCase
import com.joohnq.mood.impl.ui.mapper.MoodRecordResourceMapper.toResource
import com.joohnq.ui.BaseViewModel
import com.joohnq.ui.entity.UiState
import com.joohnq.ui.mapper.ResultMapper.toResultResource
import com.joohnq.ui.mapper.ResultMapper.toUiState
import com.joohnq.ui.mapper.UiStateMapper.onFailure
import com.joohnq.ui.mapper.UiStateMapper.onSuccess
import kotlinx.coroutines.launch

class MoodHistoryViewModel(
    private val initialState: MoodHistoryContract.State = MoodHistoryContract.State(),
    private val getMoodsUseCase: GetMoodsUseCase,
    private val deleteMoodUseCase: DeleteMoodUseCase,
) : BaseViewModel<MoodHistoryContract.State, MoodHistoryContract.Intent, MoodHistoryContract.SideEffect>(
        initialState = initialState
    ),
    MoodHistoryContract.ViewModel {
    override fun onIntent(intent: MoodHistoryContract.Intent) {
        when (intent) {
            is MoodHistoryContract.Intent.GetAll -> getAll()
            is MoodHistoryContract.Intent.Delete -> delete(intent.id)
        }
    }

    private fun getAll() =
        viewModelScope.launch {
            updateState { it.copy(UiState.Loading) }
            val res =
                getMoodsUseCase()
                    .toResultResource { it.toResource() }
                    .toUiState()
            updateState { it.copy(res) }
        }

    private fun delete(id: Int) =
        viewModelScope.launch {
            val res = deleteMoodUseCase(id).toUiState()
            res
                .onSuccess {
                    emitEffect(MoodHistoryContract.SideEffect.StatsDeleted)
                }.onFailure {
                    emitEffect(MoodHistoryContract.SideEffect.ShowError(it))
                }
        }
}
