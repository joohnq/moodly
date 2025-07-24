package com.joohnq.home.impl.components

import androidx.compose.runtime.Composable
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.home
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.ui.entity.IconResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun TabItemPreview() {
    TabItem(
        icon = IconResource(
            icon = Drawables.Icons.Outlined.Home,
            tint = Colors.Brown80,
            contentDescription = Res.string.home
        ),
        selected = true,
    )
}