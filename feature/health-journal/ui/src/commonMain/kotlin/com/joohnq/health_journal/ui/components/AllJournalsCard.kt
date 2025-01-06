package com.joohnq.health_journal.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.joohnq.core.ui.DatetimeProvider
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.health_journal.ui.presentation.all_journals.event.AllJournalEvent
import com.joohnq.mood.ui.components.MoodFace
import com.joohnq.mood.ui.mapper.toResource
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.CardWithSwipeTorReveal
import com.joohnq.shared_resources.components.TextEllipsis
import com.joohnq.shared_resources.components.TextWithBackground
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.hour
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.ComponentColors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun AllJournalsCard(
    i: Int,
    lastIndex: Int,
    healthJournal: HealthJournalRecord,
    onEvent: (AllJournalEvent) -> Unit,
    onDelete: () -> Unit,
) {
    val mood = healthJournal.mood
    val resource = mood.toResource()
    val palette = resource.palette

    CardWithSwipeTorReveal(
        content = { shape, modifier ->
            Card(
                shape = shape,
                modifier = modifier,
                colors = ComponentColors.Card.MainCardColors(),
                onClick = {
                    onEvent(
                        AllJournalEvent.OnSelectJournal(
                            healthJournal.id
                        )
                    )
                }
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box(
                            modifier = Modifier
                                .background(
                                    color = palette.color,
                                    shape = Dimens.Shape.Circle
                                )
                                .size(44.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            MoodFace(
                                modifier = Modifier.size(20.dp),
                                mood = resource,
                                backgroundColor = Colors.White,
                                color = palette.barFaceColor
                            )
                        }
                        TextWithBackground(
                            text = stringResource(resource.text),
                            backgroundColor = palette.backgroundColor,
                            textColor = palette.color
                        )
                    }
                    VerticalSpacer(10.dp)
                    TextEllipsis(
                        text = healthJournal.title,
                        style = TextStyles.TextMdExtraBold(),
                        color = Colors.Brown80,
                        maxLines = 1
                    )
                    VerticalSpacer(10.dp)
                    TextEllipsis(
                        text = healthJournal.description,
                        style = TextStyles.TextMdSemiBold(),
                        color = Colors.Brown100Alpha64,
                        maxLines = 2
                    )
                }
            }
        },
        secondaryContent = {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier.width(3.dp).weight(1f).fillMaxHeight()
                        .background(color = if (i != 0) Colors.Brown80 else Colors.Transparent)
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
                        text = DatetimeProvider.formatTime(healthJournal.date),
                        style = TextStyles.TextSmSemiBold(),
                        color = Colors.White
                    )
                }
                Box(
                    modifier = Modifier.width(3.dp).weight(1f).fillMaxHeight()
                        .background(color = if (i != lastIndex) Colors.Brown80 else Colors.Transparent)
                )
            }
        },
        cardPadding = PaddingValues(end = 10.dp, top = 5.dp, bottom = 5.dp),
        onDelete = onDelete
    )
}