package com.joohnq.gratefulness.overview.presentation.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.joohnq.gratefulness.api.entity.GratefulnessInsight
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.gratefulness_insight_card_description
import com.joohnq.shared_resources.gratefulness_insight_card_title
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.paddingAllSmall
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun GratefulnessOverviewInsightBody(
    modifier: Modifier = Modifier,
    insight: GratefulnessInsight,
) {
    Card(
        colors =
            CardColors(
                containerColor = Colors.Gray5,
                contentColor = Colors.Gray60,
                disabledContainerColor = Colors.Gray5,
                disabledContentColor = Colors.Gray60
            ),
        shape = Dimens.Shape.ExtraLarge
    ) {
        Column(
            modifier = modifier.paddingAllSmall().fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter =
                        painterResource(
                            Drawables.Icons.Outlined.LightBulb
                        ),
                    tint = Colors.Gray40,
                    contentDescription = null,
                    modifier = Modifier.size(Dimens.Icon)
                )
                Text(
                    text = stringResource(Res.string.gratefulness_insight_card_title),
                    style = TextStyles.textMdBold(),
                    color = Colors.Gray80
                )
            }
            FlowRow(
                modifier =
                    Modifier
                        .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                insight.items.forEach { insight ->
                    Row(
                        modifier =
                            Modifier
                                .clip(Dimens.Shape.Circle)
                                .border(
                                    width = 1.dp,
                                    color = Colors.Gray30,
                                    shape = Dimens.Shape.Circle
                                ).padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Icon(
                            painter =
                                painterResource(
                                    Drawables.Icons.Outlined.Circle
                                ),
                            tint = Colors.Gray60,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = insight,
                            style = TextStyles.textSmMedium(),
                            color = Colors.Gray60
                        )
                    }
                }
            }
            insight.principal?.let {
                Text(
                    text =
                        stringResource(
                            Res.string.gratefulness_insight_card_description,
                            it,
                            insight.principalCount
                        ),
                    style = TextStyles.paragraphSm(),
                    color = Colors.Gray60
                )
            }
        }
    }
}
