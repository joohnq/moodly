package com.joohnq.shared_resources.components.button

import androidx.compose.material3.ButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.app_name
import com.joohnq.shared_resources.components.parameter.BooleanParameterProvider
import com.joohnq.shared_resources.components.parameter.ButtonColorsParameterProvider
import com.joohnq.shared_resources.components.parameter.ShapeParameterProvider
import com.joohnq.shared_resources.theme.ComponentColors
import com.joohnq.shared_resources.theme.Dimens
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun TextOutlinedButtonPreview(
    @PreviewParameter(BooleanParameterProvider::class)
    boolean: Boolean
) {
    TextOutlinedButton(
        enabled = boolean,
        text = Res.string.app_name,
        colors = ComponentColors.Button.mainButtonColors(),
        shape = Dimens.Shape.Circle
    )
}

@Preview
@Composable
fun TextOutlinedButtonPreview(
    @PreviewParameter(ButtonColorsParameterProvider::class)
    colors: ButtonColors
) {
    TextOutlinedButton(
        text = Res.string.app_name,
        colors = colors,
        shape = Dimens.Shape.Circle
    )
}

@Preview
@Composable
fun TextOutlinedButtonPreview(
    @PreviewParameter(ShapeParameterProvider::class)
    shape: Shape
) {
    TextOutlinedButton(
        text = Res.string.app_name,
        colors = ComponentColors.Button.mainButtonColors(),
        shape = shape
    )
}
