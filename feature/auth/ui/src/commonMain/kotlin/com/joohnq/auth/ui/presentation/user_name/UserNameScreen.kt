package com.joohnq.auth.ui.presentation.user_name

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import com.joohnq.auth.ui.presentation.user_name.event.UserNameEvent
import com.joohnq.auth.ui.presentation.user_name.state.UserNameState
import com.joohnq.auth.ui.presentation.user_name.viewmodel.UserNameIntent
import com.joohnq.auth.ui.presentation.user_name.viewmodel.UserNameViewModel
import com.joohnq.core.ui.CustomScreen
import com.joohnq.core.ui.sharedViewModel
import com.joohnq.domain.validator.UserNameValidator
import com.joohnq.user.ui.viewmodel.user.UserSideEffect
import com.joohnq.user.ui.viewmodel.user.UserViewModel
import com.joohnq.user.ui.viewmodel.user.UserViewModelIntent
import kotlinx.coroutines.launch

class UserNameScreen(
    private val onNavigateToSecurity: () -> Unit,
) : CustomScreen<UserNameState>() {
    @Composable
    override fun Screen(): UserNameState {
        val userNameViewModel: UserNameViewModel = sharedViewModel()
        val userViewModel: UserViewModel = sharedViewModel()
        val scope = rememberCoroutineScope()
        val focusManager: FocusManager = LocalFocusManager.current
        val snackBarState = remember { SnackbarHostState() }
        val userNameState by userNameViewModel.state.collectAsState()

        fun onError(error: Throwable) {
            scope.launch {
                snackBarState.showSnackbar(error.message.toString())
            }
        }

        fun onEvent(event: UserNameEvent) =
            when (event) {
                UserNameEvent.Continue -> {
                    focusManager.clearFocus()
                    try {
                        UserNameValidator(userNameState.name)
                        userViewModel.onAction(UserViewModelIntent.UpdateUserName(userNameState.name))
                    } catch (e: Exception) {
                        userNameViewModel.onAction(UserNameIntent.UpdateUserNameError(e.message.toString()))
                    }
                }
            }

        LaunchedEffect(userViewModel) {
            scope.launch {
                userViewModel.sideEffect.collect { event ->
                    when (event) {
                        is UserSideEffect.UserNameUpdatedSuccess -> {
                            onNavigateToSecurity()
                        }

                        is UserSideEffect.ShowError -> onError(event.error)
                        else -> {}
                    }
                }
            }
        }

        return UserNameState(
            state = userNameState,
            snackBarState = snackBarState,
            onClearFocus = focusManager::clearFocus,
            onAction = userViewModel::onAction,
            onEvent = ::onEvent,
            onGetAction = userNameViewModel::onAction
        )
    }

    @Composable
    override fun UI(state: UserNameState) = UserNameUI(state)

    object UserNameTestTag {
        const val TEXT_INPUT = "TEXT_INPUT"
    }
}
