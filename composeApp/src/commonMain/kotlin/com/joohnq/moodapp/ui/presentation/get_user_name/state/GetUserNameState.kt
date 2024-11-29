package com.joohnq.moodapp.ui.presentation.get_user_name.state

import androidx.compose.material3.SnackbarHostState
import com.joohnq.moodapp.ui.presentation.get_user_name.event.GetUserNameEvent
import com.joohnq.moodapp.viewmodel.GetUserNameIntent
import com.joohnq.moodapp.viewmodel.GetUserNameViewModelState
import com.joohnq.moodapp.viewmodel.UserIntent

data class GetUserNameState(
    val snackBarState: SnackbarHostState,
    val getUserNameViewModelState: GetUserNameViewModelState,
    val onEvent: (GetUserNameEvent) -> Unit,
    val onClearFocus: () -> Unit,
    val onGetAction: (GetUserNameIntent) -> Unit,
    val onAction: (UserIntent) -> Unit
)