package com.joohnq.ui.entity

import androidx.compose.runtime.State

data class StateDispatch<STATE, EVENT>(
    val state: State<STATE>,
    val dispatch: (EVENT) -> Unit,
)
