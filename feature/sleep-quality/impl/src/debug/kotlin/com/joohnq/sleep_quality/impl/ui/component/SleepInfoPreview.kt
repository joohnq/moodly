package com.joohnq.sleep_quality.impl.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.app_name
import com.joohnq.shared_resources.theme.Drawables
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun SleepInfoPreview() {
    Row {
        SleepInfo(
            title = "Title",
            subtitle = Res.string.app_name,
            icon = Drawables.Icons.Outlined.Logo
        )
    }
}
