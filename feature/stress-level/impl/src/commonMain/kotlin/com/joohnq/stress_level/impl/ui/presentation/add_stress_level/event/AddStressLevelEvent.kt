package com.joohnq.stress_level.impl.ui.presentation.add_stress_level.event

sealed interface AddStressLevelEvent {
    data object GoBack : AddStressLevelEvent
    data object Continue : AddStressLevelEvent
    data object PopUpToStressLevelLevel : AddStressLevelEvent
}
