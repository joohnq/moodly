package com.joohnq.moodapp.ui.event

sealed class NextAndBackAction {
    data object OnGoBack : NextAndBackAction()
    data object OnContinue : NextAndBackAction()
}
