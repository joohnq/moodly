package com.joohnq.auth.ui.presentation.welcome_authentication

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.joohnq.auth.ui.components.AuthenticationOrientation
import com.joohnq.auth.ui.components.AuthenticationRedirectSection
import com.joohnq.auth.ui.components.AuthenticationScaffold
import com.joohnq.auth.ui.components.AuthenticationWelcomeHeader
import com.joohnq.auth.ui.components.SignInWithEmailButton
import com.joohnq.auth.ui.components.SignInWithGoogleButton
import com.joohnq.auth.ui.viewmodel.AuthContract
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun WelcomeAuthenticationUI(
    snackBarHostState: SnackbarHostState = SnackbarHostState(),
    onIntent: (AuthContract.Intent) -> Unit = {},
    onEvent: (WelcomeAuthenticationContract.Event) -> Unit = {}
) {
    AuthenticationScaffold(
        backgroundColor = Colors.Green50,
        containerColor = Colors.White,
        topContent = {
            AuthenticationWelcomeHeader()
        },
        orientation = AuthenticationOrientation.BOTTOM,
        snackBarHostState = snackBarHostState,
        bottomContent = {
            VerticalSpacer(32.dp)
            Text(
                text = "Select how you’d like to proceed.",
                style = TextStyles.textMdRegular(),
                color = Colors.Gray60,
                textAlign = TextAlign.Center
            )
            VerticalSpacer(24.dp)
            SignInWithGoogleButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    onEvent(WelcomeAuthenticationContract.Event.SignInWithGoogle)
                }
            )
            VerticalSpacer(16.dp)
            SignInWithEmailButton(
                onClick = { onEvent(WelcomeAuthenticationContract.Event.SignInWithEmail) }
            )
            AuthenticationRedirectSection(
                text = "Don’t have an account?",
                actionText = "Sign Up",
                onClick = { onEvent(WelcomeAuthenticationContract.Event.SignUp) }
            )
        }
    )
}

@Preview
@Composable
fun WelcomeAuthenticationUIPreview() {
    WelcomeAuthenticationUI()
}