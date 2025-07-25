package com.joohnq.shared_resources.components.parameter

import androidx.compose.material3.ButtonColors
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.joohnq.shared_resources.theme.ComponentColors

class ButtonColorsParameterProvider :
    PreviewParameterProvider<ButtonColors> {
    override val values = sequenceOf(
        ComponentColors.Button.mainButtonColors(),
        ComponentColors.Button.deleteButtonColors(),
        ComponentColors.Button.mainButtonColorsInverted(),
        ComponentColors.Button.bottomNavigationActionButtonColors(),
    )
}