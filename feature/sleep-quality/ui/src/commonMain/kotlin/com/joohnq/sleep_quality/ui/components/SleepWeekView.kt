package com.joohnq.sleep_quality.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.SectionHeader
import com.joohnq.shared_resources.week_view
import com.joohnq.sleep_quality.domain.entity.SleepQualityRecord
import com.kizitonwose.calendar.core.Week

@Composable
fun SleepWeekView(
    modifier: Modifier = Modifier,
    week: Week,
    records: List<SleepQualityRecord>,
) {
    SectionHeader(
        modifier = modifier,
        title = Res.string.week_view,
        onSeeAll = {},
    )
    SleepQualityWeekCalendar(
        modifier = modifier,
        week = week,
        records = records
    )
}
