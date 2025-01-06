package com.joohnq.auth.ui.presentation.user_name.state

import androidx.compose.material3.SnackbarHostState
import com.joohnq.auth.ui.presentation.user_name.event.UserNameEvent
import com.joohnq.auth.ui.presentation.user_name.viewmodel.UserNameIntent
import com.joohnq.auth.ui.presentation.user_name.viewmodel.UserNameViewModelState
import com.joohnq.user.ui.viewmodel.user.UserViewModelIntent

data class UserNameState(
    val snackBarState: SnackbarHostState,
    val state: UserNameViewModelState,
    val onEvent: (UserNameEvent) -> Unit,
    val onClearFocus: () -> Unit,
    val onGetAction: (UserNameIntent) -> Unit,
    val onAction: (UserViewModelIntent) -> Unit,
)