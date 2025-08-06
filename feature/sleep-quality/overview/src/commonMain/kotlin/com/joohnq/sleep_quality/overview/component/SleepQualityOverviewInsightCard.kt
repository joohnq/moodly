package com.joohnq.sleep_quality.overview.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.joohnq.api.getNow
import com.joohnq.api.mapper.LocalDateTimeMapper.toMonthDays
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.be_sure_to_log_your_sleep_metrics_everyday_to_get_accurate_heath_result
import com.joohnq.shared_resources.logged_this_month
import com.joohnq.shared_resources.nights
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.paddingAllSmall
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.sleep_quality.impl.ui.mapper.SleepQualityResourceMapper.toMonthRecordsCount
import com.joohnq.sleep_quality.impl.ui.resource.SleepQualityRecordResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SleepQualityOverviewInsightCard(
    modifier: Modifier = Modifier,
    items: List<SleepQualityRecordResource>,
) {
    val now = remember { getNow() }
    val month = now.month
    val monthDays = now.toMonthDays()

    Card(
        modifier = modifier.fillMaxWidth(),
        colors =
            CardColors(
                containerColor = Colors.Gray5,
                contentColor = Color.Unspecified,
                disabledContainerColor = Colors.Gray5,
                disabledContentColor = Color.Unspecified
            ),
        shape = Dimens.Shape.Large
    ) {
        Column(
            modifier = Modifier.paddingAllSmall(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                maxItemsInEachRow = 13,
                horizontalArrangement = Arrangement.Start
            ) {
                for (i in 0..38) {
                    val resource =
                        items.find { it.createdAt.day == i && it.createdAt.month == month }
                    val backgroundColor =
                        when {
                            i + 1 > monthDays -> Colors.Gray5
                            resource == null -> Colors.Gray20
                            else -> resource.sleepQuality.palette.color
                        }

                    Box(
                        modifier =
                            Modifier
                                .weight(1f)
                                .aspectRatio(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier =
                                Modifier
                                    .size(15.dp)
                                    .background(color = backgroundColor, shape = Dimens.Shape.Circle)
                        )
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = stringResource(Res.string.nights, items.toMonthRecordsCount()),
                        style = TextStyles.headingXsBold(),
                        color = Colors.Gray80
                    )
                    Text(
                        text = stringResource(Res.string.logged_this_month),
                        style = TextStyles.textMdRegular(),
                        color = Colors.Gray60
                    )
                }
                Icon(
                    painter = painterResource(Drawables.Icons.Outlined.ArrowOpen),
                    tint = Colors.Gray60,
                    modifier = Modifier.size(24.dp).rotate(180f),
                    contentDescription = null
                )
            }
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                color = Colors.Gray20
            )
            Text(
                text =
                    stringResource(
                        Res.string.be_sure_to_log_your_sleep_metrics_everyday_to_get_accurate_heath_result
                    ),
                style = TextStyles.paragraphSm(),
                color = Colors.Gray60
            )
        }
    }
}