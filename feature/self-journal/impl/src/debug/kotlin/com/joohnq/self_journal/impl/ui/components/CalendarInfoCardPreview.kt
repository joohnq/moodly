package com.joohnq.self_journal.impl.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import com.joohnq.shared_resources.remember.rememberCalendarInfo
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun CalendarInfoCardPreview() {
    Row {
        rememberCalendarInfo().forEach { info ->
            CalendarInfoCard(
                info = info
            )
        }
    }
}