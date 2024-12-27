package com.joohnq.user.ui.presentation.get_user_name

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import com.joohnq.mood.CustomScreen
import com.joohnq.mood.sharedViewModel
import com.joohnq.mood.state.UiState.Companion.fold
import com.joohnq.user.ui.presentation.get_user_name.event.GetUserNameEvent
import com.joohnq.user.ui.presentation.get_user_name.state.GetUserNameState
import com.joohnq.user.ui.presentation.get_user_name.viewmodel.GetUserNameIntent
import com.joohnq.user.ui.presentation.get_user_name.viewmodel.GetUserNameViewModel
import com.joohnq.user.ui.viewmodel.UserPreferenceViewModel
import com.joohnq.user.ui.viewmodel.UserPreferenceViewModelIntent
import com.joohnq.user.ui.viewmodel.UserViewModel
import com.joohnq.user.ui.viewmodel.UserViewModelIntent
import kotlinx.coroutines.launch

class GetUserNameScreen : CustomScreen<GetUserNameState>() {
    @Composable
    override fun Screen(): GetUserNameState {
        val userPreferencesViewModel: UserPreferenceViewModel = sharedViewModel()
        val getUserNameViewModel: GetUserNameViewModel = sharedViewModel()
        val userViewModel: UserViewModel = sharedViewModel()
        val scope = rememberCoroutineScope()
        val focusManager: FocusManager = LocalFocusManager.current
        val snackBarState = remember { SnackbarHostState() }
        val userState by userViewModel.state.collectAsState()
        val getUserNameState by getUserNameViewModel.getUserNameState.collectAsState()

        fun onEvent(event: GetUserNameEvent) =
            when (event) {
                GetUserNameEvent.OnContinue -> {
                    focusManager.clearFocus()
                    try {
                        if (getUserNameState.name.isEmpty()) throw Exception("Name can't be empty")
                        userViewModel.onAction(UserViewModelIntent.UpdateUserName(getUserNameState.name))
                    } catch (e: Exception) {
                        getUserNameViewModel.onAction(GetUserNameIntent.UpdateUserNameError(e.message.toString()))
                    }
                }
            }

        LaunchedEffect(userState.updating) {
            userState.updating.fold(
                onError = {
                    scope.launch {
                        snackBarState.showSnackbar(it)
                    }
                },
                onSuccess = {
//                    onNavigate(DashboardScreen(), true)
                    userPreferencesViewModel.onAction(
                        UserPreferenceViewModelIntent.UpdateSkipGetUserNameScreen()
                    )
                }
            )
        }

        return GetUserNameState(
            state = getUserNameState,
            snackBarState = snackBarState,
            onClearFocus = focusManager::clearFocus,
            onAction = userViewModel::onAction,
            onEvent = ::onEvent,
            onGetAction = getUserNameViewModel::onAction
        )
    }

    @Composable
    override fun UI(state: GetUserNameState) = GetUserNameUI(state)

    object GetUserNameTestTag {
        const val TEXT_INPUT = "TEXT_INPUT"
    }
}
