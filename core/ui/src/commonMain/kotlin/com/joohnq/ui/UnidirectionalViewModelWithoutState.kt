package com.joohnq.ui

import kotlinx.coroutines.flow.SharedFlow

interface UnidirectionalViewModelWithoutState<INTENT, EFFECT> {
    val sideEffect: SharedFlow<EFFECT>

    fun onIntent(intent: INTENT)
}