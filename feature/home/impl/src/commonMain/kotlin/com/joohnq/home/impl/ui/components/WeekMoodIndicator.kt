package com.joohnq.home.impl.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.joohnq.mood.add.ui.resource.MoodRecordResource
import com.joohnq.mood.add.ui.resource.MoodResource
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.TextStyles
import com.kizitonwose.calendar.compose.weekcalendar.rememberWeekCalendarState

@Composable
fun WeekMoodIndicator(
    modifier: Modifier = Modifier,
    items: List<MoodRecordResource>,
    resource: MoodResource,
    height: Dp = 60.dp,
) {
    val weekState = rememberWeekCalendarState()
    val week = weekState.firstVisibleWeek

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp, alignment = Alignment.End)
    ) {
        val shape = Dimens.Shape.Circle
        week.days.forEach { day ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Box(
                    modifier =
                        Modifier
                            .height(height)
                            .width(12.dp)
                            .background(color = resource.palette.backgroundColor, shape = shape)
                            .clip(shape),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    val item = items.find { it.createdAt.date == day.date }
                    item?.let {
                        val relHeight = height * (item.mood.healthLevel / 100f)
                        Box(
                            modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .height(relHeight)
                                    .background(
                                        color = resource.palette.color,
                                        shape = shape
                                    ).clip(shape)
                        )
                    }
                }
                Text(
                    text =
                        day.date.dayOfWeek.name
                            .first()
                            .toString(),
                    style = TextStyles.text2XsRegular(),
                    color = Colors.Brown80
                )
            }
        }
    }
}
