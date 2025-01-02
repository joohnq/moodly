package com.joohnq.user.ui.presentation.get_user_name.state

import androidx.compose.material3.SnackbarHostState
import com.joohnq.user.ui.presentation.get_user_name.event.GetUserNameEvent
import com.joohnq.user.ui.presentation.get_user_name.viewmodel.GetUserNameIntent
import com.joohnq.user.ui.presentation.get_user_name.viewmodel.GetUserNameViewModelState
import com.joohnq.user.ui.viewmodel.user.UserViewModelIntent

data class GetUserNameState(
    val snackBarState: SnackbarHostState,
    val state: GetUserNameViewModelState,
    val onEvent: (GetUserNameEvent) -> Unit,
    val onClearFocus: () -> Unit,
    val onGetAction: (GetUserNameIntent) -> Unit,
    val onAction: (UserViewModelIntent) -> Unit,
)