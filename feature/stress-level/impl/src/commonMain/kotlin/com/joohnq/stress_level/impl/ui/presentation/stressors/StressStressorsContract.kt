package com.joohnq.stress_level.impl.ui.presentation.stressors

sealed interface StressStressorsContract {
    sealed interface Event {
        data object OnGoBack : Event

        data object OnContinue : Event
    }
}
