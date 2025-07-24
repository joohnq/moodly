package com.joohnq.shared_resources.components.button

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.continue_word

@Composable
fun ContinueButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    PrimaryButton(
        modifier = modifier,
        text = Res.string.continue_word,
        enabled = enabled,
        onClick = onClick
    )
}