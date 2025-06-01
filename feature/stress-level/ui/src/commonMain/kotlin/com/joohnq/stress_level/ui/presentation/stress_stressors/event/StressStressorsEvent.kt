package com.joohnq.stress_level.ui.presentation.stress_stressors.event

sealed interface StressStressorsContract{
    sealed interface Event : StressStressorsContract{
        data object GoBack : Event
        data object Continue : Event
    }
}