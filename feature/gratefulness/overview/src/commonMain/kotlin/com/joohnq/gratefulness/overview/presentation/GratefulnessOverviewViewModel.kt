package com.joohnq.gratefulness.overview.presentation

import androidx.lifecycle.viewModelScope
import com.joohnq.gratefulness.api.use_case.DeleteGratefulnessUseCase
import com.joohnq.gratefulness.api.use_case.GetGratefulnessUseCase
import com.joohnq.gratefulness.overview.presentation.GratefulnessOverviewContract.Intent
import com.joohnq.gratefulness.overview.presentation.GratefulnessOverviewContract.SideEffect
import com.joohnq.gratefulness.overview.presentation.GratefulnessOverviewContract.State
import com.joohnq.gratefulness.overview.presentation.GratefulnessOverviewContract.ViewModel
import com.joohnq.ui.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class GratefulnessOverviewViewModel(
    private val getGratefulnessUseCase: GetGratefulnessUseCase,
    private val deleteGratefulnessUseCase: DeleteGratefulnessUseCase,
    initialState: State = State(),
) : BaseViewModel<State, Intent, SideEffect>(
        initialState = initialState
    ),
    ViewModel {
    override fun onIntent(intent: Intent) {
        when (intent) {
            is Intent.Delete ->
                delete(intent.id)

            is Intent.ChangeSelectedDate -> {
                updateState {
                    it.copy(
                        selectedDate = intent.date
                    )
                }
                getSelectedGratefulness()
            }
        }
    }

    init {
        observe()
    }

    private fun observe() {
        updateState { it.copy(isLoading = true) }
        getGratefulnessUseCase()
            .onEach { items ->
                updateState {
                    it.copy(
                        items = items,
                        isLoading = false
                    )
                }
                getSelectedGratefulness()
            }.catch { e ->
                emitEffect(SideEffect.ShowError(e.message.toString()))
            }.launchIn(viewModelScope)
    }

    private fun delete(id: Int) {
        viewModelScope.launch {
            try {
                deleteGratefulnessUseCase(id).getOrThrow()

                updateState {
                    it.copy(
                        items =
                            state.value.items
                                .filter { item -> item.id != id }
                    )
                }
            } catch (e: Exception) {
                emitEffect(SideEffect.ShowError(e.message.toString()))
            }
        }
    }

    private fun getSelectedGratefulness() {
        updateState {
            it.copy(
                selectedGratefulness =
                    state.value.items
                        .lastOrNull { item ->
                            item.createdAt.date == state.value.selectedDate
                        }
            )
        }
    }
}
