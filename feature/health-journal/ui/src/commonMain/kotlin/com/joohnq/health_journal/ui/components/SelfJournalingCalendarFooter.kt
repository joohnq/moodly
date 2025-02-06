package com.joohnq.health_journal.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.remember.rememberCalendarInfo

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SelfJournalingCalendarFooter() {
    val calendarInfos = rememberCalendarInfo()
    FlowRow(
        modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.CenterHorizontally)
    ) {
        calendarInfos.forEach { info -> CalendarInfoCard(info = info) }
    }
}
