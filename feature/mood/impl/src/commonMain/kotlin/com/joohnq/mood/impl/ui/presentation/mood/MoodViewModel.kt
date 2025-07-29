package com.joohnq.mood.impl.ui.presentation.mood

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

class MoodViewModel(
    private val initialState: MoodContract.State = MoodContract.State(),
    private val getMoodsUseCase: GetMoodsUseCase,
    private val deleteMoodUseCase: DeleteMoodUseCase,
    private val addMoodUseCase: AddMoodUseCase,
) : BaseViewModel<MoodContract.State, MoodContract.Intent, MoodContract.SideEffect>(
        initialState = initialState
    ),
    MoodContract.ViewModel {
    override fun onIntent(intent: MoodContract.Intent) {
        when (intent) {
            is MoodContract.Intent.GetAll -> getAll()
            is MoodContract.Intent.Add -> add(intent.record)
            is MoodContract.Intent.Delete -> delete(intent.id)
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
                    emitEffect(MoodContract.SideEffect.StatsAdded)
                }.onFailure {
                    emitEffect(MoodContract.SideEffect.ShowError(it))
                }
        }

    private fun delete(id: Int) =
        viewModelScope.launch {
            val res = deleteMoodUseCase(id).toUiState()
            res
                .onSuccess {
                    emitEffect(MoodContract.SideEffect.StatsDeleted)
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
                    emitEffect(MoodContract.SideEffect.ShowError(it))
                }
        }
}