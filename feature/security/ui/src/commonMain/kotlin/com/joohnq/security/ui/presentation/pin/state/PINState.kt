package com.joohnq.security.ui.presentation.pin.state

import androidx.compose.material3.SnackbarHostState
import androidx.compose.ui.focus.FocusRequester
import com.joohnq.security.ui.presentation.pin.event.PINEvent
import com.joohnq.security.ui.presentation.pin.viewmodel.PINViewModelIntent
import com.joohnq.security.ui.presentation.pin.viewmodel.PINViewModelState

data class PINState(
    val snackBarState: SnackbarHostState = SnackbarHostState(),
    val pinViewModelState: PINViewModelState,
    val focusRequesters: List<FocusRequester>,
    val onAction: (PINViewModelIntent) -> Unit,
    val onEvent: (PINEvent) -> Unit,
    val canContinue: Boolean,
)
