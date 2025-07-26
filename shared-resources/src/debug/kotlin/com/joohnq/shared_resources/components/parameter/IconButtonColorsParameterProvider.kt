package com.joohnq.shared_resources.components.parameter

import androidx.compose.material3.IconButtonColors
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.ComponentColors

class IconButtonColorsParameterProvider : PreviewParameterProvider<IconButtonColors> {
    override val values =
        sequenceOf(
            ComponentColors.IconButton.mainButtonColors(),
            ComponentColors.IconButton.transparentButton(color = Colors.Brown80),
            ComponentColors.IconButton.previousNextButton(color = Colors.Brown80)
        )
}
