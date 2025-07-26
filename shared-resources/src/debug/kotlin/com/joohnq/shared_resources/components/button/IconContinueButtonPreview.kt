package com.joohnq.shared_resources.components.button

import androidx.compose.material3.IconButtonColors
import androidx.compose.runtime.Composable
import com.joohnq.shared_resources.components.parameter.IconButtonColorsParameterProvider
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter

@Preview
@Composable
fun IconContinueButtonContinueButtonColorsPreview(
    @PreviewParameter(IconButtonColorsParameterProvider::class)
    colors: IconButtonColors
) {
    IconContinueButton(
        colors = colors
    )
}
