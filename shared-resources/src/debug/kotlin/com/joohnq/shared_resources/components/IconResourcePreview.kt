package com.joohnq.shared_resources.components

import androidx.compose.runtime.Composable
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.app_name
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.ui.entity.IconResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun IconResourcePreview() {
    IconResource(
        icon = Drawables.Icons.Outlined.Logo,
        tint = Colors.Brown80,
        contentDescription = Res.string.app_name
    )
}
