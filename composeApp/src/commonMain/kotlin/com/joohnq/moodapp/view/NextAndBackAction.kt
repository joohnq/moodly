package com.joohnq.moodapp.view

sealed class NextAndBackAction {
    data object OnGoBack : NextAndBackAction()
    data object OnContinue : NextAndBackAction()
}