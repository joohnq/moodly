package com.joohnq.sleep_quality.ui.component

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.joohnq.shared_resources.*
import com.joohnq.shared_resources.components.NotFoundHorizontal
import com.joohnq.shared_resources.components.SectionHeader
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.sleep_quality.ui.resource.SleepQualityRecordResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SleepInsight(
    modifier: Modifier = Modifier,
    containerColor: Color = Colors.White,
    records: List<SleepQualityRecordResource>,
    onCreate: () -> Unit = {},
) {
    SectionHeader(
        modifier = modifier,
        title = Res.string.sleep_insight
    )
    if (records.isEmpty())
        NotFoundHorizontal(
            modifier = modifier,
            containerColor = containerColor,
            title = Res.string.log_your_first_sleep,
            description = Res.string.lets_log_your_first_sleep_to_see_your_insight,
            text = Res.string.log_sleep,
            icon = Drawables.Icons.Outlined.Add,
            image = Drawables.Images.SleepQualityInsight,
            onCreate = onCreate
        )
    else
        SleepInsightCard(
            modifier = modifier,
            containerColor = containerColor,
            records = records
        )
}

@Preview
@Composable
fun SleepInsightPreviewEmpty() {
    SleepInsight(
        records = emptyList(),
    )
}

@Preview
@Composable
fun SleepInsightPreview() {
    SleepInsight(
        records = listOf(
            SleepQualityRecordResource(),
            SleepQualityRecordResource(),
            SleepQualityRecordResource(),
        )
    )
}