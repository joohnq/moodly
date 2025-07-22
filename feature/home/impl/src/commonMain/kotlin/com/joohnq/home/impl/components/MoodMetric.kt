package com.joohnq.home.impl.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.joohnq.mood.impl.ui.mapper.getTodayMoodRecord
import com.joohnq.mood.impl.ui.resource.MoodRecordResource
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.MetricCardSide
import com.joohnq.shared_resources.components.NotFoundHorizontal
import com.joohnq.shared_resources.level
import com.joohnq.shared_resources.mood
import com.joohnq.shared_resources.set_up_mood
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.you_havent_set_up_any_mood_yet
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MoodMetric(
    records: List<MoodRecordResource>,
    containerColor: Color = Colors.White,
    onCreate: () -> Unit = {},
    onClick: () -> Unit = {},
) {
    val record = records.getTodayMoodRecord()

    if (record == null)
        NotFoundHorizontal(
            modifier = Modifier.paddingHorizontalMedium(),
            containerColor = containerColor,
            image = Drawables.Images.MoodInsight,
            title = Res.string.you_havent_set_up_any_mood_yet,
            subtitle = Res.string.set_up_mood,
            onClick = onCreate
        )
    else
        MetricCardSide(
            modifier = Modifier.paddingHorizontalMedium(),
            containerColor = containerColor,
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