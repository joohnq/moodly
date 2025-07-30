package com.joohnq.sleep_quality.impl.ui.presentation.add

import androidx.lifecycle.viewModelScope
import com.joohnq.api.mapper.ListMapper.toggle
import com.joohnq.mood.impl.ui.mapper.MoodResourceMapper.toSleepQuality
import com.joohnq.sleep_quality.api.use_case.AddSleepQualityUseCase
import com.joohnq.sleep_quality.impl.ui.mapper.SleepQualityResourceMapper.toDomain
import com.joohnq.sleep_quality.impl.ui.mapper.SleepQualityResourceMapper.toResource
import com.joohnq.ui.BaseViewModel
import com.joohnq.ui.mapper.ResultMapper.toUiState
import com.joohnq.ui.mapper.UiStateMapper.onFailure
import com.joohnq.ui.mapper.UiStateMapper.onSuccess
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
            is AddSleepQualityContract.Intent.UpdateMood ->
                updateState {
                    it.copy(
                        record =
                            it.record.copy(
                                sleepQuality = intent.mood.toSleepQuality().toResource()
                            )
                    )
                }

            is AddSleepQualityContract.Intent.UpdateSelectedSleepInfluence ->
                updateState {
                    val influences =
                        state.value.record.sleepInfluences
                            .toggle(intent.sleepInfluence)
                    it.copy(
                        record = it.record.copy(sleepInfluences = influences)
                    )
                }

            is AddSleepQualityContract.Intent.UpdateShowStartTimePickerDialog ->
                updateState { it.copy(showStartTimePickerDialog = intent.value) }

            is AddSleepQualityContract.Intent.UpdateShowEndTimePickerDialog ->
                updateState { it.copy(showEndTimePickerDialog = intent.value) }

            is AddSleepQualityContract.Intent.UpdateStartTime ->
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

            is AddSleepQualityContract.Intent.UpdateEndTime ->
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

    private fun add() =
        viewModelScope.launch {
            val res = addSleepQualityUseCase(state.value.record.toDomain()).toUiState()
            res
                .onSuccess {
                    emitEffect(AddSleepQualityContract.SideEffect.OnNavigateToNext)
                }.onFailure {
                    emitEffect(AddSleepQualityContract.SideEffect.ShowError(it))
                }
        }
}
