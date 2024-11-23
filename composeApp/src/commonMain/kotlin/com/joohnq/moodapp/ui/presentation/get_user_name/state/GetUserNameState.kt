package com.joohnq.moodapp.ui.presentation.get_user_name.state

import androidx.compose.material3.SnackbarHostState
import com.joohnq.moodapp.ui.presentation.get_user_name.event.GetUserNameEvent
import com.joohnq.moodapp.viewmodel.UserIntent

data class GetUserNameState(
    val snackBarState: SnackbarHostState,
    val name: String,
    val nameError: String?,
    val onEvent: (GetUserNameEvent) -> Unit = {},
    val onClearFocus: () -> Unit = {},
    val onAction: (UserIntent) -> Unit = {}
)