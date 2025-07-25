package com.joohnq.auth.impl.presentation.auth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
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
fun AuthNameScreen(
    onNavigateToSecurity: () -> Unit,
) {
    val authNameViewModel: AuthNameViewModel = sharedViewModel()
    val userViewModel: UserViewModel = sharedViewModel()
    val preferencesViewModel: PreferencesViewModel = sharedViewModel()
    val scope = rememberCoroutineScope()
    val focusManager: FocusManager = LocalFocusManager.current
    val snackBarState = rememberSnackBarState()
    val userNameState by authNameViewModel.state.collectAsState()

    fun onError(error: String) {
        scope.launch {
            snackBarState.showSnackbar(error)
        }
    }

    fun onEvent(event: AuthNameContract.Event) = when (event) {
        AuthNameContract.Event.Continue -> {
            focusManager.clearFocus()
            try {
                UserNameValidator(userNameState.name)
                userViewModel.onAction(UserIntent.UpdateUserName(userNameState.name))
            } catch (e: Exception) {
                authNameViewModel.onAction(AuthNameContract.Intent.UpdateError(e.message.toString()))
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

    AuthNameContent(
        state = userNameState,
        snackBarState = snackBarState,
        onClearFocus = focusManager::clearFocus,
        onEvent = ::onEvent,
        onGetAction = authNameViewModel::onAction
    )
}