package com.joohnq.sleep_quality.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.SectionHeader
import com.joohnq.shared_resources.sleep_history
import com.joohnq.shared_resources.sleep_insight
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.sleep_quality.domain.entity.SleepQualityRecord
import com.kizitonwose.calendar.core.Week

@Composable
fun SleepContent(
    modifier: Modifier = Modifier,
    week: Week,
    records: List<SleepQualityRecord>,
    onSeeAll: () -> Unit
) {
    val containerColor = Colors.Gray5
//    SleepWeekView(
//        modifier = modifier,
//        week = week,
//        records = records
//    )
//    VerticalSpacer(10.dp)
    SectionHeader(
        modifier = modifier,
        title = Res.string.sleep_insight
    )
    SleepInsight(
        modifier = modifier,
        containerColor = containerColor,
        records = records,
        onClick = {}
    )
    SectionHeader(
        modifier = modifier,
        title = Res.string.sleep_history,
        onSeeAll = onSeeAll
    )
    SleepHistory(
        modifier = modifier,
        containerColor = containerColor,
        records = records.take(7),
        onClick = {},
    )
}