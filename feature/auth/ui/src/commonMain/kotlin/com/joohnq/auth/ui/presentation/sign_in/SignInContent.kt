package com.joohnq.auth.ui.presentation.sign_in

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.auth.ui.presentation.common.components.AuthenticationForgotPassword
import com.joohnq.auth.ui.presentation.common.components.AuthenticationHeader
import com.joohnq.auth.ui.presentation.common.components.AuthenticationOrDivider
import com.joohnq.auth.ui.presentation.common.components.AuthenticationOrientation
import com.joohnq.auth.ui.presentation.common.components.AuthenticationPasswordTextFieldSection
import com.joohnq.auth.ui.presentation.common.components.AuthenticationRedirectSection
import com.joohnq.auth.ui.presentation.common.components.AuthenticationScaffold
import com.joohnq.auth.ui.presentation.common.components.AuthenticationTextFieldSection
import com.joohnq.auth.ui.presentation.common.components.SignInWithGoogleButton
import com.joohnq.auth.ui.contract.AuthContract
import com.joohnq.domain.entity.UiState
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.SignInButton
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.email_address
import com.joohnq.shared_resources.enter_your_email_address
import com.joohnq.shared_resources.enter_your_password
import com.joohnq.shared_resources.password
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SignInContent(
    snackBarHostState: SnackbarHostState = SnackbarHostState(),
    state: AuthContract.SignInState = AuthContract.SignInState(),
    onIntent: (AuthContract.Intent) -> Unit = {},
    onEvent: (AuthContract.Event) -> Unit = {}
) {
    val isLoading = state.status is UiState.Loading

    AuthenticationScaffold(
        backgroundColor = Colors.Brown10,
        containerColor = Colors.White,
        topContent = {
            AuthenticationHeader(
                text = "Sign In to gain access to intelligent mental health"
            )
        },
        orientation = AuthenticationOrientation.BOTTOM,
        snackBarHostState = snackBarHostState,
        bottomContent = {
            AuthenticationTextFieldSection(
                modifier = Modifier.fillMaxWidth(),
                label = stringResource(Res.string.email_address),
                placeholder = stringResource(Res.string.enter_your_email_address),
                value = state.email,
                onValueChange = { email -> onIntent(AuthContract.Intent.SignInEmailChanged(email)) },
                leadingIcon = Drawables.Icons.Outlined.Email
            )
            VerticalSpacer(24.dp)
            AuthenticationPasswordTextFieldSection(
                modifier = Modifier.fillMaxWidth(),
                label = stringResource(Res.string.password),
                placeholder = stringResource(Res.string.enter_your_password),
                value = state.password,
                onValueChange = { password ->
                    onIntent(
                        AuthContract.Intent.SignInPasswordChanged(password)
                    )
                },
                leadingIcon = Drawables.Icons.Outlined.Email
            )
            AuthenticationForgotPassword(
                onClick = { onEvent(AuthContract.Event.NavigateToForgotPassword) }
            )
            SignInButton(
                modifier = Modifier,
                isLoading = isLoading,
                onClick = { onIntent(AuthContract.Intent.SignIn) }
            )
            VerticalSpacer(16.dp)
            AuthenticationOrDivider()
            VerticalSpacer(16.dp)
            SignInWithGoogleButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    onEvent(AuthContract.Event.SignInWithGoogle)
                }
            )
            VerticalSpacer(48.dp)
            AuthenticationRedirectSection(
                text = "Don’t have an account?",
                actionText = "Sign Up",
                onClick = { onEvent(AuthContract.Event.NavigateToSignUp) }
            )
        }
    )
}

@Preview
@Composable
fun SignInUIPreview() {
    SignInContent()
}