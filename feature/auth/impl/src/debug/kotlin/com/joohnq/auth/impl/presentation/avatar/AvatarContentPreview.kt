package com.joohnq.auth.impl.presentation.avatar

import androidx.compose.runtime.Composable
import com.joohnq.auth.impl.ui.presentation.avatar.AvatarContent
import com.joohnq.auth.impl.ui.presentation.avatar.AvatarContract
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun AvatarContentPreview() {
    AvatarContent(
        state = AvatarContract.State()
    )
}