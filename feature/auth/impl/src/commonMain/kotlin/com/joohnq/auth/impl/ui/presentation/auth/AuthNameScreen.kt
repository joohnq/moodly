package com.joohnq.auth.impl.ui.presentation.auth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import com.joohnq.api.validator.UserNameValidator
import com.joohnq.preferences.impl.ui.viewmodel.PreferencesContract
import com.joohnq.preferences.impl.ui.viewmodel.PreferencesViewModel
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.ui.sharedViewModel
import com.joohnq.user.impl.ui.viewmodel.UserContract
import com.joohnq.user.impl.ui.viewmodel.UserViewModel
import kotlinx.coroutines.launch

@Composable
fun AuthNameScreen(onNavigateToSecurity: () -> Unit) {
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

    fun onEvent(event: AuthNameContract.Event) =
        when (event) {
            AuthNameContract.Event.OnContinue -> {
                focusManager.clearFocus()
                try {
                    UserNameValidator(userNameState.name)
                    userViewModel.onIntent(UserContract.Intent.UpdateName(userNameState.name))
                } catch (e: Exception) {
                    authNameViewModel.onIntent(AuthNameContract.Intent.UpdateError(e.message.toString()))
                }
            }
        }

    LaunchedEffect(userViewModel) {
        userViewModel.sideEffect.collect { event ->
            when (event) {
                is UserContract.SideEffect.UserNameUpdatedSuccess -> {
                    preferencesViewModel.onIntent(
                        PreferencesContract.Intent.UpdateSkipAuth()
                    )
                    onNavigateToSecurity()
                }

                is UserContract.SideEffect.ShowError -> onError(event.error)
                else -> {}
            }
        }
    }

    AuthNameContent(
        state = userNameState,
        snackBarState = snackBarState,
        onClearFocus = focusManager::clearFocus,
        onEvent = ::onEvent,
        onGetAction = authNameViewModel::onIntent
    )
}
