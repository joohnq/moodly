package com.joohnq.home.impl.components

import androidx.compose.runtime.Composable
import com.joohnq.api.getNow
import com.joohnq.self_journal.impl.ui.resource.SelfJournalRecordResource
import kotlinx.datetime.LocalDateTime
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun SelfJournalingMetricPreviewToday() {
    SelfJournalingMetric(
        records = listOf(
            SelfJournalRecordResource(),
        ),
    )
}

@Preview
@Composable
fun SelfJournalingMetricPreviewYesterday() {
    val now = getNow()

    SelfJournalingMetric(
        records = listOf(
            SelfJournalRecordResource(
                createdAt = LocalDateTime(now.year, now.month, now.date.dayOfMonth.minus(1), 0, 0)
            ),
        ),
    )
}