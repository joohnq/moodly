package com.joohnq.stress_level.add.presentation

sealed interface StressStressorsContract {
    sealed interface Event {
        data object OnGoBack : Event

        data object OnContinue : Event
    }
}
