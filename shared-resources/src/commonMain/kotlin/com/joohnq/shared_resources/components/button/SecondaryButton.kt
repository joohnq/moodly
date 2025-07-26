package com.joohnq.shared_resources.components.button

import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.theme.ComponentColors
import com.joohnq.shared_resources.theme.Dimens
import org.jetbrains.compose.resources.StringResource

@Composable
fun SecondaryButton(
    modifier: Modifier = Modifier,
    text: StringResource,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    TextOutlinedButton(
        modifier = modifier.height(56.dp),
        text = text,
        enabled = enabled,
        colors = ComponentColors.Button.mainButtonColorsInverted(),
        shape = Dimens.Shape.Circle,
        onClick = onClick
    )
}
