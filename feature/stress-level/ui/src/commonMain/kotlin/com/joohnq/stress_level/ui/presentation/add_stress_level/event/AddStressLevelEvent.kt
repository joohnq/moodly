package com.joohnq.stress_level.ui.presentation.add_stress_level.event

sealed class AddStressLevelEvent {
    data object GoBack : AddStressLevelEvent()
    data object Continue : AddStressLevelEvent()
    data object PopUpToStressLevelLevel : AddStressLevelEvent()
}
