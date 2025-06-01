package com.joohnq.stress_level.ui.presentation.add_stress_level.viewmodel

import com.joohnq.stress_level.ui.resource.StressLevelRecordResource
import com.joohnq.stress_level.ui.resource.StressorResource

sealed interface AddStressLevelContract {
    sealed interface Intent: AddStressLevelContract {
        data class UpdateStressors(val stressor: StressorResource) : Intent
        data class UpdateSliderValue(val sliderValue: Float) : Intent
        data object ResetState : Intent
    }

    data class State(
        val record: StressLevelRecordResource = StressLevelRecordResource(),
        val sliderValue: Float = 0f,
    ): AddStressLevelContract

    sealed interface Event: AddStressLevelContract {
        data object GoBack : Event
        data object Continue : Event
        data object PopUpToStressLevelLevel : Event
    }
}