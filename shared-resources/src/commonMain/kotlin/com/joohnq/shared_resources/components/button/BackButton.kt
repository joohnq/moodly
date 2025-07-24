package com.joohnq.shared_resources.components.button

import androidx.compose.foundation.layout.size
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.IconResource
import com.joohnq.shared_resources.go_back
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.ui.entity.IconResource

@Composable
fun BackButton(
    color: Color,
    backgroundColor: Color = Colors.Transparent,
    onClick: () -> Unit,
) {
    FilledIconButton(
        modifier = Modifier.size(48.dp),
        shape = Dimens.Shape.Circle,
        colors = IconButtonColors(
            containerColor = backgroundColor,
            contentColor = color,
            disabledContainerColor = backgroundColor,
            disabledContentColor = color
        ),
        onClick = onClick,
    ) {
        IconResource(
            IconResource(
                icon = Drawables.Icons.Outlined.ArrowOpen,
                tint = color,
                modifier = Modifier.size(Dimens.Icon),
                contentDescription = Res.string.go_back
            )
        )
    }
}