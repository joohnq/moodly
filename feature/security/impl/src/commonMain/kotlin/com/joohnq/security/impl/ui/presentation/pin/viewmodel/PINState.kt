package com.joohnq.security.impl.ui.presentation.pin.viewmodel

data class PINState(
    val code: List<Int?> = (1..4).map { null },
    val focusedIndex: Int? = null,
)