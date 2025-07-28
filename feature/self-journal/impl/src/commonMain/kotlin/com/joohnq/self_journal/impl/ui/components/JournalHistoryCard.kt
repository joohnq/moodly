package com.joohnq.self_journal.impl.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.joohnq.api.mapper.LocalDateTimeMapper.toMonthAbbreviatedAndDayString
import com.joohnq.api.mapper.StringMapper.toWordCount
import com.joohnq.self_journal.impl.ui.resource.SelfJournalRecordResource
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.spacer.HorizontalSpacer
import com.joohnq.shared_resources.components.spacer.VerticalSpacer
import com.joohnq.shared_resources.mood
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.paddingAllSmall
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.shared_resources.total_words
import com.joohnq.shared_resources.words
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun JournalHistoryCard(
    record: SelfJournalRecordResource,
    onClick: (Int) -> Unit = {},
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors =
            CardColors(
                containerColor = Colors.Gray5,
                contentColor = Color.Unspecified,
                disabledContainerColor = Colors.Gray5,
                disabledContentColor = Color.Unspecified
            ),
        onClick = {
            onClick(record.id)
        }
    ) {
        Row(
            modifier = Modifier.paddingAllSmall(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(Drawables.Icons.Outlined.BookOpen),
                contentDescription = null,
                tint = Colors.Brown60,
                modifier = Modifier.size(24.dp)
            )
            HorizontalSpacer(12.dp)
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = record.title,
                    style = TextStyles.textMdBold(),
                    color = Colors.Gray80,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = record.description,
                    style = TextStyles.textSmMedium(),
                    color = Colors.Gray60,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2
                )
                VerticalSpacer(5.dp)
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(record.mood.assets.secondaryIcon),
                            contentDescription = stringResource(Res.string.mood),
                            tint = Colors.Gray40,
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text = stringResource(record.mood.text),
                            style = TextStyles.textSmMedium(),
                            color = Colors.Gray60
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(Drawables.Icons.Outlined.Eye),
                            contentDescription = stringResource(Res.string.total_words),
                            tint = Colors.Gray40,
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text =
                                stringResource(
                                    Res.string.words,
                                    record.description.toWordCount()
                                ),
                            style = TextStyles.textSmMedium(),
                            color = Colors.Gray60
                        )
                    }
                }
            }
            HorizontalSpacer(12.dp)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = record.createdAt.toMonthAbbreviatedAndDayString(),
                    style = TextStyles.textSmRegular(),
                    color = Colors.Gray60
                )
                Icon(
                    painter = painterResource(Drawables.Icons.Outlined.ArrowOpen),
                    contentDescription = null,
                    tint = Colors.Gray60,
                    modifier = Modifier.size(24.dp).rotate(180f)
                )
            }
        }
    }
}
