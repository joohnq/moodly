package com.joohnq.security.ui.presentation.pin.viewmodel

sealed interface PINIntent {
    data class OnEnterNumber(val number: Int?, val index: Int) : PINIntent
    data class OnChangeFieldFocused(val index: Int) : PINIntent
    data object OnKeyboardBack : PINIntent
}