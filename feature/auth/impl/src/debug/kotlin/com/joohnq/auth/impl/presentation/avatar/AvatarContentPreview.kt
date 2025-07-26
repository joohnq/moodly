package com.joohnq.auth.impl.presentation.avatar

import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun AvatarContentPreview() {
    AvatarContent(
        state = AvatarContract.State()
    )
}
