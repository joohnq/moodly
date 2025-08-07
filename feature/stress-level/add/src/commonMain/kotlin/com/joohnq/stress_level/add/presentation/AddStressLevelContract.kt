package com.joohnq.stress_level.add.presentation

import com.joohnq.stress_level.impl.ui.resource.StressLevelRecordResource
import com.joohnq.stress_level.impl.ui.resource.StressorResource
import com.joohnq.ui.UnidirectionalViewModel

sealed interface AddStressLevelContract {
    interface ViewModel : UnidirectionalViewModel<State, Intent, SideEffect>

    sealed interface Event {
        data object GoBack : Event
    }

    sealed interface Intent {
        data class ChangeAddingStressors(
            val stressor: StressorResource,
        ) : Intent

        data class ChangeStressLevel(
            val sliderValue: Float,
        ) : Intent

        data object Add : Intent

        data object ResetState : Intent
    }

    sealed interface SideEffect {
        data object NavigateToStressStressors : SideEffect

        data object GoBack : SideEffect

        data class ShowError(
            val message: String,
        ) : SideEffect
    }

    data class State(
        val item: StressLevelRecordResource = StressLevelRecordResource(),
        val sliderValue: Float = 0f,
    )
}
