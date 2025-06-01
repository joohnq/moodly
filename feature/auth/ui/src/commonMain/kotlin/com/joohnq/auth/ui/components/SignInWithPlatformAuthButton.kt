package com.joohnq.auth.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.joohnq.auth.domain.entity.OAuthUser
import com.joohnq.domain.Platform
import com.joohnq.domain.entity.DIcon
import com.joohnq.domain.platform
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.HorizontalSpacer
import com.joohnq.shared_resources.components.Icon
import com.joohnq.shared_resources.components.ProgressButton
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.sign_in_with_apple
import com.joohnq.shared_resources.sign_in_with_email
import com.joohnq.shared_resources.sign_in_with_google
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.TextStyles
import com.mmk.kmpauth.core.UiContainerScope
import com.mmk.kmpauth.firebase.apple.AppleButtonUiContainer
import com.mmk.kmpauth.firebase.apple.AppleSignInRequestScope
import com.mmk.kmpauth.firebase.google.GoogleButtonUiContainerFirebase
import com.mmk.kmpauth.google.GoogleButtonUiContainer
import dev.gitlive.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource

@Composable
fun SignInWithPlatformAuthButton(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    onGoogleSignInResult: (OAuthUser) -> Unit = {},
    onAppleSignInResult: (Result<FirebaseUser?>) -> Unit = {},
) {
    Column {
        SignInWithGoogleButton(modifier, isLoading, onGoogleSignInResult)
        if (platform == Platform.IOS) {
            VerticalSpacer(16.dp)
            SignInWithAppleButton(modifier, isLoading, onAppleSignInResult)
        }
    }
}

@Composable
fun SignInWithGoogleButton(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    onGoogleSignInResult: (OAuthUser) -> Unit = {},
) {
    val scope = rememberCoroutineScope()
    when (platform) {
        Platform.ANDROID -> {
            GoogleButtonUiContainer(
                filterByAuthorizedAccounts = false,
                onGoogleSignInResult = {
                    onGoogleSignInResult(
                        OAuthUser(
                            id = it?.idToken,
                            accessToken = it?.accessToken,
                            token = it?.idToken,
                            email = it?.email,
                            name = it?.displayName,
                            image = it?.profilePicUrl
                        )
                    )
                }
            ) {
                SignInWithGoogleContent(
                    modifier = modifier,
                    isLoading = isLoading
                )
            }
        }

        Platform.IOS -> {
            GoogleButtonUiContainer(
                filterByAuthorizedAccounts = false,
                onGoogleSignInResult = {
                    onGoogleSignInResult(
                        OAuthUser(
                            id = it?.idToken,
                            accessToken = it?.accessToken,
                            token = it?.idToken,
                            email = it?.email,
                            name = it?.displayName,
                            image = it?.profilePicUrl
                        )
                    )
                }
            ) {
                SignInWithGoogleContent(
                    modifier = modifier,
                    isLoading = isLoading
                )
            }
        }
    }
}

@Composable
fun UiContainerScope.SignInWithGoogleContent(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
) {
    ProgressButton(
        modifier = modifier.fillMaxWidth().height(48.dp),
        isLoading = isLoading,
        colors = ButtonDefaults.buttonColors(
            containerColor = Colors.Gray100,
            contentColor = Colors.White,
            disabledContainerColor = Colors.Gray100,
            disabledContentColor = Colors.White
        ),
        onClick = { this.onClick() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                DIcon(
                    icon = Drawables.Icons.Outlined.Google,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(Dimens.Icon),
                    contentDescription = Res.string.sign_in_with_google
                )
            )
            HorizontalSpacer(12.dp)
            Text(
                text = stringResource(Res.string.sign_in_with_google),
                style = TextStyles.textMdSemiBold(),
            )
        }
    }
}

@Composable
fun SignInWithAppleButton(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    onAppleSignInResult: (Result<FirebaseUser?>) -> Unit = {},
) {
    AppleButtonUiContainer(
        modifier = Modifier,
        requestScopes = listOf(AppleSignInRequestScope.Email),
        linkAccount = false,
        onResult = onAppleSignInResult,
    ) {
        ProgressButton(
            modifier = modifier.fillMaxWidth().height(48.dp),
            isLoading = isLoading,
            colors = ButtonDefaults.buttonColors(
                containerColor = Colors.Gray100,
                contentColor = Colors.White,
                disabledContainerColor = Colors.Gray100,
                disabledContentColor = Colors.White
            ),
            onClick = { this.onClick() }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    DIcon(
                        icon = Drawables.Icons.Outlined.Apple,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(Dimens.Icon),
                        contentDescription = Res.string.sign_in_with_apple
                    )
                )
                HorizontalSpacer(12.dp)
                Text(
                    text = stringResource(Res.string.sign_in_with_apple),
                    style = TextStyles.textMdSemiBold(),
                )
            }
        }
    }
}

@Composable
fun SignInWithEmailButton(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    onClick: () -> Unit = {},
) {
    ProgressButton(
        modifier = modifier.fillMaxWidth().height(48.dp),
        isLoading = isLoading,
        colors = ButtonDefaults.buttonColors(
            containerColor = Colors.Brown60,
            contentColor = Colors.White,
            disabledContainerColor = Colors.Brown60,
            disabledContentColor = Colors.White
        ),
        onClick = onClick
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                DIcon(
                    icon = Drawables.Icons.Outlined.Email,
                    tint = Colors.White,
                    modifier = Modifier.size(Dimens.Icon),
                    contentDescription = Res.string.sign_in_with_email
                )
            )
            HorizontalSpacer(12.dp)
            Text(
                text = stringResource(Res.string.sign_in_with_email),
                style = TextStyles.textMdSemiBold(),
            )
        }
    }
}