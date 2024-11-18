package com.joohnq.moodapp.view.screens.addstresslevel

import com.joohnq.moodapp.entities.Stressors

sealed class StressStressorsAction {
    data object OnGoBack : StressStressorsAction()
    data object OnContinue : StressStressorsAction()
    data class OnClick(val stressor: Stressors) : StressStressorsAction()
}