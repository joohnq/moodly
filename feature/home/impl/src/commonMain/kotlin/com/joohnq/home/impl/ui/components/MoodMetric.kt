package com.joohnq.home.impl.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joohnq.mood.add.ui.mapper.MoodRecordResourceMapper.getTodayMoodRecord
import com.joohnq.mood.add.ui.resource.MoodRecordResource
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.card.MetricSummaryCard
import com.joohnq.shared_resources.components.layout.NotFoundHorizontalLayout
import com.joohnq.shared_resources.components.text.SectionHeader
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
    items: List<MoodRecordResource>,
    onCreate: () -> Unit = {},
    onClick: () -> Unit = {},
) {
    val item = items.getTodayMoodRecord()
    SectionHeader(
        modifier = Modifier.paddingHorizontalMedium(),
        title = Res.string.mood,
        onSeeMore = onClick
    )
    if (item == null) {
        NotFoundHorizontalLayout(
            modifier = Modifier.paddingHorizontalMedium(),
            containerColor = Colors.White,
            image = Drawables.Images.MoodInsight,
            title = Res.string.you_havent_set_up_any_mood_yet,
            actionText = Res.string.set_up_mood,
            onClick = onCreate
        )
    } else {
        MetricSummaryCard(
            modifier = Modifier.paddingHorizontalMedium(),
            containerColor = Colors.White,
            icon = item.mood.assets.icon,
            title = stringResource(Res.string.mood),
            text = item.mood.healthLevel.toString(),
            suffix = stringResource(Res.string.level),
            description = stringResource(item.mood.text),
            content = { modifier ->
                WeekMoodIndicator(
                    modifier = modifier,
                    items = items,
                    resource = item.mood
                )
            },
            color = item.mood.palette.color,
            onClick = onClick
        )
    }
}
