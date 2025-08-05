package com.joohnq.sleep_quality.add.presentation

import androidx.lifecycle.viewModelScope
import com.joohnq.api.mapper.ListMapper.toggle
import com.joohnq.mood.add.ui.mapper.MoodResourceMapper.toSleepQuality
import com.joohnq.sleep_quality.api.use_case.AddSleepQualityUseCase
import com.joohnq.sleep_quality.impl.ui.mapper.SleepQualityResourceMapper.toDomain
import com.joohnq.sleep_quality.impl.ui.mapper.SleepQualityResourceMapper.toResource
import com.joohnq.ui.BaseViewModel
import kotlinx.coroutines.launch

class AddSleepQualityViewModel(
    private val addSleepQualityUseCase: AddSleepQualityUseCase,
    initialState: AddSleepQualityContract.State = AddSleepQualityContract.State(),
) : BaseViewModel<AddSleepQualityContract.State, AddSleepQualityContract.Intent, AddSleepQualityContract.SideEffect>(
        initialState = initialState
    ),
    AddSleepQualityContract.ViewModel {
    override fun onIntent(intent: AddSleepQualityContract.Intent) {
        when (intent) {
            is AddSleepQualityContract.Intent.ChangeMood ->
                updateState {
                    it.copy(
                        record =
                            it.record.copy(
                                sleepQuality = intent.mood.toSleepQuality().toResource()
                            )
                    )
                }

            is AddSleepQualityContract.Intent.ChangeSelectedSleepInfluence ->
                updateState {
                    val influences =
                        state.value.record.sleepInfluences
                            .toggle(intent.sleepInfluence)
                    it.copy(
                        record = it.record.copy(sleepInfluences = influences)
                    )
                }

            is AddSleepQualityContract.Intent.ChangeShowStartTimePickerDialog ->
                updateState { it.copy(showStartTimePickerDialog = intent.value) }

            is AddSleepQualityContract.Intent.ChangeShowEndTimePickerDialog ->
                updateState { it.copy(showEndTimePickerDialog = intent.value) }

            is AddSleepQualityContract.Intent.ChangeStartTime ->
                updateState {
                    it.copy(
                        record =
                            it.record.copy(
                                startSleeping =
                                    it.record.startSleeping.copy(
                                        intent.hour,
                                        intent.minute
                                    )
                            )
                    )
                }

            is AddSleepQualityContract.Intent.ChangeEndTime ->
                updateState {
                    it.copy(
                        record =
                            it.record.copy(
                                endSleeping =
                                    it.record.endSleeping.copy(
                                        intent.hour,
                                        intent.minute
                                    )
                            )
                    )
                }

            AddSleepQualityContract.Intent.ResetState ->
                resetState()

            AddSleepQualityContract.Intent.Add -> add()
        }
    }

    private fun add() {
        viewModelScope.launch {
            try {
                addSleepQualityUseCase(state.value.record.toDomain()).getOrThrow()
            } catch (e: Exception) {
                emitEffect(AddSleepQualityContract.SideEffect.ShowError(e.message.toString()))
            }
        }
    }
}