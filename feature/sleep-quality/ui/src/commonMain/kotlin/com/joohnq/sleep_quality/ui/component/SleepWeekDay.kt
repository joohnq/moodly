package com.joohnq.sleep_quality.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.joohnq.mood.ui.components.MoodFace
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.sleep_quality.ui.mapper.toMoodResource
import com.joohnq.sleep_quality.ui.resource.SleepQualityRecordResource
import com.kizitonwose.calendar.core.WeekDay
import org.jetbrains.compose.resources.painterResource

@Composable
fun SleepWeekDay(
    modifier: Modifier = Modifier,
    resource: SleepQualityRecordResource?,
    day: WeekDay,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = Colors.Gray30,
                    shape = Dimens.Shape.Circle
                )
                .padding(horizontal = 3.dp, vertical = 8.dp)
                .clip(Dimens.Shape.Circle),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(
                text = day.date.dayOfWeek.name.first().toString(),
                style = TextStyles.TextXsRegular(),
                color = Colors.Gray60
            )
            if (resource != null)
                MoodFace(modifier = Modifier.size(24.dp), resource = resource.sleepQuality.toMoodResource())
            else
                Icon(
                    painter = painterResource(Drawables.Icons.Close),
                    contentDescription = null,
                    tint = Colors.Pink40,
                    modifier = Modifier.size(24.dp)
                )
        }
    }
}
