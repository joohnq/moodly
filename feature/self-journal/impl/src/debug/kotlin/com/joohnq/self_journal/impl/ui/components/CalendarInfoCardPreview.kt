package com.joohnq.self_journal.impl.ui.components

import androidx.compose.runtime.Composable
import com.joohnq.shared_resources.remember.rememberCalendarInfo
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun CalendarInfoCardPreview() {
    rememberCalendarInfo().forEach { info ->
        CalendarInfoCard(
            info = info
        )
    }
}