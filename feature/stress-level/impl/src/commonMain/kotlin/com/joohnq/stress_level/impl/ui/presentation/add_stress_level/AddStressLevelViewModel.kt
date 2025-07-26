package com.joohnq.stress_level.impl.ui.presentation.add_stress_level

import com.joohnq.api.mapper.toggle
import com.joohnq.stress_level.impl.ui.mapper.fromSliderValueToStressLevelResource
import com.joohnq.ui.BaseViewModel

class AddStressLevelViewModel(
    initialState: AddStressLevelContract.State = AddStressLevelContract.State()
) : BaseViewModel<AddStressLevelContract.State, AddStressLevelContract.Intent, AddStressLevelContract.SideEffect>(
        initialState = initialState
    ),
    AddStressLevelContract.ViewModel {
    override fun onIntent(intent: AddStressLevelContract.Intent) {
        when (intent) {
            is AddStressLevelContract.Intent.UpdateAddingStressors ->
                updateState {
                    it.copy(
                        record =
                            it.record.copy(
                                stressors =
                                    state.value.record.stressors
                                        .toggle(intent.stressor)
                            )
                    )
                }

            is AddStressLevelContract.Intent.UpdateAddingSliderValue ->
                updateState {
                    it.copy(
                        sliderValue = intent.sliderValue,
                        record =
                            it.record.copy(
                                stressLevel = intent.sliderValue.fromSliderValueToStressLevelResource()
                            )
                    )
                }

            is AddStressLevelContract.Intent.ResetState ->
                updateState { AddStressLevelContract.State() }
        }
    }
}
