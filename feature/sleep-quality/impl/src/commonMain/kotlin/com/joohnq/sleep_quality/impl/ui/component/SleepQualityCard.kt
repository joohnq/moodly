package com.joohnq.sleep_quality.impl.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.ComponentColors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingAllSmall
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.sleep_quality.impl.ui.entity.SleepStatsItem
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun SleepQualityCard(modifier: Modifier = Modifier, item: SleepStatsItem) {
    Card(
        shape = Dimens.Shape.Large,
        colors = ComponentColors.Card.MainCardColors(),
        modifier = modifier
    ) {
        Column(Modifier.paddingAllSmall()) {
            Box(
                modifier = Modifier.size(40.dp)
                    .background(
                        color = Colors.Brown10,
                        shape = Dimens.Shape.ExtraSmall
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(item.icon),
                    contentDescription = stringResource(item.title),
                    tint = Colors.Brown80,
                    modifier = Modifier.size(24.dp)
                )
            }
            VerticalSpacer(16.dp)
            Text(
                text = stringResource(item.title),
                style = TextStyles.TextMdBold(),
                color = Colors.Brown80
            )
            VerticalSpacer(5.dp)
            Box(modifier = Modifier.fillMaxWidth()) {
                item.content()
            }
        }
    }
}
