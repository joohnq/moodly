package com.joohnq.sleep_quality.impl.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.api.mapper.calculateDuration
import com.joohnq.api.mapper.toFormattedTimeString
import com.joohnq.api.mapper.toHoursAndMinutesString
import com.joohnq.mood.impl.ui.components.MoodFace
import com.joohnq.shared_resources.*
import com.joohnq.shared_resources.components.card.GiganticSecondaryCard
import com.joohnq.shared_resources.components.layout.NotFoundVerticalLayout
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.sleep_quality.impl.ui.mapper.getTodaySleepQualityRecord
import com.joohnq.sleep_quality.impl.ui.mapper.toMoodResource
import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityRecordResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun SleepQualityMetric(
    records: List<SleepQualityRecordResource>,
    onCreate: () -> Unit = {},
    onClick: () -> Unit = {},
) {
    val record = records.getTodaySleepQualityRecord()

    if (record == null) {
        NotFoundVerticalLayout(
            modifier = Modifier.paddingHorizontalMedium(),
            containerColor = Colors.White,
            image = Drawables.Images.SleepQualityCreate,
            title = Res.string.you_havent_set_up_any_mental_sleep_yet,
            subtitle = Res.string.set_up_sleep,
            onClick = onCreate
        )
    } else {
        val duration =
            calculateDuration(
                start = record.startSleeping,
                end = record.endSleeping
            )
        val durationString = duration.toHoursAndMinutesString()

        GiganticSecondaryCard(
            modifier = Modifier.paddingHorizontalMedium(),
            containerColor = Colors.White,
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
                            icon = Drawables.Icons.Outlined.Moon
                        )
                        SleepInfo(
                            title = record.endSleeping.toFormattedTimeString(),
                            subtitle = Res.string.wake_up,
                            icon = Drawables.Icons.Outlined.Sun
                        )
                    }
                }
            }
        )
    }
}
