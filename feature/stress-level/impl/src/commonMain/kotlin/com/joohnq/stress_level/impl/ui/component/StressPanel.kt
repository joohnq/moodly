package com.joohnq.stress_level.impl.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.current_stress_level
import com.joohnq.shared_resources.not_available
import com.joohnq.shared_resources.stress_level
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.stress_level.impl.ui.resource.StressLevelRecordResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun StressPanel(
    record: StressLevelRecordResource?
) {
    val hasToday = record != null
    val iconTint = if (hasToday) Colors.White else Colors.Orange40
    val textColor = if (hasToday) Colors.White else Colors.Brown80

    Column(
        modifier = Modifier.fillMaxWidth().paddingHorizontalMedium(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(Drawables.Icons.Outlined.Warning),
            contentDescription = stringResource(Res.string.stress_level),
            modifier = Modifier.size(48.dp),
            tint = iconTint
        )
        if (!hasToday)
            VerticalSpacer(24.dp)
        Text(
            text = if (hasToday) record.stressLevel.level.toString() else stringResource(Res.string.not_available),
            style = if (hasToday) TextStyles.DisplaySmExtraBold() else TextStyles.Text2xlBold(),
            color = textColor
        )
        if (!hasToday)
            VerticalSpacer(10.dp)
        Text(
            text = stringResource(Res.string.current_stress_level),
            style = TextStyles.TextLgMedium(),
            color = textColor
        )
        if (hasToday)
            Text(
                text = stringResource(record.stressLevel.text),
                style = TextStyles.TextMdRegular(),
                color = record.stressLevel.palette.backgroundColor
            )
    }
}