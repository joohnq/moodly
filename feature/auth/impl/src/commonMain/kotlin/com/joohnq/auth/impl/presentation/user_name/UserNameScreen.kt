package com.joohnq.auth.impl.presentation.user_name

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import com.joohnq.auth.impl.presentation.user_name.event.UserNameEvent
import com.joohnq.auth.impl.presentation.user_name.viewmodel.UserNameIntent
import com.joohnq.auth.impl.presentation.user_name.viewmodel.UserNameViewModel
import com.joohnq.api.validator.UserNameValidator
import com.joohnq.preferences.impl.ui.viewmodel.PreferenceIntent
import com.joohnq.preferences.impl.ui.viewmodel.PreferencesViewModel
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.ui.sharedViewModel
import com.joohnq.user.impl.ui.viewmodel.UserIntent
import com.joohnq.user.impl.ui.viewmodel.UserSideEffect
import com.joohnq.user.impl.ui.viewmodel.UserViewModel
import kotlinx.coroutines.launch

@Composable
fun UserNameScreen(
    onNavigateToSecurity: () -> Unit,
) {
    val userNameViewModel: UserNameViewModel = sharedViewModel()
    val userViewModel: UserViewModel = sharedViewModel()
    val preferencesViewModel: PreferencesViewModel = sharedViewModel()
    val scope = rememberCoroutineScope()
    val focusManager: FocusManager = LocalFocusManager.current
    val snackBarState = rememberSnackBarState()
    val userNameState by userNameViewModel.state.collectAsState()

    fun onError(error: String) {
        scope.launch {
            snackBarState.showSnackbar(error)
        }
    }

    fun onEvent(event: UserNameEvent) = when (event) {
        UserNameEvent.Continue -> {
            focusManager.clearFocus()
            try {
                UserNameValidator(userNameState.name)
                userViewModel.onAction(UserIntent.UpdateUserName(userNameState.name))
            } catch (e: Exception) {
                userNameViewModel.onAction(UserNameIntent.UpdateUserNameError(e.message.toString()))
            }
        }
    }

    LaunchedEffect(userViewModel) {
        userViewModel.sideEffect.collect { event ->
            when (event) {
                is UserSideEffect.UserNameUpdatedSuccess -> {
                    preferencesViewModel.onAction(
                        PreferenceIntent.UpdateSkipAuth()
                    )
                    onNavigateToSecurity()
                }

                is UserSideEffect.ShowError -> onError(event.error)
                else -> {}
            }
        }
    }

    UserNameContent(
        state = userNameState,
        snackBarState = snackBarState,
        onClearFocus = focusManager::clearFocus,
        onEvent = ::onEvent,
        onGetAction = userNameViewModel::onAction
    )
}

