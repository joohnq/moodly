package com.joohnq.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow

abstract class BaseViewModel<
        I : ViewModelContract.Intent,
        S : ViewModelContract.State,
        SE : ViewModelContract.SideEffect
        >(
    initialState: S
) : ViewModel() {

    protected val _state = MutableStateFlow(initialState)
    val state: StateFlow<S> = _state

    protected val _sideEffect = Channel<SE>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    abstract fun onIntent(intent: I)
}