package com.joohnq.gratefulness.impl.ui.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.joohnq.gratefulness.api.entity.Gratefulness
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.add_new_gratitute
import com.joohnq.shared_resources.components.layout.NotFoundVerticalLayout
import com.joohnq.shared_resources.components.spacer.VerticalSpacer
import com.joohnq.shared_resources.components.text.SectionHeader
import com.joohnq.shared_resources.gratefulness_metric_action
import com.joohnq.shared_resources.gratefulness_metric_description
import com.joohnq.shared_resources.gratefulness_overview_title
import com.joohnq.shared_resources.gratitude_and_affirmations
import com.joohnq.shared_resources.lets_set_up_daily_gratitude_and_affirmations
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.paddingAllSmall
import com.joohnq.shared_resources.theme.PaddingModifier.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun GratefulnessMetric(
    resource: Gratefulness?,
    onCreate: () -> Unit = {},
    onClick: () -> Unit = {},
) {
    SectionHeader(
        modifier = Modifier.paddingHorizontalMedium(),
        title = Res.string.gratitude_and_affirmations,
        onSeeMore = onClick
    )
    if (resource == null) {
        NotFoundVerticalLayout(
            modifier = Modifier.paddingHorizontalMedium(),
            containerColor = Colors.White,
            title = Res.string.lets_set_up_daily_gratitude_and_affirmations,
            actionText = Res.string.add_new_gratitute,
            image = Drawables.Images.GratefulnessGetStarted,
            onClick = onCreate
        )
    } else {
        GratefulnessMetricCard(
            modifier = Modifier.paddingHorizontalMedium(),
            containerColor = Colors.White,
            title = stringResource(Res.string.gratefulness_overview_title, resource.iAmGratefulFor),
            description = Res.string.gratefulness_metric_description,
            actionText = Res.string.gratefulness_metric_action,
            onClick = onClick
        )
    }
}

@Composable
fun GratefulnessMetricCard(
    modifier: Modifier = Modifier,
    title: String,
    containerColor: Color = Colors.Gray5,
    description: StringResource,
    actionText: StringResource,
    onClick: () -> Unit,
) {
    Card(
        colors =
            CardColors(
                containerColor = containerColor,
                contentColor = Colors.Brown80,
                disabledContainerColor = containerColor,
                disabledContentColor = Colors.Brown80
            ),
        shape = Dimens.Shape.Large,
        modifier = modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.paddingAllSmall(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.size(48.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(Drawables.Icons.Outlined.Sun),
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth(),
                    tint = Colors.Yellow50
                )
            }
            VerticalSpacer(24.dp)
            Text(
                text = title,
                style = TextStyles.headingXsRegular(),
                color = Colors.Gray60,
                textAlign = TextAlign.Center
            )
            VerticalSpacer(24.dp)
            Text(
                text = stringResource(description),
                style = TextStyles.textSmRegular(),
                color = Colors.Gray60,
                textAlign = TextAlign.Center
            )
            VerticalSpacer(16.dp)
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                color = Colors.Gray20
            )
            VerticalSpacer(16.dp)
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                horizontalArrangement =
                    Arrangement.spacedBy(
                        10.dp,
                        alignment = Alignment.CenterHorizontally
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(actionText),
                    style = TextStyles.textMdSemiBold(),
                    color = Colors.Brown60
                )
                Icon(
                    painter = painterResource(Drawables.Icons.Outlined.Arrow),
                    contentDescription = null,
                    tint = Colors.Brown60,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}
