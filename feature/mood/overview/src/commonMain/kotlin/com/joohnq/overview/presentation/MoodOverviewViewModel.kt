package com.joohnq.overview.presentation

import androidx.lifecycle.viewModelScope
import com.joohnq.mood.add.ui.mapper.MoodRecordResourceMapper.toResource
import com.joohnq.mood.api.use_case.DeleteMoodUseCase
import com.joohnq.mood.api.use_case.GetMoodsUseCase
import com.joohnq.ui.BaseViewModel
import com.joohnq.ui.entity.UiState
import com.joohnq.ui.mapper.ResultMapper.toResultResource
import com.joohnq.ui.mapper.ResultMapper.toUiState
import com.joohnq.ui.mapper.UiStateMapper.getValueOrEmpty
import kotlinx.coroutines.launch

class MoodOverviewViewModel(
    private val getMoodsUseCase: GetMoodsUseCase,
    private val deleteMoodUseCase: DeleteMoodUseCase,
    initialState: MoodOverviewContract.State = MoodOverviewContract.State(),
) : BaseViewModel<MoodOverviewContract.State, MoodOverviewContract.Intent, MoodOverviewContract.SideEffect>(
        initialState = initialState
    ),
    MoodOverviewContract.ViewModel {
    override fun onIntent(intent: MoodOverviewContract.Intent) {
        when (intent) {
            is MoodOverviewContract.Intent.Delete -> delete(intent.id)
        }
    }

    init {
        getAll()
    }

    private fun getAll() {
        viewModelScope.launch {
            updateState { it.copy(UiState.Loading) }
            try {
                val result =
                    getMoodsUseCase()
                        .toResultResource { it.toResource() }
                        .toUiState()

                updateState { it.copy(result) }
            } catch (e: Exception) {
                emitEffect(MoodOverviewContract.SideEffect.ShowError(e.message.toString()))
            }
        }
    }

    private fun delete(id: Int) {
        viewModelScope.launch {
            try {
                deleteMoodUseCase(id).getOrThrow()

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
                emitEffect(MoodOverviewContract.SideEffect.ShowError(e.message.toString()))
            }
        }
    }
}
