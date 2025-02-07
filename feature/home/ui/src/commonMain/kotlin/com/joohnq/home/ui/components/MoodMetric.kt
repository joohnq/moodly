package com.joohnq.home.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.joohnq.mood.ui.mapper.getTodayStatRecord
import com.joohnq.mood.ui.resource.MoodRecordResource
import com.joohnq.mood.ui.resource.MoodResource
import com.joohnq.shared_resources.*
import com.joohnq.shared_resources.components.MetricCardSide
import com.joohnq.shared_resources.components.NotFoundHorizontal
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import com.kizitonwose.calendar.compose.weekcalendar.rememberWeekCalendarState
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun WeekMoodIndicator(
    modifier: Modifier = Modifier,
    records: List<MoodRecordResource>,
    resource: MoodResource,
    height: Dp = 60.dp
) {
    val weekState = rememberWeekCalendarState()
    val week = weekState.firstVisibleWeek

    Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(12.dp, alignment = Alignment.End)) {
        val shape = Dimens.Shape.Circle
        week.days.forEach { day ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Box(
                    modifier = Modifier
                        .height(height)
                        .width(12.dp)
                        .background(color = resource.palette.backgroundColor, shape = shape)
                        .clip(shape),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    val item = records.find { it.createdAt.date == day.date }
                    item?.let {
                        val relHeight = height * (item.mood.healthLevel / 100f)
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(relHeight)
                                .background(
                                    color = resource.palette.color, shape = shape
                                )
                                .clip(shape)
                        )
                    }
                }
                Text(
                    text = day.date.dayOfWeek.name.first().toString(),
                    style = TextStyles.Text2XsRegular(),
                    color = Colors.Brown80
                )
            }
        }
    }
}

@Composable
fun MoodMetric(
    records: List<MoodRecordResource>,
    containerColor: Color = Colors.White,
    onCreate: () -> Unit = {},
    onClick: () -> Unit = {},
) {
    val record = records.getTodayStatRecord()

    if (record == null)
        NotFoundHorizontal(
            modifier = Modifier.paddingHorizontalMedium(),
            containerColor = containerColor,
            image = Drawables.Images.MoodIllustration,
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

@Preview
@Composable
fun MoodMetricPreview() {
    MoodMetric(
        records = listOf(
            MoodRecordResource(),
            MoodRecordResource()
        )
    )
}