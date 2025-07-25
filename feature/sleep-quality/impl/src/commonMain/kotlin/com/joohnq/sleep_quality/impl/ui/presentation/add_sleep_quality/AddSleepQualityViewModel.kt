package com.joohnq.sleep_quality.impl.ui.presentation.add_sleep_quality

import androidx.lifecycle.ViewModel
import com.joohnq.api.mapper.toggle
import com.joohnq.mood.impl.ui.mapper.toSleepQuality
import com.joohnq.sleep_quality.impl.ui.mapper.toResource
import com.joohnq.sleep_quality.impl.ui.resource.SleepInfluencesResource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AddSleepQualityViewModel : ViewModel() {
    private val _state = MutableStateFlow(AddSleepQualityContract.State())
    val state: StateFlow<AddSleepQualityContract.State> =
        _state.asStateFlow()

    fun onAction(intent: AddSleepQualityContract.Intent) {
        when (intent) {
            is AddSleepQualityContract.Intent.UpdateMood ->
                _state.update {
                    it.copy(
                        record = it.record.copy(
                            sleepQuality = intent.mood.toSleepQuality().toResource()
                        ),
                    )
                }

            is AddSleepQualityContract.Intent.UpdateSelectedSleepInfluence ->
                _state.update {
                    val influences =
                        state.value.record.sleepInfluences.toggle(intent.sleepInfluence)
                    it.copy(
                        record = it.record.copy(sleepInfluences = influences)
                    )
                }

            is AddSleepQualityContract.Intent.UpdateShowStartTimePickerDialog ->
                _state.update { it.copy(showStartTimePickerDialog = intent.value) }

            is AddSleepQualityContract.Intent.UpdateShowEndTimePickerDialog ->
                _state.update { it.copy(showEndTimePickerDialog = intent.value) }

            is AddSleepQualityContract.Intent.UpdateStartTime ->
                _state.update {
                    it.copy(
                        record = it.record.copy(
                            startSleeping = it.record.startSleeping.copy(
                                intent.hour,
                                intent.minute
                            )
                        ),
                    )
                }

            is AddSleepQualityContract.Intent.UpdateEndTime ->
                _state.update {
                    it.copy(
                        record = it.record.copy(
                            endSleeping = it.record.endSleeping.copy(
                                intent.hour,
                                intent.minute
                            )
                        ),
                    )
                }

            AddSleepQualityContract.Intent.ResetState ->
                _state.update { AddSleepQualityContract.State() }
        }
    }
}