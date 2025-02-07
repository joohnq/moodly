package com.joohnq.mood.ui.components

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
import com.joohnq.core.ui.mapper.toMonthAbbreviatedDayAndHourFormatted
import com.joohnq.mood.ui.resource.MoodRecordResource
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingAllSmall
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MoodHistoryCard(
    containerColor: Color,
    record: MoodRecordResource,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardColors(
            containerColor = containerColor,
            contentColor = Color.Unspecified,
            disabledContainerColor = containerColor,
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
                    style = TextStyles.TextMdBold(),
                    color = Colors.Gray80
                )
                Text(
                    text = record.description,
                    style = TextStyles.TextSmMedium(),
                    color = Colors.Gray60,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = record.createdAt.toMonthAbbreviatedDayAndHourFormatted(),
                    style = TextStyles.TextSmMedium(),
                    color = Colors.Gray60
                )
                Icon(
                    painter =
                        painterResource(Drawables.Icons.ArrowChevron),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp).rotate(180f),
                    tint = Colors.Gray60
                )
            }
        }
    }
}

@Preview
@Composable
fun MoodHistoryCardPreview() {
    MoodHistoryCard(
        containerColor = Colors.White,
        record = MoodRecordResource(),
    )
}