package com.joohnq.shared_resources.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.start_sleeping
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.ComponentColors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingAllSmall
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalSmall
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingVerticalExtraLarge
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.sleep_quality.domain.entity.SleepStatsItem
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun MindfulTrackerCardRow(
    icon: DrawableResource,
    color: Color,
    backgroundColor: Color,
    title: StringResource,
    subtitle: StringResource,
    content: @Composable (modifier: Modifier) -> Unit,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth().paddingHorizontalSmall(),
        onClick = onClick,
        shape = Dimens.Shape.Large,
        colors = ComponentColors.Card.MainCardColors(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().paddingAllSmall(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier.background(
                        color = backgroundColor,
                        shape = Dimens.Shape.Large
                    ).size(64.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(icon),
                        contentDescription = stringResource(title),
                        modifier = Modifier.size(Dimens.Icon),
                        tint = color
                    )
                }
                HorizontalSpacer(20.dp)
                Column(modifier = Modifier) {
                    Text(
                        text = stringResource(title),
                        style = TextStyles.TextLgExtraBold(),
                        color = Colors.Brown80
                    )
                    VerticalSpacer(5.dp)
                    Text(
                        text = stringResource(subtitle),
                        style = TextStyles.TextSmSemiBold(),
                        color = Colors.Brown100Alpha64
                    )
                }
            }
            Box(modifier = Modifier) {
                content(Modifier)
            }
        }
    }
}

@Composable
fun MindfulTrackerCardColumn(
    icon: DrawableResource,
    color: Color,
    backgroundColor: Color,
    title: StringResource,
    subtitle: StringResource? = null,
    content: @Composable () -> Unit,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth().paddingHorizontalSmall(),
        onClick = onClick,
        shape = Dimens.Shape.Large,
        colors = ComponentColors.Card.MainCardColors(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().paddingAllSmall(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier.background(
                    color = backgroundColor,
                    shape = Dimens.Shape.Large
                ).size(64.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(icon),
                    contentDescription = stringResource(title),
                    modifier = Modifier.size(Dimens.Icon),
                    tint = color
                )
            }
            HorizontalSpacer(20.dp)
            Column {
                Text(
                    text = stringResource(title),
                    style = TextStyles.TextLgExtraBold(),
                    color = Colors.Brown80
                )
                VerticalSpacer(8.dp)
                Box(modifier = Modifier.fillMaxWidth()) {
                    content()
                }
                subtitle?.let {
                    VerticalSpacer(8.dp)
                    Text(
                        text = stringResource(subtitle),
                        style = TextStyles.TextSmSemiBold(),
                        color = Colors.Brown100Alpha64
                    )
                }
            }
        }
    }
}

@Composable
fun StressLevelCard(
    modifier: Modifier = Modifier,
    icon: DrawableResource,
    title: StringResource,
    value: String,
    content: @Composable () -> Unit,
) {
    Card(
        colors = ComponentColors.Card.StressLevelCardColors(),
        shape = Dimens.Shape.ExtraLarge,
        modifier = modifier
    ) {
        Column(modifier = Modifier.fillMaxWidth().paddingVerticalExtraLarge()) {
            Row(
                modifier = Modifier.fillMaxWidth().paddingHorizontalMedium(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Icon(
                    painter = painterResource(icon),
                    contentDescription = stringResource(title),
                    tint = Colors.Brown80,
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = stringResource(title),
                    style = TextStyles.TextMdExtraBold(),
                    color = Colors.Brown80
                )
            }
            VerticalSpacer(12.dp)
            Text(
                text = value,
                style = TextStyles.TextLgSemiBold(),
                color = Colors.Brown100Alpha64,
                modifier = Modifier.paddingHorizontalMedium()
            )
            VerticalSpacer(20.dp)
            content()
        }
    }
}

@Composable
fun TimePickerCard(
    modifier: Modifier = Modifier,
    title: StringResource,
    hour: String,
    minutes: String,
    isAfternoon: Boolean,
    onClick: () -> Unit = {},
) {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp), modifier = modifier) {
        Text(
            text = stringResource(title),
            style = TextStyles.TextLgExtraBold(),
            color = Colors.Brown80
        )
        Card(
            colors = ComponentColors.Card.MainCardColors(),
            shape = Dimens.Shape.Circle,
            onClick = onClick,
            modifier = Modifier.height(56.dp)
        ) {
            Row(
                modifier = Modifier.padding(10.dp).fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(Drawables.Icons.Calendar),
                        contentDescription = stringResource(Res.string.start_sleeping),
                        tint = Colors.Brown80,
                        modifier = Modifier.size(24.dp)
                    )
                    HorizontalSpacer(15.dp)
                    Text(
                        text = "$hour:$minutes",
                        style = TextStyles.TextMdBold(),
                        color = Colors.Brown100Alpha64
                    )
                    Text(
                        text = if (isAfternoon) " PM" else " AM",
                        style = TextStyles.TextMdBold(),
                        color = Colors.Brown100Alpha64
                    )
                }
                HorizontalSpacer(10.dp)
                Icon(
                    painter = painterResource(Drawables.Icons.ArrowOpen),
                    contentDescription = stringResource(Res.string.start_sleeping),
                    tint = Colors.Brown80,
                    modifier = Modifier.size(18.dp).rotate(90f)
                )
            }
        }
    }
}

@Composable
fun SleepQualityCard(modifier: Modifier = Modifier, item: SleepStatsItem) {
    Card(
        shape = Dimens.Shape.Large,
        colors = ComponentColors.Card.MainCardColors(),
        modifier = modifier
    ) {
        Column(Modifier.paddingAllSmall()) {
            Box(
                modifier = Modifier.size(40.dp)
                    .background(
                        color = Colors.Brown10,
                        shape = Dimens.Shape.ExtraSmall
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(item.icon),
                    contentDescription = stringResource(item.title),
                    tint = Colors.Brown80,
                    modifier = Modifier.size(24.dp)
                )
            }
            VerticalSpacer(16.dp)
            Text(
                text = stringResource(item.title),
                style = TextStyles.TextMdBold(),
                color = Colors.Brown80
            )
            VerticalSpacer(5.dp)
            Box(modifier = Modifier.fillMaxWidth()) {
                item.content()
            }
        }
    }
}

