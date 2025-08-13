package com.joohnq.shared_resources.components.parameter

import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.joohnq.shared_resources.theme.Dimens

class ShapeParameterProvider : PreviewParameterProvider<Shape> {
    override val values =
        sequenceOf(
            Dimens.Shape.Circle,
            Dimens.Shape.ExtraLarge,
            Dimens.Shape.Large,
            Dimens.Shape.Medium,
            Dimens.Shape.Small,
            Dimens.Shape.ExtraSmall
        )
}
