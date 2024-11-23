package com.joohnq.moodapp.ui.presentation.add_stress_level.event

sealed class AddStressLevelEvent {
    data object OnGoBack : AddStressLevelEvent()
    data object OnNavigateTo : AddStressLevelEvent()
    data object OnPopUpToStressLevelLevel : AddStressLevelEvent()
}
