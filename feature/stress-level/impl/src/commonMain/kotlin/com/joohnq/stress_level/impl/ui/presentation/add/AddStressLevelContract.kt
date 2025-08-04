package com.joohnq.stress_level.impl.ui.presentation.add

import com.joohnq.stress_level.impl.ui.resource.StressLevelRecordResource
import com.joohnq.stress_level.impl.ui.resource.StressorResource
import com.joohnq.ui.UnidirectionalViewModel

sealed interface AddStressLevelContract {
    interface ViewModel : UnidirectionalViewModel<State, Intent, SideEffect>

    sealed interface Event {
        data object OnGoBack : Event

        data object OnContinue : Event
    }

    sealed interface Intent {
        data class UpdateAddingStressors(
            val stressor: StressorResource,
        ) : Intent

        data class UpdateStressLevel(
            val sliderValue: Float,
        ) : Intent

        data object Add : Intent

        data object ResetState : Intent
    }

    sealed interface SideEffect {
        data object NavigateToStressStressors : SideEffect

        data object OnGoBack : SideEffect

        data class ShowError(
            val message: String,
        ) : SideEffect
    }

    data class State(
        val record: StressLevelRecordResource = StressLevelRecordResource(),
        val sliderValue: Float = 0f,
    )
}
