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
import com.joohnq.core.ui.mapper.fold
import com.joohnq.core.ui.sharedViewModel
import com.joohnq.domain.validator.UserNameValidator
import com.joohnq.user.ui.viewmodel.user.UserViewModel
import com.joohnq.user.ui.viewmodel.user.UserViewModelIntent
import com.joohnq.user.ui.viewmodel.user_preferences.UserPreferenceViewModel
import com.joohnq.user.ui.viewmodel.user_preferences.UserPreferenceViewModelIntent
import kotlinx.coroutines.launch

class UserNameScreen(
    private val onNavigateToDashboardScreen: () -> Unit,
) : CustomScreen<UserNameState>() {
    @Composable
    override fun Screen(): UserNameState {
        val userPreferencesViewModel: UserPreferenceViewModel = sharedViewModel()
        val userNameViewModel: UserNameViewModel = sharedViewModel()
        val userViewModel: UserViewModel = sharedViewModel()
        val scope = rememberCoroutineScope()
        val focusManager: FocusManager = LocalFocusManager.current
        val snackBarState = remember { SnackbarHostState() }
        val userState by userViewModel.state.collectAsState()
        val getUserNameState by userNameViewModel.state.collectAsState()

        fun onError(error: String) {
            scope.launch {
                snackBarState.showSnackbar(error)
            }
        }

        fun onEvent(event: UserNameEvent) =
            when (event) {
                UserNameEvent.Continue -> {
                    focusManager.clearFocus()
                    try {
                        UserNameValidator(getUserNameState.name)
                        userViewModel.onAction(UserViewModelIntent.UpdateUserName(getUserNameState.name))
                    } catch (e: Exception) {
                        userNameViewModel.onAction(UserNameIntent.UpdateUserNameError(e.message.toString()))
                    }
                }
            }

        LaunchedEffect(userState.updating) {
            userState.updating.fold(
                onError = ::onError,
                onSuccess = {
                    userPreferencesViewModel.onAction(
                        UserPreferenceViewModelIntent.UpdateSkipUserNameScreen()
                    )
                    onNavigateToDashboardScreen()
                }
            )
        }

        return UserNameState(
            state = getUserNameState,
            snackBarState = snackBarState,
            onClearFocus = focusManager::clearFocus,
            onAction = userViewModel::onAction,
            onEvent = ::onEvent,
            onGetAction = userNameViewModel::onAction
        )
    }

    @Composable
    override fun UI(state: UserNameState) = UserNameUI(state)

    object GetUserNameTestTag {
        const val TEXT_INPUT = "TEXT_INPUT"
    }
}
