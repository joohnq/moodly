package com.joohnq.self_journal.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.joohnq.core.ui.mapper.toCompleteDateString
import com.joohnq.mood.ui.components.MoodFace
import com.joohnq.self_journal.ui.resource.SelfJournalRecordResource
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.TextEllipsis
import com.joohnq.shared_resources.components.TextWithBackground
import com.joohnq.shared_resources.mood_show
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.ComponentColors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingAllSmall
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun SelfJournalHistoryCard(
    record: SelfJournalRecordResource,
    onClick: () -> Unit,
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
fun SelfJournalStatsCard(
    modifier: Modifier = Modifier,
    icon: DrawableResource,
    title: String,
    color: Color,
    backgroundColor: Color,
    description: String,
) {
    Card(
        modifier = modifier,
        colors = ComponentColors.Card.MainCardColors(),
        shape = Dimens.Shape.Large
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().paddingAllSmall(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(
                modifier = Modifier.size(48.dp)
                    .background(color = backgroundColor, shape = Dimens.Shape.Small),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(icon),
                    contentDescription = description,
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
                    text = description,
                    style = TextStyles.TextSmSemiBold(),
                    color = Colors.Brown100Alpha64
                )
            }
        }
    }
}

