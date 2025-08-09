package com.joohnq.gratefulness.add.presentation

import androidx.lifecycle.viewModelScope
import com.joohnq.gratefulness.add.presentation.GratefulnessAddContract.Intent
import com.joohnq.gratefulness.add.presentation.GratefulnessAddContract.SideEffect
import com.joohnq.gratefulness.add.presentation.GratefulnessAddContract.State
import com.joohnq.gratefulness.add.presentation.GratefulnessAddContract.ViewModel
import com.joohnq.gratefulness.api.use_case.AddGratefulnessUseCase
import com.joohnq.ui.BaseViewModel
import kotlinx.coroutines.launch

class GratefulnessAddViewModel(
    private val addGratefulnessUseCase: AddGratefulnessUseCase,
    initialState: State = State(),
) : BaseViewModel<State, Intent, SideEffect>(
        initialState = initialState
    ),
    ViewModel {
    override fun onIntent(intent: Intent) {
        when (intent) {
            is Intent.Add -> add()
            is Intent.ChangeIAmGratefulFor -> {
                updateState {
                    it.copy(
                        item =
                            it.item.copy(
                                iAmGratefulFor = intent.value
                            )
                    )
                }
            }

            is Intent.ChangeIAmThankfulFor -> {
                updateState {
                    it.copy(
                        item =
                            it.item.copy(
                                iAmThankfulFor = intent.value
                            )
                    )
                }
            }

            is Intent.ChangeSmallThingIAppreciate -> {
                updateState {
                    it.copy(
                        item =
                            it.item.copy(
                                smallThingIAppreciate = intent.value
                            )
                    )
                }
            }
        }
    }

    private fun add() {
        viewModelScope.launch {
            try {
                addGratefulnessUseCase(state.value.item).getOrThrow()

                emitEffect(SideEffect.NavigateToGratefulnessOverview)
            } catch (e: Exception) {
                emitEffect(SideEffect.ShowError(e.message.toString()))
            }
        }
    }
}
