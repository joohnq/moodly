package com.joohnq.shared_resources.components.button

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.ComponentColors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.ui.entity.IconResource
import org.jetbrains.compose.resources.StringResource

@Composable
fun TextAndCheckButton(
    modifier: Modifier = Modifier,
    text: StringResource,
    onClick: () -> Unit = {},
) {
    TextAndIconButton(
        modifier = modifier,
        text = text,
        colors = ComponentColors.Button.MainButtonColorsInverted(),
        shape = Dimens.Shape.Circle,
        icon = IconResource(
            icon = Drawables.Icons.Outlined.Check,
            tint = Colors.Brown80,
            modifier = Modifier.size(Dimens.Icon),
            contentDescription = text
        ),
        onClick = onClick
    )
}