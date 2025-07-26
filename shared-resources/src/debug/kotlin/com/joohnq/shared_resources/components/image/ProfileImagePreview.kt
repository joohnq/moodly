package com.joohnq.shared_resources.components.image

import androidx.compose.runtime.Composable
import com.joohnq.shared_resources.theme.Drawables
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun ProfileImagePreview() {
    ProfileImage(
        painter = painterResource(Drawables.Icons.Outlined.Logo)
    )
}
