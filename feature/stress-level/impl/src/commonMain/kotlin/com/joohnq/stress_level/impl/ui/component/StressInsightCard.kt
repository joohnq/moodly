package com.joohnq.stress_level.impl.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.active_stressors
import com.joohnq.shared_resources.components.spacer.VerticalSpacer
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingAllSmall
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.shared_resources.your_logged_as_your_most_active_stressors_for_this_month
import com.joohnq.stress_level.impl.ui.resource.StressorResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun StressInsightCard(
    modifier: Modifier = Modifier,
    stressors: List<Pair<StressorResource, Int>>,
    mostActive: StressorResource,
) {
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
        Column(modifier = Modifier.paddingAllSmall(), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Column {
                Text(
                    text = stressors.size.toString(),
                    style = TextStyles.headingXsBold(),
                    color = Colors.Gray80
                )
                VerticalSpacer(5.dp)
                Text(
                    text = stringResource(Res.string.active_stressors),
                    style = TextStyles.textMdRegular(),
                    color = Colors.Gray60
                )
            }

            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                stressors.forEach { (stressor, _) ->
                    Row(
                        modifier =
                            Modifier
                                .border(width = 1.dp, color = Colors.Gray30, shape = Dimens.Shape.Circle)
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                                .clip(Dimens.Shape.Circle),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            painter = painterResource(stressor.icon),
                            contentDescription = stringResource(stressor.text),
                            modifier = Modifier.size(16.dp),
                            tint = Colors.Gray60
                        )
                        Text(
                            text = stringResource(stressor.text),
                            style = TextStyles.textSmMedium(),
                            color = Colors.Gray60
                        )
                    }
                }
            }

            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                color = Colors.Gray20
            )
            Text(
                text =
                    stringResource(
                        Res.string.your_logged_as_your_most_active_stressors_for_this_month,
                        stringResource(mostActive.text)
                    ),
                style = TextStyles.paragraphSm(),
                color = Colors.Gray60
            )
        }
    }
}
