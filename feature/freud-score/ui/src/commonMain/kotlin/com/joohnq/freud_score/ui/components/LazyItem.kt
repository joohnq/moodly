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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.mood.domain.entity.StatsRecord
import com.joohnq.mood.ui.MoodResource.Companion.toResource
import com.joohnq.shared.domain.DatetimeProvider
import com.joohnq.shared.ui.components.CircularProgressWithText
import com.joohnq.shared.ui.components.HorizontalSpacer
import com.joohnq.shared.ui.components.TextEllipsis
import com.joohnq.shared.ui.components.VerticalSpacer
import com.joohnq.shared.ui.theme.Colors
import com.joohnq.shared.ui.theme.ComponentColors
import com.joohnq.shared.ui.theme.Dimens
import com.joohnq.shared.ui.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared.ui.theme.TextStyles
import org.jetbrains.compose.resources.stringResource

@Composable
fun MentalScoreHistoryItemWithHour(
    statsRecord: StatsRecord,
    onClick: () -> Unit,
) {
    val hourAndMinutes = remember { DatetimeProvider.formatTime(statsRecord.date) }
    val daySection = remember { DatetimeProvider.getDaySection(statsRecord.date) }
    val resource = statsRecord.mood.toResource()

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
                    text = daySection,
                    style = TextStyles.LabelSm(),
                    color = Colors.Brown100Alpha64
                )
                Text(
                    text = hourAndMinutes,
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
                    text = statsRecord.description,
                    style = TextStyles.TextSmSemiBold(),
                    color = Colors.Brown100Alpha64,
                )
            }
            HorizontalSpacer(20.dp)
            CircularProgressWithText(
                modifier = Modifier.size(64.dp),
                text = statsRecord.mood.healthLevel.toString(),
                textStyle = TextStyles.TextXsBold(),
                textColor = Colors.Brown80,
                color = resource.palette.color,
                backgroundColor = resource.palette.backgroundColor,
                progress = { statsRecord.mood.healthLevel / 100f },
            )
        }
    }
}

