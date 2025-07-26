package com.joohnq.security.impl.ui.components

import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun PinCodePreview() {
    PinCode(
        code = listOf(1, 2, 3, 4),
        focusedIndex = 1
    )
}
