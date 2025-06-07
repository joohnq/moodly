package com.joohnq.auth.ui.presentation.common.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.TextStyles


@Composable
fun AuthenticationRedirectSection(
    text: String,
    actionText: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$text ",
            style = TextStyles.textSmMedium(),
            color = Colors.Gray60,
        )
        TextButton(
            contentPadding = PaddingValues(0.dp),
            onClick = onClick,
        ) {
            Text(
                text = actionText,
                style = TextStyles.textSmMedium(),
                color = Colors.Green50,
                textDecoration = TextDecoration.Underline
            )
        }
    }
}