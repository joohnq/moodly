package com.joohnq.self_journal.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.unit.dp
import com.joohnq.api.mapper.LocalDateMapper.toCompleteDateString
import com.joohnq.api.mapper.LocalDateTimeMapper.toFormattedTimeString
import com.joohnq.mood.add.ui.components.MoodFace
import com.joohnq.self_journal.impl.ui.resource.SelfJournalRecordResource
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.layout.CardWithMoreMenuLayout
import com.joohnq.shared_resources.components.spacer.VerticalSpacer
import com.joohnq.shared_resources.components.text.TextEllipsis
import com.joohnq.shared_resources.components.text.TextWithBackground
import com.joohnq.shared_resources.hour
import com.joohnq.shared_resources.mood_show
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.ComponentColors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.paddingAllSmall
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun SelfJournalHistoryCard(
    item: SelfJournalRecordResource,
    onClick: () -> Unit = {},
) {
    Card(
        modifier = Modifier.width(220.dp).height(250.dp),
        colors = ComponentColors.Card.mainCardColors(),
        shape = Dimens.Shape.Large,
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.fillMaxSize().paddingAllSmall(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier =
                        Modifier.size(48.dp).background(
                            color = item.mood.palette.color,
                            shape = Dimens.Shape.Small
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    MoodFace(
                        resource = item.mood,
                        modifier = Modifier.size(24.dp),
                        backgroundColor = Colors.White,
                        color = item.mood.palette.color
                    )
                }
                Text(
                    text = item.createdAt.date.toCompleteDateString(),
                    style = TextStyles.textSmSemiBold(),
                    color = Colors.Brown80
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                TextWithBackground(
                    text =
                        stringResource(
                            Res.string.mood_show,
                            stringResource(item.mood.text)
                        ).uppercase(),
                    backgroundColor = item.mood.palette.backgroundColor,
                    textColor = item.mood.palette.color
                )
                TextEllipsis(
                    text = item.title,
                    style = TextStyles.textLgBold(),
                    color = Colors.Brown80
                )
                TextEllipsis(
                    text = item.description,
                    style = TextStyles.textSmSemiBold(),
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
    item: SelfJournalRecordResource,
    onClick: (Long) -> Unit = {},
    onDelete: (Long) -> Unit = {},
) {
    CardWithMoreMenuLayout(
        modifier = modifier.background(color = Colors.White, shape = Dimens.Shape.Large),
        onDelete = { onDelete(item.id) },
        onClick = { onClick(item.id) },
        content = { modifier ->
            Column(modifier = Modifier.paddingAllSmall()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier =
                            Modifier
                                .background(
                                    color = item.mood.palette.color,
                                    shape = Dimens.Shape.Circle
                                ).size(44.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        MoodFace(
                            modifier = Modifier.size(20.dp),
                            resource = item.mood,
                            backgroundColor = Colors.White,
                            color = item.mood.palette.barFaceColor
                        )
                    }
                    TextWithBackground(
                        text = stringResource(item.mood.text),
                        backgroundColor = item.mood.palette.backgroundColor,
                        textColor = item.mood.palette.color
                    )
                }
                VerticalSpacer(10.dp)
                TextEllipsis(
                    text = item.title,
                    style = TextStyles.textMdExtraBold(),
                    color = Colors.Brown80,
                    maxLines = 1
                )
                VerticalSpacer(10.dp)
                TextEllipsis(
                    text = item.description,
                    style = TextStyles.textMdSemiBold(),
                    color = Colors.Brown100Alpha64,
                    maxLines = 2
                )
            }
        },
        secondary = {
            Column(
                modifier =
                    Modifier
                        .fillMaxHeight()
                        .padding(horizontal = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier =
                        Modifier
                            .width(3.dp)
                            .weight(1f)
                            .fillMaxHeight()
                            .background(color = if (isNotFirst) Colors.Brown80 else Colors.Transparent)
                )
                Column(
                    modifier =
                        Modifier
                            .size(50.dp)
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
                        text = item.createdAt.toFormattedTimeString(),
                        style = TextStyles.textSmSemiBold(),
                        color = Colors.White
                    )
                }
                Box(
                    modifier =
                        Modifier
                            .width(3.dp)
                            .weight(1f)
                            .fillMaxHeight()
                            .background(color = if (isNotLast) Colors.Brown80 else Colors.Transparent)
                )
            }
        }
//        padding = PaddingValues(end = 10.dp, top = 5.dp, bottom = 5.dp),
    )
}
