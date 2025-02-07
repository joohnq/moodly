package com.joohnq.sleep_quality.ui.component

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.SectionHeader
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingAllSmall
import com.joohnq.shared_resources.week_view
import com.joohnq.sleep_quality.ui.resource.SleepQualityRecordResource
import com.kizitonwose.calendar.core.Week

@Composable
fun SleepWeekView(
    modifier: Modifier = Modifier,
    containerColor: Color = Colors.White,
    week: Week,
    records: List<SleepQualityRecordResource>,
) {
    SectionHeader(
        modifier = modifier,
        title = Res.string.week_view,
        onSeeAll = {},
    )
    SleepQualityWeekCalendar(
        modifier = modifier.background(color = containerColor, shape = Dimens.Shape.Large).paddingAllSmall(),
        week = week,
        records = records
    )
}
