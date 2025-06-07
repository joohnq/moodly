package com.joohnq.auth.ui.presentation.common.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.joohnq.domain.entity.DIcon
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.HorizontalSpacer
import com.joohnq.shared_resources.components.Icon
import com.joohnq.shared_resources.components.ProgressButton
import com.joohnq.shared_resources.sign_in_with_email
import com.joohnq.shared_resources.sign_in_with_google
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.stringResource

@Composable
fun SignInWithGoogleButton(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    onClick: () -> Unit = {},
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
        onClick = onClick
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