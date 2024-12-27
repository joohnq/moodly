package com.joohnq.mood.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.joohnq.domain.entity.StatsRecord
import com.joohnq.domain.entity.TextRadioButtonColors
import com.joohnq.mood.theme.Colors
import com.joohnq.mood.theme.ComponentColors
import com.joohnq.mood.theme.Dimens
import com.joohnq.mood.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.mood.theme.TextStyles
import com.joohnq.mood.util.helper.DatetimeManager
import com.joohnq.stress_level.domain.entity.Stressor
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
        colors = ComponentColors.Card.MentalHealthMetricColors(backgroundColor),
        shape = Dimens.Shape.Medium
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(20.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(icon),
                    contentDescription = stringResource(title),
                    tint = Colors.White,
                    modifier = Modifier.size(Dimens.Icon)
                )
                HorizontalSpacer(10.dp)
                Text(
                    text = stringResource(title),
                    style = TextStyles.TextMdBold(),
                    color = Colors.White
                )
            }
            VerticalSpacer(24.dp)
            content(
                Modifier.heightIn(min = 130.dp, max = 140.dp)
                    .widthIn(min = 130.dp, max = 140.dp).fillMaxSize()
            )
        }
    }
}

@Composable
fun MentalScoreHistoryItemWithHour(
    statsRecord: StatsRecord,
    onClick: () -> Unit,
) {
    val hourAndMinutes = remember { DatetimeManager.formatTime(statsRecord.date) }
    val daySection = remember { DatetimeManager.getDaySection(statsRecord.date) }

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
                    text = stringResource(statsRecord.mood.text),
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
                color = statsRecord.mood.palette.color,
                backgroundColor = statsRecord.mood.palette.backgroundColor,
                progress = { statsRecord.mood.healthLevel / 100f },
            )
        }
    }
}

@Composable
fun StressStressorCircle(
    stressStressor: Stressor,
    selected: Boolean,
    onClick: () -> Unit = {}
) {
    val fontSize = 18.sp
    val text = stringResource(stressStressor.text)
    val padding = 24.dp
    val size = CalculateTextWidth(text, fontSize)

    Box(modifier = Modifier.size(size + padding)) {
        TextRadioButton(
            onClick = onClick,
            modifier = Modifier
                .size(size + padding),
            colors = TextRadioButtonColors(
                selectedBackgroundColor = Colors.Green50,
                selectedContentColor = Colors.White,
                selectedBorderColor = Colors.Green50Alpha25,
                unSelectedBackgroundColor = Colors.Brown20,
                unSelectedContentColor = Colors.Brown40
            ),
            contentPaddingValues = PaddingValues(0.dp),
            text = text,
            selected = selected,
            shape = Dimens.Shape.Circle
        )
    }
}