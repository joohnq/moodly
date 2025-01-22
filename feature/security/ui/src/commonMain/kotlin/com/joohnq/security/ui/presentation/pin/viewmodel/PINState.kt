package com.joohnq.security.ui.presentation.pin.viewmodel

data class PINState(
    val code: List<Int?> = (1..4).map { null },
    val focusedIndex: Int? = null,
)