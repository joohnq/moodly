package com.joohnq.security.ui.presentation.pin.viewmodel

data class PINViewModelState(
    val code: List<Int?> = (1..4).map { null },
    val focusedIndex: Int? = null,
    val isValid: Boolean = false,
)