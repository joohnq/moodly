package com.joohnq.sleep_quality.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.joohnq.core.ui.getNow
import com.joohnq.core.ui.mapper.calculateDuration
import com.joohnq.core.ui.mapper.toFormattedTimeString
import com.joohnq.core.ui.mapper.toHoursAndMinutesString
import com.joohnq.mood.ui.components.MoodFace
import com.joohnq.shared_resources.*
import com.joohnq.shared_resources.components.GiganticSecondaryCard
import com.joohnq.shared_resources.components.NotFoundVertical
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.sleep_quality.ui.mapper.getTodaySleepQualityRecord
import com.joohnq.sleep_quality.ui.mapper.toMoodResource
import com.joohnq.sleep_quality.ui.resource.SleepQualityRecordResource
import kotlinx.datetime.LocalDate
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SleepQualityMetric(
    records: List<SleepQualityRecordResource>,
    containerColor: Color = Colors.White,
    onCreate: () -> Unit = {},
    onClick: () -> Unit = {},
) {
    val record = records.getTodaySleepQualityRecord()

    if (record == null)
        NotFoundVertical(
            modifier = Modifier.paddingHorizontalMedium(),
            containerColor = containerColor,
            image = Drawables.Images.SleepWomanIllustration,
            title = Res.string.you_havent_set_up_any_mental_sleep_yet,
            subtitle = Res.string.set_up_sleep,
            onClick = onCreate
        )
    else {
        val duration = Pair(record.endSleeping, record.startSleeping).calculateDuration()
        val durationString = duration.toHoursAndMinutesString()

        GiganticSecondaryCard(
            modifier = Modifier.paddingHorizontalMedium(),
            containerColor = containerColor,
            title = durationString,
            subtitle = stringResource(record.sleepQuality.firstText),
            onClick = onClick,
            secondary = {
                MoodFace(
                    modifier = Modifier.size(40.dp),
                    resource = record.sleepQuality.toMoodResource()
                )
            },
            content = {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        SleepInfo(
                            title = record.startSleeping.toFormattedTimeString(),
                            subtitle = Res.string.bedtime,
                            icon = Drawables.Icons.Moon
                        )
                        SleepInfo(
                            title = record.endSleeping.toFormattedTimeString(),
                            subtitle = Res.string.wake_up,
                            icon = Drawables.Icons.Sun
                        )
                    }
                }
            }
        )
    }
}

@Preview
@Composable
fun SleepQualityMetricPreviewToday() {
    SleepQualityMetric(
        records = listOf(
            SleepQualityRecordResource()
        ),
    )
}


@Preview
@Composable
fun SleepQualityMetricPreviewYesterday() {
    val now = getNow()
    SleepQualityMetric(
        records = listOf(
            SleepQualityRecordResource(
                createdAt = LocalDate(now.year, now.month, now.dayOfMonth.minus(1)),
            )
        ),
    )
}