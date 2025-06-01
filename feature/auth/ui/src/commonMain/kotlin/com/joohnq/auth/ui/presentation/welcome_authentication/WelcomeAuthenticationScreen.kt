package com.joohnq.auth.ui.presentation.welcome_authentication

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.auth.ui.viewmodel.AuthViewModel
import com.joohnq.domain.mapper.onFailure
import com.joohnq.domain.mapper.onSuccess
import com.joohnq.shared_resources.remember.rememberSnackBarState
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun WelcomeAuthenticationScreen(
    navigateToNext: () -> Unit,
    navigateToSignIn: () -> Unit,
    navigateToSignUp: () -> Unit
) {
    val viewModel = koinViewModel<AuthViewModel>()
    val signInState by viewModel.signInState.collectAsState()
    val snackBarHostState = rememberSnackBarState()
    val scope = rememberCoroutineScope()

    fun onError(error: Throwable) {
        scope.launch {
            snackBarHostState.showSnackbar(error.message.toString())
        }
    }

    fun onEvent(event: WelcomeAuthenticationContract.Event) {
        when (event) {
            WelcomeAuthenticationContract.Event.SignInWithEmail -> navigateToSignIn()
            WelcomeAuthenticationContract.Event.SignUp -> navigateToSignUp()
        }
    }

    LaunchedEffect(signInState.status) {
        signInState.status
            .onSuccess { navigateToNext() }
            .onFailure(::onError)
    }

    WelcomeAuthenticationUI(
        snackBarHostState = snackBarHostState,
        onIntent = viewModel::onIntent,
        onEvent = ::onEvent
    )
}