package com.joohnq.moodapp.view.screens.addstresslevel

import com.joohnq.moodapp.entities.Stressor

sealed class StressStressorsAction {
    data object OnGoBack : StressStressorsAction()
    data object OnContinue : StressStressorsAction()
    data class OnClick(val stressor: Stressor) : StressStressorsAction()
}