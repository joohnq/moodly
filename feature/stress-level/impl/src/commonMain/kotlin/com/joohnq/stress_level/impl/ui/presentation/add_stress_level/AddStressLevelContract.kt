package com.joohnq.stress_level.impl.ui.presentation.add_stress_level

import com.joohnq.stress_level.impl.ui.resource.StressLevelRecordResource
import com.joohnq.stress_level.impl.ui.resource.StressorResource

sealed interface AddStressLevelContract {
    sealed interface Event {
        data object GoBack : Event
        data object Continue : Event
        data object PopUpToStressLevelLevel : Event
    }

    sealed interface Intent {
        data class UpdateAddingStressors(val stressor: StressorResource) : Intent
        data class UpdateAddingSliderValue(val sliderValue: Float) : Intent
        data object ResetState : Intent
    }

    data class State(
        val record: StressLevelRecordResource = StressLevelRecordResource(),
        val sliderValue: Float = 0f,
    )
}