package com.joohnq.mood.add.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.joohnq.api.mapper.LocalDateTimeMapper.toMonthAbbreviatedDayAndHourFormatted
import com.joohnq.mood.add.ui.resource.MoodRecordResource
import com.joohnq.shared_resources.components.layout.CardWithMoreMenuLayout
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.PaddingModifier.paddingAllSmall
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.stringResource

@Composable
fun MoodHistoryCard(
    modifier: Modifier = Modifier,
    item: MoodRecordResource,
    onDelete: (Int) -> Unit = {},
) {
    CardWithMoreMenuLayout(
        modifier = modifier.fillMaxWidth(),
        menuContainerColor = item.mood.palette.color,
        onDelete = { onDelete(item.id) }
    ) { modifier ->
        Row(
            modifier = modifier.paddingAllSmall(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            MoodFace(
                modifier = Modifier.size(24.dp),
                resource = item.mood
            )
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = stringResource(item.mood.text),
                    style = TextStyles.textMdBold(),
                    color = Colors.Gray80
                )
                Text(
                    text = item.description,
                    style = TextStyles.textSmMedium(),
                    color = Colors.Gray60,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Text(
                text = item.createdAt.toMonthAbbreviatedDayAndHourFormatted(),
                style = TextStyles.textSmMedium(),
                color = Colors.Gray60
            )
        }
    }
}
