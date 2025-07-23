package com.joohnq.self_journal.impl.ui.components

import androidx.compose.runtime.Composable
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.app_name
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun SelfJournalPanelInfoPreview() {
    SelfJournalPanelInfo(
        progress = 0.5f,
        value = "Value",
        title = Res.string.app_name,
        color = Colors.Brown80,
        icon = Drawables.Icons.Outlined.Logo
    )
}