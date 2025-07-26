package com.joohnq.shared_resources.components.button

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.continue_word
import com.joohnq.shared_resources.theme.ComponentColors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.ui.entity.IconResource
import org.jetbrains.compose.resources.StringResource

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    text: StringResource,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
) {
    TextAndIconButton(
        modifier = modifier.height(56.dp),
        text = text,
        enabled = enabled,
        colors = ComponentColors.Button.mainButtonColors(),
        shape = Dimens.Shape.Circle,
        icon =
            IconResource(
                icon = Drawables.Icons.Outlined.Arrow,
                modifier = Modifier.size(Dimens.Icon),
                contentDescription = Res.string.continue_word
            ),
        onClick = onClick
    )
}
