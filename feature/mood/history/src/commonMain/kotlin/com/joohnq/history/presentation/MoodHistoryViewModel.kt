package com.joohnq.history.presentation

import androidx.lifecycle.viewModelScope
import com.joohnq.mood.add.ui.mapper.MoodRecordResourceMapper.toResource
import com.joohnq.mood.api.use_case.DeleteMoodUseCase
import com.joohnq.mood.api.use_case.GetMoodsUseCase
import com.joohnq.ui.BaseViewModel
import com.joohnq.ui.entity.UiState
import com.joohnq.ui.mapper.ResultMapper.toResultResource
import com.joohnq.ui.mapper.ResultMapper.toUiState
import com.joohnq.ui.mapper.UiStateMapper.getValueOrNull
import kotlinx.coroutines.launch

class MoodHistoryViewModel(
    private val getMoodsUseCase: GetMoodsUseCase,
    private val deleteMoodUseCase: DeleteMoodUseCase,
    initialState: MoodHistoryContract.State = MoodHistoryContract.State(),
) : BaseViewModel<MoodHistoryContract.State, MoodHistoryContract.Intent, MoodHistoryContract.SideEffect>(
        initialState = initialState
    ),
    MoodHistoryContract.ViewModel {
    override fun onIntent(intent: MoodHistoryContract.Intent) {
        when (intent) {
            is MoodHistoryContract.Intent.Delete -> delete(intent.id)
        }
    }

    init {
        getAll()
    }

    private fun getAll() =
        viewModelScope.launch {
            updateState { it.copy(UiState.Loading) }
            try {
                val result =
                    getMoodsUseCase()
                        .toResultResource { it.toResource() }
                        .toUiState()
                updateState { it.copy(result) }
            } catch (e: Exception) {
                emitEffect(MoodHistoryContract.SideEffect.ShowError(e.message.toString()))
            }
        }

    private fun delete(id: Int) =
        viewModelScope.launch {
            try {
                deleteMoodUseCase(id).getOrThrow()

                updateState {
                    it.copy(
                        records =
                            UiState.Success(
                                it.records.getValueOrNull().filter { mood ->
                                    mood.id != id
                                }
                            )
                    )
                }
            } catch (e: Exception) {
                emitEffect(MoodHistoryContract.SideEffect.ShowError(e.message.toString()))
            }
        }
}
