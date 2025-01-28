package com.joohnq.stress_level.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.FivePackIndicator
import com.joohnq.shared_resources.components.MetricCardSide
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.level
import com.joohnq.shared_resources.set_up_sleep
import com.joohnq.shared_resources.stress_level
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.shared_resources.you_havent_set_up_any_mental_sleep_yet
import com.joohnq.stress_level.ui.mapper.getBrushGradient
import com.joohnq.stress_level.ui.resource.StressLevelResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun StressLevelNotFound(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Card(
        colors = CardColors(
            containerColor = Colors.White,
            contentColor = Colors.Brown80,
            disabledContainerColor = Colors.White,
            disabledContentColor = Colors.Brown80
        ),
        shape = Dimens.Shape.Large,
        modifier = modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(Drawables.Images.SleepWomanIllustration),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )
            VerticalSpacer(16.dp)
            Text(
                text = stringResource(Res.string.you_havent_set_up_any_mental_sleep_yet),
                style = TextStyles.ParagraphSm(),
                color = Colors.Gray60,
                textAlign = TextAlign.Center
            )
            VerticalSpacer(16.dp)
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                color = Colors.Gray20
            )
            VerticalSpacer(10.dp)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    10.dp,
                    alignment = Alignment.CenterHorizontally
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(Res.string.set_up_sleep),
                    style = TextStyles.TextMdSemiBold(),
                    color = Colors.Brown60
                )
                Icon(
                    painter = painterResource(Drawables.Icons.Arrow),
                    contentDescription = null,
                    tint = Colors.Brown60,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

@Composable
fun StressLevelMetric(resource: StressLevelResource?, onCreate: () -> Unit, onClick: () -> Unit) {
    if (resource == null)
        StressLevelNotFound(modifier = Modifier.paddingHorizontalMedium(), onClick = onCreate)
    else
        MetricCardSide(
            modifier = Modifier.paddingHorizontalMedium(),
            icon = Drawables.Icons.Warning,
            title = Res.string.stress_level,
            text = resource.value,
            suffix = Res.string.level,
            description = resource.subtitle,
            content = {
                FivePackIndicator(resource.level, ::getBrushGradient)
            },
            color = Colors.Orange40,
            onClick = onClick
        )
}