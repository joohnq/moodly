package com.joohnq.stress_level.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.stress_level.ui.resource.StressorResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun StressTrigger(
    percentage: String,
    stressor: StressorResource,
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .background(color = stressor.color, shape = Dimens.Shape.Circle)
                    .clip(Dimens.Shape.Circle)
            )
            Text(
                text = stringResource(stressor.text),
                style = TextStyles.TextSmRegular(),
                color = Colors.Gray80
            )
        }
        Text(
            text = percentage,
            style = TextStyles.TextSmSemiBold(),
            color = Colors.Gray80
        )
    }
}