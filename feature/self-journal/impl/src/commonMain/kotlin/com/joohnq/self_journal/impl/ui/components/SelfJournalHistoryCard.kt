package com.joohnq.self_journal.impl.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.api.mapper.toCompleteDateString
import com.joohnq.api.mapper.toFormattedTimeString
import com.joohnq.mood.impl.ui.components.MoodFace
import com.joohnq.self_journal.impl.ui.resource.SelfJournalRecordResource
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.SwipeableCardLayout
import com.joohnq.shared_resources.components.TextEllipsis
import com.joohnq.shared_resources.components.TextWithBackground
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.hour
import com.joohnq.shared_resources.mood_show
import com.joohnq.shared_resources.theme.*
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingAllSmall
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun SelfJournalHistoryCard(
    record: SelfJournalRecordResource,
    onClick: () -> Unit = {},
) {
    Card(
        modifier = Modifier.width(220.dp).height(250.dp),
        colors = ComponentColors.Card.MainCardColors(),
        shape = Dimens.Shape.Large,
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.fillMaxSize().paddingAllSmall(),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier.size(48.dp).background(
                        color = record.mood.palette.color,
                        shape = Dimens.Shape.Small
                    ),
                    contentAlignment = Alignment.Center
                ) {
                    MoodFace(
                        resource = record.mood,
                        modifier = Modifier.size(24.dp),
                        backgroundColor = Colors.White,
                        color = record.mood.palette.color
                    )
                }
                Text(
                    text = record.createdAt.date.toCompleteDateString(),
                    style = TextStyles.TextSmSemiBold(),
                    color = Colors.Brown80
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                TextWithBackground(
                    text = stringResource(
                        Res.string.mood_show,
                        stringResource(record.mood.text)
                    ).uppercase(),
                    backgroundColor = record.mood.palette.backgroundColor,
                    textColor = record.mood.palette.color
                )
                TextEllipsis(
                    text = record.title,
                    style = TextStyles.TextLgBold(),
                    color = Colors.Brown80
                )
                TextEllipsis(
                    text = record.description,
                    style = TextStyles.TextSmSemiBold(),
                    color = Colors.Brown100Alpha64
                )
            }
        }
    }
}

@Composable
fun SelfJournalHistoryCard(
    modifier: Modifier = Modifier,
    isNotFirst: Boolean,
    isNotLast: Boolean,
    record: SelfJournalRecordResource,
    onClick: (Int) -> Unit,
    onDelete: () -> Unit,
) {
    SwipeableCardLayout(
        modifier = modifier.background(color = Colors.White, shape = Dimens.Shape.Large),
        content = { modifier ->
            Card(
                shape = Dimens.Shape.Large,
                modifier = modifier,
                colors = ComponentColors.Card.MainCardColors(),
                onClick = {
                    onClick(record.id)
                }
            ) {
                Column(modifier = Modifier.paddingAllSmall()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box(
                            modifier = Modifier
                                .background(
                                    color = record.mood.palette.color,
                                    shape = Dimens.Shape.Circle
                                )
                                .size(44.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            MoodFace(
                                modifier = Modifier.size(20.dp),
                                resource = record.mood,
                                backgroundColor = Colors.White,
                                color = record.mood.palette.barFaceColor
                            )
                        }
                        TextWithBackground(
                            text = stringResource(record.mood.text),
                            backgroundColor = record.mood.palette.backgroundColor,
                            textColor = record.mood.palette.color
                        )
                    }
                    VerticalSpacer(10.dp)
                    TextEllipsis(
                        text = record.title,
                        style = TextStyles.TextMdExtraBold(),
                        color = Colors.Brown80,
                        maxLines = 1
                    )
                    VerticalSpacer(10.dp)
                    TextEllipsis(
                        text = record.description,
                        style = TextStyles.TextMdSemiBold(),
                        color = Colors.Brown100Alpha64,
                        maxLines = 2
                    )
                }
            }
        },
        secondary = {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier.width(3.dp).weight(1f).fillMaxHeight()
                        .background(color = if (isNotFirst) Colors.Brown80 else Colors.Transparent)
                )
                Column(
                    modifier = Modifier.size(50.dp)
                        .background(
                            color = Colors.Brown80,
                            shape = Dimens.Shape.ExtraSmall
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        painter = painterResource(Drawables.Icons.Filled.Clock),
                        contentDescription = stringResource(Res.string.hour),
                        tint = Colors.White,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = record.createdAt.toFormattedTimeString(),
                        style = TextStyles.TextSmSemiBold(),
                        color = Colors.White
                    )
                }
                Box(
                    modifier = Modifier.width(3.dp).weight(1f).fillMaxHeight()
                        .background(color = if (isNotLast) Colors.Brown80 else Colors.Transparent)
                )
            }
        },
        padding = PaddingValues(end = 10.dp, top = 5.dp, bottom = 5.dp),
        onAction = onDelete
    )
}