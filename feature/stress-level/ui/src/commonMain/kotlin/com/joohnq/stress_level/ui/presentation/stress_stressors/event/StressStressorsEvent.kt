package com.joohnq.stress_level.ui.presentation.stress_stressors.event

sealed class StressStressorsEvent {
    data object GoBack : StressStressorsEvent()
    data object Continue : StressStressorsEvent()
}