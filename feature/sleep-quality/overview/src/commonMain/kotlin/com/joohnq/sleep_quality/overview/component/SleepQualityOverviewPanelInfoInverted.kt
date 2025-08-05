package com.joohnq.sleep_quality.overview.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun SleepQualityOverviewPanelInfoInverted(
    modifier: Modifier = Modifier,
    icon: DrawableResource,
    title: StringResource,
    value: String,
) {
    Row(
        modifier = modifier,
        horizontalArrangement =
            Arrangement.spacedBy(
                space = 8.dp,
                alignment = Alignment.End
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(horizontalAlignment = Alignment.End, verticalArrangement = Arrangement.spacedBy(3.dp)) {
            Text(
                text = stringResource(title),
                style = TextStyles.textSmMedium(),
                color = Colors.Gray60
            )
            Text(
                text = value,
                style = TextStyles.textLgSemiBold(),
                color = Colors.Gray80
            )
        }
        Box(
            modifier =
                Modifier
                    .size(48.dp)
                    .clip(Dimens.Shape.Circle)
                    .border(
                        width = 2.dp,
                        color = Colors.Brown80,
                        shape = Dimens.Shape.Circle
                    ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = stringResource(title),
                tint = Colors.Brown80,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
