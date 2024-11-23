package com.joohnq.moodapp.ui.presentation.stress_stressors.event

import com.joohnq.moodapp.domain.Stressor

sealed class StressStressorsEvent {
    data object OnGoBack : StressStressorsEvent()
    data object OnContinue : StressStressorsEvent()
    data object OnGoBackToStressLevel : StressStressorsEvent()
    data class OnAddStressor(val stressor: Stressor) : StressStressorsEvent()
}