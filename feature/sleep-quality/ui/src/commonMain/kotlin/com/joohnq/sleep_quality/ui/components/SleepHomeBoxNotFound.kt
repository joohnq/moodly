package com.joohnq.sleep_quality.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.joohnq.core.ui.getNow
import com.joohnq.core.ui.mapper.calculateDuration
import com.joohnq.core.ui.mapper.toFormattedTimeString
import com.joohnq.core.ui.mapper.toHoursAndMinutesString
import com.joohnq.mood.ui.components.MoodFace
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.bedtime
import com.joohnq.shared_resources.components.GiganticSecondaryCard
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.set_up_sleep
import com.joohnq.shared_resources.start_sleeping
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.shared_resources.wake_up
import com.joohnq.shared_resources.you_havent_set_up_any_mental_sleep_yet
import com.joohnq.sleep_quality.domain.entity.SleepQualityRecord
import com.joohnq.sleep_quality.ui.mapper.toMoodResource
import com.joohnq.sleep_quality.ui.mapper.toResource
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

fun List<SleepQualityRecord>.getTodaySleepQualityRecord(): SleepQualityRecord? =
    find { it.createdAt == getNow().date }

@Composable
fun SleepQualityNotFound(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Card(
        colors = CardColors(
            containerColor = Colors.White,
            contentColor = Colors.Brown80,
            disabledContainerColor = Colors.White,
            disabledContentColor = Colors.Brown80
        ),
        shape = Dimens.Shape.Large,
        modifier = modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(Drawables.Images.SleepWomanIllustration),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )
            VerticalSpacer(16.dp)
            Text(
                text = stringResource(Res.string.you_havent_set_up_any_mental_sleep_yet),
                style = TextStyles.ParagraphSm(),
                color = Colors.Gray60,
                textAlign = TextAlign.Center
            )
            VerticalSpacer(16.dp)
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                color = Colors.Gray20
            )
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(
                    10.dp,
                    alignment = Alignment.CenterHorizontally
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(Res.string.set_up_sleep),
                    style = TextStyles.TextMdSemiBold(),
                    color = Colors.Brown60
                )
                Icon(
                    painter = painterResource(Drawables.Icons.Arrow),
                    contentDescription = null,
                    tint = Colors.Brown60,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

@Composable
fun RowScope.SleepInfo(
    title: String,
    subtitle: StringResource,
    icon: DrawableResource,
) {
    Row(
        modifier = Modifier.weight(1f),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier.size(40.dp).clip(Dimens.Shape.Circle)
                .background(color = Colors.Gray10),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = stringResource(Res.string.start_sleeping),
                tint = Colors.Gray60,
                modifier = Modifier.size(20.dp)
            )
        }
        Column {
            Text(
                text = title,
                style = TextStyles.TextSmSemiBold(),
                color = Colors.Gray80,
            )
            Text(
                text = stringResource(subtitle),
                style = TextStyles.TextSmRegular(),
                color = Colors.Gray60,
            )
        }
    }
}

@Composable
fun SleepQualityMetric(
    records: List<SleepQualityRecord>,
    onCreate: () -> Unit,
    onClick: () -> Unit,
) {
    val today = records.getTodaySleepQualityRecord()
    val resource = today?.sleepQuality?.toResource()

    if (resource == null)
        SleepQualityNotFound(modifier = Modifier.paddingHorizontalMedium(), onClick = onCreate)
    else {
        val duration = Pair(today.endSleeping, today.startSleeping).calculateDuration()
        val durationString = duration.toHoursAndMinutesString()

        GiganticSecondaryCard(
            modifier = Modifier.paddingHorizontalMedium(),
            title = durationString,
            subtitle = stringResource(resource.firstText),
            onClick = onClick,
            secondary = {
                MoodFace(
                    modifier = Modifier.size(40.dp),
                    resource = resource.toMoodResource()
                )
            },
            content = {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        SleepInfo(
                            title = today.startSleeping.toFormattedTimeString(),
                            subtitle = Res.string.bedtime,
                            icon = Drawables.Icons.Moon
                        )
                        SleepInfo(
                            title = today.endSleeping.toFormattedTimeString(),
                            subtitle = Res.string.wake_up,
                            icon = Drawables.Icons.Sun
                        )
                    }
                }
            }
        )
    }
}