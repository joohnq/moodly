package com.joohnq.moodapp.ui.presentation.add_stress_level.event

sealed class AddStressLevelEvent {
    data object OnGoBack : AddStressLevelEvent()
    data object OnContinue : AddStressLevelEvent()
    data object OnPopUpToStressLevelLevel : AddStressLevelEvent()
}
