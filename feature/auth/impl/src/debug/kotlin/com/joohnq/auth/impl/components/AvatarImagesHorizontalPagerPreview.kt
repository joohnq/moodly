package com.joohnq.auth.impl.components

import androidx.compose.runtime.Composable
import com.joohnq.auth.impl.ui.components.AvatarImagesHorizontalPager
import com.joohnq.shared_resources.remember.rememberAvatars
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun AvatarImagesHorizontalPagerPreview() {
    AvatarImagesHorizontalPager(
        avatars = rememberAvatars()
    )
}
