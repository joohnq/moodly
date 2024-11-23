package com.joohnq.moodapp.ui.components

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
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.domain.HealthJournalRecord
import com.joohnq.moodapp.domain.SleepStatsItem
import com.joohnq.moodapp.ui.theme.Colors
import com.joohnq.moodapp.ui.theme.ComponentColors
import com.joohnq.moodapp.ui.theme.Dimens
import com.joohnq.moodapp.ui.theme.Drawables
import com.joohnq.moodapp.ui.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.moodapp.ui.theme.PaddingModifier.Companion.paddingHorizontalSmall
import com.joohnq.moodapp.ui.theme.PaddingModifier.Companion.paddingVerticalExtraLarge
import com.joohnq.moodapp.ui.theme.TextStyles
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.mood_show
import moodapp.composeapp.generated.resources.start_sleeping
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
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth().paddingHorizontalSmall(),
        onClick = onClick,
        shape = Dimens.Shape.Large,
        colors = ComponentColors.Card.MainCardColors(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
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
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth().paddingHorizontalSmall(),
        onClick = onClick,
        shape = Dimens.Shape.Large,
        colors = ComponentColors.Card.MainCardColors(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
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
    content: @Composable () -> Unit
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
        Column(Modifier.padding(16.dp)) {
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

@Composable
fun HealthJournalCard(journal: HealthJournalRecord, onClick: () -> Unit) {
    val mood = journal.mood
    val palette = mood.palette
    Card(
        modifier = Modifier.width(220.dp).height(250.dp),
        colors = ComponentColors.Card.MainCardColors(),
        shape = Dimens.Shape.Large,
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier.size(48.dp).background(
                        color = palette.color,
                        shape = Dimens.Shape.Small
                    ),
                    contentAlignment = Alignment.Center
                ) {
                    MoodFace(
                        mood = mood,
                        modifier = Modifier.size(24.dp),
                        backgroundColor = Colors.White,
                        color = palette.color
                    )
                }
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                TextWithBackground(
                    text = stringResource(
                        Res.string.mood_show,
                        stringResource(mood.text)
                    ),
                    backgroundColor = palette.backgroundColor,
                    textColor = palette.color
                )
                TextEllipsis(
                    text = journal.title,
                    style = TextStyles.TextLgBold(),
                    color = Colors.Brown80
                )
                TextEllipsis(
                    text = journal.description,
                    style = TextStyles.TextSmSemiBold(),
                    color = Colors.Brown100Alpha64
                )
            }
        }
    }
}

@Composable
fun HealthJournalStatsCard(
    modifier: Modifier = Modifier,
    icon: DrawableResource,
    title: String,
    color: Color,
    backgroundColor: Color,
    desc: String
) {
    Card(
        modifier = modifier,
        colors = ComponentColors.Card.MainCardColors(),
        shape = Dimens.Shape.Large
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(
                modifier = Modifier.size(48.dp)
                    .background(color = backgroundColor, shape = Dimens.Shape.Small),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(icon),
                    contentDescription = desc,
                    tint = color,
                    modifier = Modifier.size(24.dp)
                )
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    text = title,
                    style = TextStyles.TextMdExtraBold(),
                    color = Colors.Brown80
                )
                Text(
                    text = desc,
                    style = TextStyles.TextSmSemiBold(),
                    color = Colors.Brown100Alpha64
                )
            }
        }
    }
}

