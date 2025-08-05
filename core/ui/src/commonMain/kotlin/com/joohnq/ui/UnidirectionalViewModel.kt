package com.joohnq.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.joohnq.ui.entity.StateDispatch
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface UnidirectionalViewModel<STATE, INTENT, EFFECT> {
    val state: StateFlow<STATE>

    val sideEffect: SharedFlow<EFFECT>

    fun onIntent(intent: INTENT)
}

@Composable
inline fun <reified STATE, INTENT, EFFECT> UnidirectionalViewModel<STATE, INTENT, EFFECT>.observe(
    crossinline handleEffect: CoroutineScope.(EFFECT) -> Unit,
): StateDispatch<STATE, INTENT> {
    val collectedState by state.collectAsStateWithLifecycle()

    val dispatch: (INTENT) -> Unit = { onIntent(it) }

    LaunchedEffect(key1 = sideEffect) {
        sideEffect.collect {
            handleEffect(it)
        }
    }

    return StateDispatch(
        state = collectedState,
        dispatch = dispatch
    )
}

@Suppress("MaxLineLength")
@Composable
inline fun <reified STATE, INTENT, EFFECT> UnidirectionalViewModel<STATE, INTENT, EFFECT>.observeWithoutEffect(): StateDispatch<STATE, INTENT> {
    val collectedState by state.collectAsStateWithLifecycle()
    val dispatch: (INTENT) -> Unit = { onIntent(it) }

    return StateDispatch(
        state = collectedState,
        dispatch = dispatch
    )
}
