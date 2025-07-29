package com.joohnq.stress_level.impl.ui.presentation.stress_stressors

sealed interface StressStressorsContract {
    sealed interface Event {
        data object OnGoBack : Event

        data object OnContinue : Event
    }
}