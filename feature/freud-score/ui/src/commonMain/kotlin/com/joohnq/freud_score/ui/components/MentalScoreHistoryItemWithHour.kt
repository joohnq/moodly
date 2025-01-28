package com.joohnq.freud_score.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.core.ui.mapper.getDayPeriod
import com.joohnq.core.ui.mapper.toFormattedTimeString
import com.joohnq.mood.ui.resource.MoodResource
import com.joohnq.shared_resources.components.CircularProgressWithText
import com.joohnq.shared_resources.components.HorizontalSpacer
import com.joohnq.shared_resources.components.TextEllipsis
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.ComponentColors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import kotlinx.datetime.LocalDateTime
import org.jetbrains.compose.resources.stringResource

@Composable
fun MentalScoreHistoryItemWithHour(
    date: LocalDateTime,
    resource: MoodResource,
    description: String,
    healthLevel: Int,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth().paddingHorizontalMedium(),
        colors = ComponentColors.Card.MainCardColors(),
        shape = Dimens.Shape.Medium,
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
                    .background(color = Colors.White, shape = Dimens.Shape.ExtraSmall),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = date.getDayPeriod(),
                    style = TextStyles.LabelSm(),
                    color = Colors.Brown100Alpha64
                )
                Text(
                    text = date.toFormattedTimeString(),
                    style = TextStyles.TextLgExtraBold(),
                    color = Colors.Brown80
                )
            }
            HorizontalSpacer(20.dp)
            Column(
                modifier = Modifier.fillMaxHeight().weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(resource.text),
                    style = TextStyles.TextMdBold(),
                    color = Colors.Brown80
                )
                VerticalSpacer(5.dp)
                TextEllipsis(
                    text = description,
                    style = TextStyles.TextSmSemiBold(),
                    color = Colors.Brown100Alpha64,
                )
            }
            HorizontalSpacer(20.dp)
            CircularProgressWithText(
                modifier = Modifier.size(64.dp),
                text = healthLevel.toString(),
                textStyle = TextStyles.TextXsBold(),
                textColor = Colors.Brown80,
                color = resource.palette.color,
                backgroundColor = resource.palette.backgroundColor,
                progress = { healthLevel / 100f },
            )
        }
    }
}

