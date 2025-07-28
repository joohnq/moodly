package com.joohnq.home.impl.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joohnq.mood.impl.ui.mapper.getTodayMoodRecord
import com.joohnq.mood.impl.ui.resource.MoodRecordResource
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.card.MetricSummaryCard
import com.joohnq.shared_resources.components.layout.NotFoundHorizontalLayout
import com.joohnq.shared_resources.level
import com.joohnq.shared_resources.mood
import com.joohnq.shared_resources.set_up_mood
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.paddingHorizontalMedium
import com.joohnq.shared_resources.you_havent_set_up_any_mood_yet
import org.jetbrains.compose.resources.stringResource

@Composable
fun MoodMetric(
    records: List<MoodRecordResource>,
    onCreate: () -> Unit = {},
    onClick: () -> Unit = {},
) {
    val record = records.getTodayMoodRecord()

    if (record == null) {
        NotFoundHorizontalLayout(
            modifier = Modifier.paddingHorizontalMedium(),
            containerColor = Colors.White,
            image = Drawables.Images.MoodInsight,
            title = Res.string.you_havent_set_up_any_mood_yet,
            subtitle = Res.string.set_up_mood,
            onClick = onCreate
        )
    } else {
        MetricSummaryCard(
            modifier = Modifier.paddingHorizontalMedium(),
            containerColor = Colors.White,
            icon = record.mood.assets.icon,
            title = stringResource(Res.string.mood),
            text = record.mood.healthLevel.toString(),
            suffix = stringResource(Res.string.level),
            description = stringResource(record.mood.text),
            content = { modifier ->
                WeekMoodIndicator(
                    modifier = modifier,
                    records = records,
                    resource = record.mood
                )
            },
            color = record.mood.palette.color,
            onClick = onClick
        )
    }
}
