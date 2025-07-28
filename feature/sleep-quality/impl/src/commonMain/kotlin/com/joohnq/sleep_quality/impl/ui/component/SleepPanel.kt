package com.joohnq.sleep_quality.impl.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.joohnq.api.entity.Time
import com.joohnq.api.mapper.TimeMapper.calculateDuration
import com.joohnq.api.mapper.TimeMapper.toHoursAndMinutesString
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.spacer.VerticalSpacer
import com.joohnq.shared_resources.current_sleep_quality
import com.joohnq.shared_resources.mood
import com.joohnq.shared_resources.not_available
import com.joohnq.shared_resources.sleep_quality
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.paddingAllSmall
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.shared_resources.time_asleep
import com.joohnq.sleep_quality.impl.ui.mapper.toMoodResource
import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityRecordResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun SleepPanel(
    modifier: Modifier = Modifier,
    record: SleepQualityRecordResource?,
) {
    val hasToday = record != null
    val iconTint = if (hasToday) Colors.White else Colors.Brown80
    val textColor = if (hasToday) Colors.White else Colors.Brown80
    val duration =
        calculateDuration(
            start = record?.startSleeping ?: Time(0, 0),
            end = record?.endSleeping ?: Time(0, 0)
        )
    val moodResource = record?.sleepQuality?.toMoodResource()

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(Drawables.Icons.Outlined.Sleep),
            contentDescription = stringResource(Res.string.sleep_quality),
            modifier = Modifier.size(48.dp),
            tint = iconTint
        )
        if (!hasToday) {
            VerticalSpacer(24.dp)
        }
        Text(
            text = if (hasToday) record.sleepQuality.level.toString() else stringResource(Res.string.not_available),
            style = if (hasToday) TextStyles.displaySmExtraBold() else TextStyles.text2xlBold(),
            color = textColor
        )
        if (!hasToday) {
            VerticalSpacer(10.dp)
        }
        Text(
            text = stringResource(Res.string.current_sleep_quality),
            style = TextStyles.textLgMedium(),
            color = textColor
        )
        VerticalSpacer(24.dp)
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .background(color = Colors.White, shape = Dimens.Shape.Large)
                    .clip(Dimens.Shape.Large)
                    .paddingAllSmall(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            SleepPanelInfo(
                modifier = Modifier.weight(1f),
                icon = Drawables.Icons.Outlined.Sleep,
                title = Res.string.time_asleep,
                value = duration.toHoursAndMinutesString()
            )
            SleepPanelInfoInverted(
                modifier = Modifier.weight(1f),
                icon = Drawables.Icons.Outlined.MoodNeutral,
                title = Res.string.mood,
                value = moodResource?.let { stringResource(it.text) } ?: "..."
            )
        }
    }
}
