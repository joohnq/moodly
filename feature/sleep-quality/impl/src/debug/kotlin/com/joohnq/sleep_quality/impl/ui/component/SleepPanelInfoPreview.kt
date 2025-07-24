package com.joohnq.sleep_quality.impl.ui.component

import androidx.compose.runtime.Composable
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.app_name
import com.joohnq.shared_resources.theme.Drawables
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun SleepPanelInfoPreview() {
    SleepPanelInfo(
        icon = Drawables.Icons.Outlined.Logo,
        title = Res.string.app_name,
        value = "Value"
    )
}