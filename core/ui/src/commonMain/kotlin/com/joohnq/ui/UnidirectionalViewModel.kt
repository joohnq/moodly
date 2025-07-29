package com.joohnq.ui

import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface UnidirectionalViewModel<STATE, EVENT, EFFECT> {
    val state: StateFlow<STATE>

    val sideEffect: SharedFlow<EFFECT>

    fun onIntent(event: EVENT)
}
