package com.joohnq.sleep_quality.overview.component

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.layout.NotFoundHorizontalLayout
import com.joohnq.shared_resources.components.text.SectionHeader
import com.joohnq.shared_resources.lets_log_your_first_sleep_to_see_your_insight
import com.joohnq.shared_resources.log_sleep
import com.joohnq.shared_resources.log_your_first_sleep
import com.joohnq.shared_resources.sleep_insight
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityRecordResource

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SleepQualityOverviewInsight(
    modifier: Modifier = Modifier,
    items: List<SleepQualityRecordResource>,
    onCreate: () -> Unit = {},
) {
    SectionHeader(
        modifier = modifier,
        title = Res.string.sleep_insight
    )
    if (items.isEmpty()) {
        NotFoundHorizontalLayout(
            modifier = modifier,
            containerColor = Colors.Gray5,
            title = Res.string.log_your_first_sleep,
            description = Res.string.lets_log_your_first_sleep_to_see_your_insight,
            text = Res.string.log_sleep,
            icon = Drawables.Icons.Outlined.Add,
            image = Drawables.Images.SleepQualityInsight,
            onCreate = onCreate
        )
    } else {
        SleepQualityOverviewInsightCard(
            modifier = modifier,
            items = items
        )
    }
}