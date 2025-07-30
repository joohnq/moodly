package com.joohnq.overview.presentation

import androidx.lifecycle.viewModelScope
import com.joohnq.mood.api.entity.MoodRecord
import com.joohnq.mood.api.use_case.AddMoodUseCase
import com.joohnq.mood.api.use_case.DeleteMoodUseCase
import com.joohnq.mood.api.use_case.GetMoodsUseCase
import com.joohnq.mood.impl.ui.mapper.MoodRecordResourceMapper.toResource
import com.joohnq.ui.BaseViewModel
import com.joohnq.ui.entity.UiState
import com.joohnq.ui.mapper.ResultMapper.toResultResource
import com.joohnq.ui.mapper.ResultMapper.toUiState
import com.joohnq.ui.mapper.UiStateMapper.getValueOrEmpty
import com.joohnq.ui.mapper.UiStateMapper.onFailure
import com.joohnq.ui.mapper.UiStateMapper.onSuccess
import kotlinx.coroutines.launch

class MoodOverviewViewModel(
    private val initialState: MoodOverviewContract.State = MoodOverviewContract.State(),
    private val getMoodsUseCase: GetMoodsUseCase,
    private val deleteMoodUseCase: DeleteMoodUseCase,
    private val addMoodUseCase: AddMoodUseCase,
) : BaseViewModel<MoodOverviewContract.State, MoodOverviewContract.Intent, MoodOverviewContract.SideEffect>(
        initialState = initialState
    ),
    MoodOverviewContract.ViewModel {
    override fun onIntent(intent: MoodOverviewContract.Intent) {
        when (intent) {
            is MoodOverviewContract.Intent.GetAll -> getAll()
            is MoodOverviewContract.Intent.Add -> add(intent.record)
            is MoodOverviewContract.Intent.Delete -> delete(intent.id)
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

    private fun add(record: MoodRecord) =
        viewModelScope.launch {
            val res = addMoodUseCase(record).toUiState()
            res
                .onSuccess {
                    emitEffect(MoodOverviewContract.SideEffect.Added)
                }.onFailure {
                    emitEffect(MoodOverviewContract.SideEffect.ShowError(it))
                }
        }

    private fun delete(id: Int) =
        viewModelScope.launch {
            val res = deleteMoodUseCase(id).toUiState()
            res
                .onSuccess {
                    emitEffect(MoodOverviewContract.SideEffect.StatsDeleted)
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
                    emitEffect(MoodOverviewContract.SideEffect.ShowError(it))
                }
        }
}
