package com.joohnq.mood.impl.ui.components

import androidx.compose.foundation.layout.*
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
import com.joohnq.api.mapper.toMonthAbbreviatedDayAndHourFormatted
import com.joohnq.mood.impl.ui.resource.MoodRecordResource
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingAllSmall
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun MoodHistoryCard(
    modifier: Modifier = Modifier,
    record: MoodRecordResource,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardColors(
            containerColor = Colors.Gray5,
            contentColor = Color.Unspecified,
            disabledContainerColor = Colors.Gray5,
            disabledContentColor = Color.Unspecified
        ),
        shape = Dimens.Shape.Large
    ) {
        Row(
            modifier = Modifier.paddingAllSmall(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            MoodFace(
                modifier = Modifier.size(24.dp),
                resource = record.mood,
            )
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = stringResource(record.mood.text),
                    style = TextStyles.textMdBold(),
                    color = Colors.Gray80
                )
                Text(
                    text = record.description,
                    style = TextStyles.textSmMedium(),
                    color = Colors.Gray60,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = record.createdAt.toMonthAbbreviatedDayAndHourFormatted(),
                    style = TextStyles.textSmMedium(),
                    color = Colors.Gray60
                )
                Icon(
                    painter =
                    painterResource(Drawables.Icons.Outlined.ArrowOpen),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp).rotate(180f),
                    tint = Colors.Gray60
                )
            }
        }
    }
}