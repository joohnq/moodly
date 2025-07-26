package com.joohnq.home.impl.components

import androidx.compose.runtime.Composable
import com.joohnq.api.entity.ImageType
import com.joohnq.api.entity.User
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun HomeTopBarPreview() {
    HomeTopBar(
        user = User(
            id = 1,
            name = "John Doe",
            image = "0",
            imageType = ImageType.DRAWABLE
        )
    )
}
