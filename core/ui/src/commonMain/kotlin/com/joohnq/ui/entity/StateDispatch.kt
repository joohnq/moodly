package com.joohnq.ui.entity

data class StateDispatch<STATE, EVENT>(
    val state: STATE,
    val dispatch: (EVENT) -> Unit,
)