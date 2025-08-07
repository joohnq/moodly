package com.joohnq.stress_level.add.presentation

import androidx.lifecycle.viewModelScope
import com.joohnq.api.mapper.ListMapper.toggle
import com.joohnq.stress_level.api.use_case.AddStressLevelUseCase
import com.joohnq.stress_level.impl.ui.mapper.StressLevelRecordResourceMapper.toDomain
import com.joohnq.stress_level.impl.ui.mapper.StressLevelResourceMapper.fromSliderValueToStressLevelResource
import com.joohnq.stress_level.impl.ui.resource.StressLevelResource
import com.joohnq.ui.BaseViewModel
import kotlinx.coroutines.launch

class AddStressLevelViewModel(
    private val addStressLevelUseCase: AddStressLevelUseCase,
    initialState: AddStressLevelContract.State = AddStressLevelContract.State(),
) : BaseViewModel<AddStressLevelContract.State, AddStressLevelContract.Intent, AddStressLevelContract.SideEffect>(
        initialState = initialState
    ),
    AddStressLevelContract.ViewModel {
    override fun onIntent(intent: AddStressLevelContract.Intent) {
        when (intent) {
            is AddStressLevelContract.Intent.ChangeAddingStressors ->
                updateState {
                    it.copy(
                        item =
                            it.item.copy(
                                stressors =
                                    state.value.item.stressors
                                        .toggle(intent.stressor)
                            )
                    )
                }

            is AddStressLevelContract.Intent.ChangeStressLevel ->
                updateState {
                    it.copy(
                        sliderValue = intent.sliderValue,
                        item =
                            it.item.copy(
                                stressLevel = intent.sliderValue.fromSliderValueToStressLevelResource()
                            )
                    )
                }

            is AddStressLevelContract.Intent.ResetState ->
                resetState()

            AddStressLevelContract.Intent.Add -> add()
        }
    }

    private fun add() {
        viewModelScope.launch {
            if (state.value.item.stressLevel != StressLevelResource.One &&
                state.value.item.stressors
                    .isEmpty()
            ) {
                emitEffect(AddStressLevelContract.SideEffect.NavigateToStressStressors)
                return@launch
            }

            try {
                addStressLevelUseCase(state.value.item.toDomain()).getOrThrow()

                emitEffect(AddStressLevelContract.SideEffect.GoBack)

                resetState()
            } catch (e: Exception) {
                emitEffect(AddStressLevelContract.SideEffect.ShowError(e.message.toString()))
            }
        }
    }
}
