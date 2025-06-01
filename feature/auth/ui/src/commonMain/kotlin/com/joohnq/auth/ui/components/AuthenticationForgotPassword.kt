package com.joohnq.auth.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.TextStyles

@Composable
fun AuthenticationForgotPassword(
    onClick: () -> Unit = {}
) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
        TextButton(
            onClick = onClick,
        ) {
            Text(
                text = "Forgot password?",
                style = TextStyles.textSmBold(),
                color = Colors.Green50,
                textDecoration = TextDecoration.Underline
            )
        }
    }
}