package com.joohnq.sleep_quality.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.joohnq.core.ui.entity.Time
import com.joohnq.core.ui.mapper.calculateDuration
import com.joohnq.core.ui.mapper.toHoursAndMinutesString
import com.joohnq.shared_resources.*
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.sleep_quality.domain.entity.SleepQualityRecord
import com.joohnq.sleep_quality.ui.mapper.toMoodResource
import com.joohnq.sleep_quality.ui.resource.SleepQualityResource
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun SleepPanelInfo(
    modifier: Modifier = Modifier,
    hasToday: Boolean,
    icon: DrawableResource,
    title: StringResource,
    value: String
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(
            8.dp,
            alignment = Alignment.Start
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(Dimens.Shape.Circle)
                .border(
                    width = 1.dp,
                    color = Colors.Gray30,
                    shape = Dimens.Shape.Circle
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = stringResource(title),
                tint = if (hasToday) Colors.White else Colors.Brown80,
                modifier = Modifier.size(24.dp)
            )
        }
        Column {
            Text(
                text = stringResource(title),
                style = TextStyles.TextSmMedium(),
                color = if (hasToday) Colors.White else Colors.Gray60
            )
            Text(
                text = value,
                style = TextStyles.TextLgSemiBold(),
                color = if (hasToday) Colors.White else Colors.Gray80
            )
        }
    }
}

@Composable
fun SleepPanelInfoInverted(
    modifier: Modifier = Modifier,
    hasToday: Boolean,
    icon: DrawableResource,
    title: StringResource,
    value: String
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(
            8.dp,
            alignment = Alignment.End
        ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = stringResource(title),
                style = TextStyles.TextSmMedium(),
                color = if (hasToday) Colors.White else Colors.Gray60
            )
            Text(
                text = value,
                style = TextStyles.TextLgSemiBold(),
                color = if (hasToday) Colors.White else Colors.Gray80
            )
        }
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(Dimens.Shape.Circle)
                .border(
                    width = 1.dp,
                    color = Colors.Gray30,
                    shape = Dimens.Shape.Circle
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = stringResource(title),
                tint = if (hasToday) Colors.White else Colors.Brown80,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun SleepPanel(
    modifier: Modifier = Modifier,
    record: SleepQualityRecord?,
    resource: SleepQualityResource?,
) {
    val hasToday = resource != null
    val iconTint = if (hasToday) Colors.White else Colors.Brown80
    val textColor = if (hasToday) Colors.White else Colors.Brown80
    val duration = Pair(record?.startSleeping ?: Time(0, 0), record?.endSleeping ?: Time(0, 0)).calculateDuration()
    val moodResource = resource?.toMoodResource()

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(Drawables.Icons.Sleep),
            contentDescription = stringResource(Res.string.sleep_quality),
            modifier = Modifier.size(48.dp),
            tint = iconTint
        )
        if (!hasToday)
            VerticalSpacer(24.dp)
        Text(
            text = if (hasToday) resource.level.toString() else stringResource(Res.string.not_available),
            style = if (hasToday) TextStyles.DisplaySmExtraBold() else TextStyles.Text2xlBold(),
            color = textColor
        )
        if (!hasToday)
            VerticalSpacer(10.dp)
        Text(
            text = stringResource(Res.string.current_sleep_quality),
            style = TextStyles.TextLgMedium(),
            color = textColor
        )
        VerticalSpacer(24.dp)
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            SleepPanelInfo(
                modifier = Modifier.weight(1f),
                hasToday = hasToday,
                icon = Drawables.Icons.Sleep,
                title = Res.string.time_asleep,
                value = duration.toHoursAndMinutesString()
            )
            SleepPanelInfoInverted(
                modifier = Modifier.weight(1f),
                hasToday = hasToday,
                icon = Drawables.Icons.Outlined.MoodNeutral,
                title = Res.string.mood,
                value = moodResource?.let { stringResource(it.text) } ?: ".."
            )
        }
    }
}