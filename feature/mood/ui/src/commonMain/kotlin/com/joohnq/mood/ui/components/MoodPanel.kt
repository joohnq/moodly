package com.joohnq.mood.ui.components

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
import com.joohnq.domain.mapper.toFormattedTimeString
import com.joohnq.mood.ui.resource.MoodRecordResource
import com.joohnq.shared_resources.*
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun MoodPanel(
    modifier: Modifier = Modifier,
    record: MoodRecordResource?,
) {
    val hasToday = record != null
    val iconTint = if (hasToday) Colors.White else Colors.Brown80
    val textColor = if (hasToday) Colors.White else Colors.Brown80

    Column(
        modifier = modifier
            .fillMaxWidth()
            .paddingHorizontalMedium(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (!hasToday)
            Icon(
                painter = painterResource(Drawables.Icons.Outlined.MoodNeutral),
                contentDescription = stringResource(Res.string.mood),
                modifier = Modifier.size(48.dp),
                tint = iconTint
            )
        if (!hasToday)
            VerticalSpacer(24.dp)
        Text(
            text = if (hasToday) stringResource(Res.string.your_mood_is) else stringResource(Res.string.not_available),
            style = TextStyles.TextXlBold(),
            color = textColor
        )
        if (!hasToday)
            VerticalSpacer(10.dp)
        if (hasToday) {
            Text(
                text = stringResource(record.mood.text),
                style = TextStyles.HeadingXlExtraBold(),
                color = Colors.White
            )
            VerticalSpacer(10.dp)
            MoodFace(
                modifier = Modifier.size(96.dp),
                resource = record.mood,
                backgroundColor = Colors.White,
                color = record.mood.palette.color
            )
            VerticalSpacer(10.dp)
            Text(
                text = stringResource(
                    Res.string.logged_today_at,
                    record.createdAt.toFormattedTimeString()
                ),
                style = TextStyles.TextXlMedium(),
                color = Colors.White
            )
        }
        if (!hasToday) {
            VerticalSpacer(10.dp)
            Text(
                text = stringResource(Res.string.current_mood),
                style = TextStyles.TextLgMedium(),
                color = textColor
            )
        }
    }
}