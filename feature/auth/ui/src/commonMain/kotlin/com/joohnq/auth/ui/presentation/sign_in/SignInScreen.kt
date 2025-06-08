package com.joohnq.auth.ui.presentation.sign_in

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.auth.ui.contract.AuthContract
import com.joohnq.auth.ui.viewmodel.AuthViewModel
import com.joohnq.domain.mapper.onFailure
import com.joohnq.domain.mapper.onSuccess
import com.joohnq.shared_resources.remember.rememberSnackBarState
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SignInScreen(
    navigateToForgotPassword: () -> Unit,
    navigateToSignUp: () -> Unit,
    navigateToNext: () -> Unit
) {
    val viewModel = koinViewModel<AuthViewModel>()
    val state by viewModel.signInState.collectAsState()
    val snackBarHostState = rememberSnackBarState()
    val scope = rememberCoroutineScope()

    fun onError(error: Throwable) {
        scope.launch {
            snackBarHostState.showSnackbar(error.message.toString())
        }
    }

    fun onEvent(event: AuthContract.Event) =
        when (event) {
            AuthContract.Event.NavigateToForgotPassword -> navigateToForgotPassword()
            AuthContract.Event.NavigateToSignUp -> navigateToSignUp()
            else -> Unit
        }

    LaunchedEffect(state.status) {
        state.status
            .onSuccess { navigateToNext() }
            .onFailure(::onError)
    }

    SignInContent(
        state = state,
        snackBarHostState = snackBarHostState,
        onIntent = viewModel::onIntent,
        onEvent = ::onEvent
    )
}