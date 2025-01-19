package com.joohnq.security.ui.presentation.pin.viewmodel

sealed interface PINViewModelIntent {
    data class OnEnterNumber(val number: Int?, val index: Int) : PINViewModelIntent
    data class OnChangeFieldFocused(val index: Int) : PINViewModelIntent
    data object OnKeyboardBack : PINViewModelIntent
}