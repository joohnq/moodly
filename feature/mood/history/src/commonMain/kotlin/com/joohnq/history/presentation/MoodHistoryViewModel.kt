package com.joohnq.history.presentation

import androidx.lifecycle.viewModelScope
import com.joohnq.mood.add.ui.mapper.MoodRecordResourceMapper.toResource
import com.joohnq.mood.api.use_case.DeleteMoodUseCase
import com.joohnq.mood.api.use_case.GetMoodsUseCase
import com.joohnq.ui.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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
        observe()
    }

    private fun observe() {
        updateState { it.copy(isLoading = true) }
        getMoodsUseCase()
            .onEach { items ->
                updateState {
                    it.copy(
                        items = items.toResource(),
                        isLoading = false
                    )
                }
            }.catch { e ->
                emitEffect(MoodHistoryContract.SideEffect.ShowError(e.message.toString()))
            }.launchIn(viewModelScope)
    }

    private fun delete(id: Int) {
        viewModelScope.launch {
            try {
                deleteMoodUseCase(id).getOrThrow()

                updateState {
                    it.copy(
                        items =
                            it.items.filter { mood ->
                                mood.id != id
                            }
                    )
                }
            } catch (e: Exception) {
                emitEffect(MoodHistoryContract.SideEffect.ShowError(e.message.toString()))
            }
        }
    }
}
