package com.joohnq.stress_level.impl.ui.component

import androidx.compose.runtime.Composable
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.stress_level.overview.component.TriggerIcon
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun Preview() {
    TriggerIcon(
        icon = Drawables.Icons.Outlined.Logo
    )
}
