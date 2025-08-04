package com.joohnq.stress_level.impl.ui.presentation.add

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

            is AddStressLevelContract.Intent.UpdateStressLevel ->
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
                resetState()

            AddStressLevelContract.Intent.Add -> add()
        }
    }

    private fun add() {
        viewModelScope.launch {
            if (state.value.record.stressLevel != StressLevelResource.One) {
                emitEffect(AddStressLevelContract.SideEffect.NavigateToStressStressors)
                return@launch
            }
            try {
                addStressLevelUseCase(state.value.record.toDomain()).getOrThrow()

                emitEffect(AddStressLevelContract.SideEffect.OnGoBack)
            } catch (e: Exception) {
                emitEffect(AddStressLevelContract.SideEffect.ShowError(e.message.toString()))
            }
        }
    }
}
