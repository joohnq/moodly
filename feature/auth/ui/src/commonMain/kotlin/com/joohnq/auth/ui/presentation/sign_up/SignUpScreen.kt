package com.joohnq.auth.ui.presentation.sign_up

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.auth.ui.contract.AuthContract
import com.joohnq.auth.ui.googleAuthenticatorComposable
import com.joohnq.auth.ui.viewmodel.AuthViewModel
import com.joohnq.domain.mapper.onFailure
import com.joohnq.domain.mapper.onSuccess
import com.joohnq.shared_resources.remember.rememberSnackBarState
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SignUpScreen(
    navigateToSignIn: () -> Unit,
    navigateToNext: () -> Unit
) {
    val viewModel = koinViewModel<AuthViewModel>()
    val state by viewModel.signUpState.collectAsState()
    val snackBarHostState = rememberSnackBarState()
    val scope = rememberCoroutineScope()
    val googleAuthenticator = googleAuthenticatorComposable()

    fun onError(error: Throwable) {
        scope.launch {
            snackBarHostState.showSnackbar(error.message.toString())
        }
    }

    fun onEvent(event: AuthContract.Event) =
        when (event) {
            AuthContract.Event.NavigateToSignIn -> navigateToSignIn()
            AuthContract.Event.SignInWithGoogle -> {
                scope.launch {
                    val oAuthUser = googleAuthenticator.signIn()
                    viewModel.onIntent(AuthContract.Intent.SignInWithGoogle(oAuthUser))
                }
            }
            else -> Unit
        }

    LaunchedEffect(state.status) {
        state.status
            .onSuccess {
                navigateToNext()
            }
            .onFailure(::onError)
    }

    SignUpContent(
        snackBarHostState = snackBarHostState,
        state = state,
        onIntent = viewModel::onIntent,
        onEvent = ::onEvent
    )
}