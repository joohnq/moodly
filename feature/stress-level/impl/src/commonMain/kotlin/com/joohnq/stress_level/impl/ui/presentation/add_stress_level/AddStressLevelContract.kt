package com.joohnq.stress_level.impl.ui.presentation.add_stress_level

import com.joohnq.stress_level.impl.ui.resource.StressLevelRecordResource
import com.joohnq.stress_level.impl.ui.resource.StressorResource
import com.joohnq.ui.UnidirectionalViewModel

sealed interface AddStressLevelContract {
    interface ViewModel : UnidirectionalViewModel<State, Intent, SideEffect>

    sealed interface Event {
        data object OnGoBack : Event

        data object OnContinue : Event

        data object OnPopUpToStressLevelLevel : Event
    }

    sealed interface Intent {
        data class UpdateAddingStressors(
            val stressor: StressorResource
        ) : Intent

        data class UpdateAddingSliderValue(
            val sliderValue: Float
        ) : Intent

        data object ResetState : Intent
    }

    sealed interface SideEffect

    data class State(
        val record: StressLevelRecordResource = StressLevelRecordResource(),
        val sliderValue: Float = 0f
    )
}
