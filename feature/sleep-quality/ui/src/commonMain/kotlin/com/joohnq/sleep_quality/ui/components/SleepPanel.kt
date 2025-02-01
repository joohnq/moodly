package com.joohnq.sleep_quality.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.*
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.sleep_quality.ui.resource.SleepQualityResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun SleepPanel(
    modifier: Modifier = Modifier,
    resource: SleepQualityResource?,
) {
    val hasToday = resource != null
    val iconTint = if (hasToday) Colors.White else Colors.Brown80
    val textColor = if (hasToday) Colors.White else Colors.Brown80

    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(Drawables.Icons.Sleep),
            contentDescription = stringResource(Res.string.sleep_quality),
            modifier = Modifier.size(48.dp),
            tint = iconTint
        )
        if (!hasToday)
            VerticalSpacer(24.dp)
        Text(
            text = if (hasToday) resource.level.toString() else stringResource(Res.string.not_available),
            style = if (hasToday) TextStyles.DisplaySmExtraBold() else TextStyles.Text2xlBold(),
            color = textColor
        )
        if (!hasToday)
            VerticalSpacer(10.dp)
        Text(
            text = stringResource(Res.string.current_sleep_quality),
            style = TextStyles.TextLgMedium(),
            color = textColor
        )
    }
}