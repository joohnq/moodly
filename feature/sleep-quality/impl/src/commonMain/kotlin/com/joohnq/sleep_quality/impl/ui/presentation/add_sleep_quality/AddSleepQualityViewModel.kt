package com.joohnq.sleep_quality.impl.ui.presentation.add_sleep_quality

import com.joohnq.api.mapper.toggle
import com.joohnq.mood.impl.ui.mapper.toSleepQuality
import com.joohnq.sleep_quality.impl.ui.mapper.toResource
import com.joohnq.ui.BaseViewModel

class AddSleepQualityViewModel(
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
                updateState { AddSleepQualityContract.State() }
        }
    }
}
