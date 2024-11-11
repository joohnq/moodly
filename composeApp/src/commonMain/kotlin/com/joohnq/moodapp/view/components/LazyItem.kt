package com.joohnq.moodapp.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.entities.StatsRecord
import com.joohnq.moodapp.helper.DatetimeHelper
import com.joohnq.moodapp.view.constants.Colors
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun MentalHealthMetricItem(
    title: StringResource,
    icon: DrawableResource,
    backgroundColor: Color,
    onClick: () -> Unit,
    content: @Composable (Modifier) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxSize(),
        onClick = onClick,
        colors = CardColors(
            containerColor = backgroundColor,
            contentColor = Colors.White,
            disabledContainerColor = backgroundColor,
            disabledContentColor = Colors.White
        ),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(20.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(icon),
                    contentDescription = stringResource(title),
                    tint = Colors.White,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = stringResource(title),
                    style = TextStyles.HomeMetricTitle()
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            content(
                Modifier.heightIn(min = 130.dp, max = 140.dp)
                    .widthIn(min = 130.dp, max = 140.dp).fillMaxSize()
            )
        }
    }
}

@Composable
fun MentalScoreHistoryItem(statsRecord: StatsRecord, onClick: () -> Unit) {
    val day = remember { DatetimeHelper.dayOfMonth(statsRecord.date) }
    val monthNameAbbrev = remember { DatetimeHelper.monthNameAbbrev(statsRecord.date) }

    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        colors = CardColors(
            containerColor = Colors.White,
            contentColor = Colors.White,
            disabledContainerColor = Colors.White,
            disabledContentColor = Colors.White
        ),
        shape = RoundedCornerShape(20.dp),
        onClick = onClick,
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .background(color = Colors.White, shape = RoundedCornerShape(16.dp))
                    .padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = monthNameAbbrev,
                    style = TextStyles.SmLabel(),
                    color = Colors.Brown100Alpha64
                )
                Text(
                    text = day,
                    style = TextStyles.ExtraBoldXL(),
                    color = Colors.Brown80
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(statsRecord.mood.text),
                    style = TextStyles.MindfulTrackerCardTitle()
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = statsRecord.description,
                    style = TextStyles.MindfulTrackerCardSubtitle(),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            CircularProgressWithText(
                modifier = Modifier.size(64.dp),
                color = statsRecord.mood.palette.color,
                backgroundColor = statsRecord.mood.palette.backgroundColor,
                progress = { statsRecord.mood.healthLevel / 100f },
            ) {
                Text(
                    text = statsRecord.mood.healthLevel.toString(),
                    style = TextStyles.SleepQualityOption()
                )
            }
        }
    }
}

@Composable
fun MentalScoreHistoryItemWithHour(statsRecord: StatsRecord, onClick: () -> Unit) {
    val hourAndMinutes = remember { DatetimeHelper.hourAndMinutes(statsRecord.date) }
    val daySection = remember { DatetimeHelper.daySection(statsRecord.date) }

    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        colors = CardColors(
            containerColor = Colors.White,
            contentColor = Colors.White,
            disabledContainerColor = Colors.White,
            disabledContentColor = Colors.White
        ),
        shape = RoundedCornerShape(20.dp),
        onClick = onClick,
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(color = Colors.White, shape = RoundedCornerShape(16.dp)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = daySection,
                    style = TextStyles.SmLabel(),
                    color = Colors.Brown100Alpha64
                )
                Text(
                    text = hourAndMinutes,
                    style = TextStyles.TextExtraBoldLG(),
                    color = Colors.Brown80
                )
            }
            Spacer(modifier = Modifier.width(20.dp))
            Column(
                modifier = Modifier.fillMaxHeight().weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(statsRecord.mood.text),
                    style = TextStyles.MindfulTrackerCardTitle()
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = statsRecord.description,
                    style = TextStyles.MindfulTrackerCardSubtitle(),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(modifier = Modifier.width(20.dp))
            CircularProgressWithText(
                modifier = Modifier.size(64.dp),
                color = statsRecord.mood.palette.color,
                backgroundColor = statsRecord.mood.palette.backgroundColor,
                progress = { statsRecord.mood.healthLevel / 100f },
            ) {
                Text(
                    text = statsRecord.mood.healthLevel.toString(),
                    style = TextStyles.SleepQualityOption()
                )
            }
        }
    }
}