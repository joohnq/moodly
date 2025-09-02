package com.joohnq.overview.presentation

import androidx.lifecycle.viewModelScope
import com.joohnq.mood.add.ui.mapper.MoodRecordResourceMapper.toResource
import com.joohnq.mood.api.use_case.DeleteMoodUseCase
import com.joohnq.mood.api.use_case.GetMoodsUseCase
import com.joohnq.ui.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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
        observe()
    }

    private fun observe() {
        updateState { it.copy(isLoading = true) }
        viewModelScope.launch {
            getMoodsUseCase()
                .onEach { items ->
                    updateState {
                        it.copy(
                            items = items.toResource(),
                            isLoading = false
                        )
                    }
                }.catch { e ->
                    emitEffect(MoodOverviewContract.SideEffect.ShowError(e.message.toString()))
                }.launchIn(viewModelScope)
        }
    }

    private fun delete(id: Long) {
        viewModelScope.launch {
            try {
                deleteMoodUseCase(id).getOrThrow()

                updateState {
                    it.copy(
                        items =
                            state.value.items
                                .filter { item -> item.id != id }
                    )
                }
            } catch (e: Exception) {
                emitEffect(MoodOverviewContract.SideEffect.ShowError(e.message.toString()))
            }
        }
    }
}
