package com.joohnq.health_journal.ui.components

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.joohnq.core.ui.mapper.toCompleteDateString
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.mood.ui.components.MoodFace
import com.joohnq.mood.ui.mapper.toResource
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
fun HealthJournalCard(
    journal: HealthJournalRecord, onClick: () -> Unit,
) {
    val resource = journal.mood.toResource()
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
                        color = resource.palette.color,
                        shape = Dimens.Shape.Small
                    ),
                    contentAlignment = Alignment.Center
                ) {
                    MoodFace(
                        resource = resource,
                        modifier = Modifier.size(24.dp),
                        backgroundColor = Colors.White,
                        color = resource.palette.color
                    )
                }
                Text(
                    text = journal.createdAt.date.toCompleteDateString(),
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
                        stringResource(resource.text)
                    ).uppercase(),
                    backgroundColor = resource.palette.backgroundColor,
                    textColor = resource.palette.color
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

