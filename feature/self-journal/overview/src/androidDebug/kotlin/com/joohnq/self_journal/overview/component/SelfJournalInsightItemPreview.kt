package com.joohnq.self_journal.overview.component

import androidx.compose.runtime.Composable
import com.joohnq.self_journal.presentation.components.SelfJournalInsightItem
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun Preview() {
    SelfJournalInsightItem(
        count = 1,
        percentage = 0.5f,
        color = Colors.Brown80,
        face = Drawables.Icons.Outlined.Logo
    )
}
