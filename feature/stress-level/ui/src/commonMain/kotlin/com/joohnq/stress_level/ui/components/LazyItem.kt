package com.joohnq.stress_level.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.joohnq.shared.ui.components.CalculateTextWidth
import com.joohnq.shared.ui.components.TextRadioButton
import com.joohnq.shared.domain.entity.TextRadioButtonColors
import com.joohnq.shared.ui.theme.Colors
import com.joohnq.shared.ui.theme.Dimens
import com.joohnq.stress_level.ui.StressorResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun StressStressorCircle(
    stressStressor: StressorResource,
    selected: Boolean,
    onClick: () -> Unit = {},
) {
    val fontSize = 18.sp
    val text = stringResource(stressStressor.text)
    val padding = 24.dp
    val size = CalculateTextWidth(text, fontSize)

    Box(modifier = Modifier.size(size + padding)) {
        TextRadioButton(
            onClick = onClick,
            modifier = Modifier
                .size(size + padding),
            colors = TextRadioButtonColors(
                selectedBackgroundColor = Colors.Green50,
                selectedContentColor = Colors.White,
                selectedBorderColor = Colors.Green50Alpha25,
                unSelectedBackgroundColor = Colors.Brown20,
                unSelectedContentColor = Colors.Brown40
            ),
            contentPaddingValues = PaddingValues(0.dp),
            text = text,
            selected = selected,
            shape = Dimens.Shape.Circle
        )
    }
}