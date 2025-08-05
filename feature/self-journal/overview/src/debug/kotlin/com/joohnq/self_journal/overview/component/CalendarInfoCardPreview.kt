package com.joohnq.self_journal.overview.component

import androidx.compose.runtime.Composable
import com.joohnq.self_journal.presentation.components.CalendarInfoCard
import com.joohnq.shared_resources.remember.rememberCalendarInfo
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun Preview() {
    rememberCalendarInfo().forEach { info ->
        CalendarInfoCard(
            info = info
        )
    }
}