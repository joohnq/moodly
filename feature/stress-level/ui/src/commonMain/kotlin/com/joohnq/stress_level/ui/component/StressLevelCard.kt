package com.joohnq.stress_level.ui.component

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
import com.joohnq.domain.mapper.toMonthAbbreviatedAndDayString
import com.joohnq.shared_resources.components.HorizontalSpacer
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingAllSmall
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.stress_level.ui.resource.StressLevelRecordResource
import com.joohnq.stress_level.ui.resource.StressorResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun StressLevelHistoryCard(
    modifier: Modifier = Modifier,
    containerColor: Color = Colors.White,
    record: StressLevelRecordResource,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardColors(
            containerColor = containerColor,
            contentColor = Color.Unspecified,
            disabledContainerColor = containerColor,
            disabledContentColor = Color.Unspecified
        ),
        shape = Dimens.Shape.Large,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().paddingAllSmall(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painter = painterResource(Drawables.Icons.Filled.Warning),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = Colors.Orange40
            )
            HorizontalSpacer(20.dp)
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(
                    8.dp,
                    alignment = Alignment.CenterVertically
                )
            ) {
                Text(
                    text = stringResource(record.stressLevel.subtitle),
                    style = TextStyles.TextMdBold(),
                    color = Colors.Brown80
                )
                Text(
                    text = if (record.stressors.isNotEmpty()) record.stressors.joinToString(", ") else stringResource(
                        StressorResource.InPeace.text
                    ),
                    style = TextStyles.TextSmMedium(),
                    color = Colors.Brown80,
                    overflow = TextOverflow.Ellipsis
                )
            }
            HorizontalSpacer(20.dp)
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = record.createdAt.toMonthAbbreviatedAndDayString(),
                    style = TextStyles.TextSmRegular(),
                    color = Colors.Gray60
                )
                Icon(
                    painter = painterResource(Drawables.Icons.Outlined.ArrowOpen),
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
fun StressLevelCardPreview() {
    StressLevelHistoryCard(
        containerColor = Colors.White,
        record = StressLevelRecordResource()
    )
}

@Preview
@Composable
fun StressLevelCardPreviewWithStressors() {
    StressLevelHistoryCard(
        containerColor = Colors.White,
        record = StressLevelRecordResource(
            stressors = listOf(
                StressorResource.Finances,
                StressorResource.Work
            )
        )
    )
}