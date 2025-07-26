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
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.ComponentColors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.ui.entity.IconResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun TextAndIconButtonPreview(
    @PreviewParameter(BooleanParameterProvider::class)
    boolean: Boolean,
) {
    TextAndIconButton(
        enabled = boolean,
        text = Res.string.app_name,
        icon =
            IconResource(
                icon = Drawables.Icons.Outlined.Logo,
                tint = Colors.Brown80,
                contentDescription = Res.string.app_name
            ),
        colors = ComponentColors.Button.mainButtonColors(),
        shape = Dimens.Shape.Circle
    )
}

@Preview
@Composable
fun TextAndIconButtonPreview(
    @PreviewParameter(ButtonColorsParameterProvider::class)
    colors: ButtonColors,
) {
    TextAndIconButton(
        enabled = true,
        text = Res.string.app_name,
        icon =
            IconResource(
                icon = Drawables.Icons.Outlined.Logo,
                tint = Colors.Brown80,
                contentDescription = Res.string.app_name
            ),
        colors = colors,
        shape = Dimens.Shape.Circle
    )
}

@Preview
@Composable
fun TextAndIconButtonPreview(
    @PreviewParameter(ShapeParameterProvider::class)
    shape: Shape,
) {
    TextAndIconButton(
        enabled = true,
        text = Res.string.app_name,
        icon =
            IconResource(
                icon = Drawables.Icons.Outlined.Logo,
                tint = Colors.Brown80,
                contentDescription = Res.string.app_name
            ),
        colors = ComponentColors.Button.mainButtonColors(),
        shape = shape
    )
}
