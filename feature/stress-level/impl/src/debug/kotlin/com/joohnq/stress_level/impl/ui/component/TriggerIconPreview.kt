package com.joohnq.stress_level.impl.ui.component

import androidx.compose.runtime.Composable
import com.joohnq.shared_resources.theme.Drawables
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun TriggerIconPreview() {
    TriggerIcon(
        icon = Drawables.Icons.Outlined.Logo
    )
}