package com.joohnq.overview.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.api.mapper.LocalDateTimeMapper.toFormattedTimeString
import com.joohnq.mood.add.ui.components.MoodFace
import com.joohnq.mood.add.ui.resource.MoodRecordResource
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.spacer.VerticalSpacer
import com.joohnq.shared_resources.current_mood
import com.joohnq.shared_resources.logged_today_at
import com.joohnq.shared_resources.mood
import com.joohnq.shared_resources.not_available
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.shared_resources.your_mood_is
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun MoodOverviewContentPanel(
    modifier: Modifier = Modifier,
    item: MoodRecordResource?,
) {
    val hasToday = item != null
    val iconTint = if (hasToday) Colors.White else Colors.Brown80
    val textColor = if (hasToday) Colors.White else Colors.Brown80

    Column(
        modifier =
            modifier
                .fillMaxWidth()
                .paddingHorizontalMedium(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (!hasToday) {
            Icon(
                painter = painterResource(Drawables.Icons.Outlined.MoodNeutral),
                contentDescription = stringResource(Res.string.mood),
                modifier = Modifier.size(48.dp),
                tint = iconTint
            )
        }
        if (!hasToday) {
            VerticalSpacer(24.dp)
        }
        Text(
            text = if (hasToday) stringResource(Res.string.your_mood_is) else stringResource(Res.string.not_available),
            style = TextStyles.textXlBold(),
            color = textColor
        )
        if (!hasToday) {
            VerticalSpacer(10.dp)
        }
        if (hasToday) {
            Text(
                text = stringResource(item.mood.text),
                style = TextStyles.headingXlExtraBold(),
                color = Colors.White
            )
            VerticalSpacer(10.dp)
            MoodFace(
                modifier = Modifier.size(96.dp),
                resource = item.mood,
                backgroundColor = Colors.White,
                color = item.mood.palette.color
            )
            VerticalSpacer(10.dp)
            Text(
                text =
                    stringResource(
                        Res.string.logged_today_at,
                        item.createdAt.toFormattedTimeString()
                    ),
                style = TextStyles.textXlMedium(),
                color = Colors.White
            )
        }
        if (!hasToday) {
            VerticalSpacer(10.dp)
            Text(
                text = stringResource(Res.string.current_mood),
                style = TextStyles.textLgMedium(),
                color = textColor
            )
        }
    }
}
