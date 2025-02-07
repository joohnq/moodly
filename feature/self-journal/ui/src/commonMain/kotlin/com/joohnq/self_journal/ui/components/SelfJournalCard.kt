package com.joohnq.self_journal.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.core.ui.mapper.toFormattedTimeString
import com.joohnq.mood.ui.components.MoodFace
import com.joohnq.mood.ui.mapper.toResource
import com.joohnq.self_journal.domain.entity.SelfJournalRecord
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.SwipeTorRevealCard
import com.joohnq.shared_resources.components.TextEllipsis
import com.joohnq.shared_resources.components.TextWithBackground
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.hour
import com.joohnq.shared_resources.theme.*
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingAllSmall
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun SelfJournalCard(
    isNotFirst: Boolean,
    isNotLast: Boolean,
    record: SelfJournalRecord,
    onClick: (Int) -> Unit,
    onDelete: () -> Unit,
) {
    val resource = record.mood.toResource()

    SwipeTorRevealCard(
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
                                    color = resource.palette.color,
                                    shape = Dimens.Shape.Circle
                                )
                                .size(44.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            MoodFace(
                                modifier = Modifier.size(20.dp),
                                resource = resource,
                                backgroundColor = Colors.White,
                                color = resource.palette.barFaceColor
                            )
                        }
                        TextWithBackground(
                            text = stringResource(resource.text),
                            backgroundColor = resource.palette.backgroundColor,
                            textColor = resource.palette.color
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
                        painter = painterResource(Drawables.Icons.Clock),
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