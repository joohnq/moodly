package com.joohnq.shared_resources.components.button

import androidx.compose.foundation.layout.size
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.IconResource
import com.joohnq.shared_resources.go_back
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.ui.entity.IconResource

@Composable
fun AppDarkBackButton(onClick: () -> Unit = {}) {
    FilledIconButton(
        modifier = Modifier.size(48.dp),
        shape = Dimens.Shape.Circle,
        colors =
            IconButtonColors(
                containerColor = Colors.Brown20,
                contentColor = Colors.Brown80,
                disabledContainerColor = Colors.Brown20,
                disabledContentColor = Colors.Brown80
            ),
        onClick = onClick
    ) {
        IconResource(
            IconResource(
                icon = Drawables.Icons.Outlined.ArrowOpen,
                tint = Colors.Brown80,
                modifier = Modifier.size(Dimens.Icon),
                contentDescription = Res.string.go_back
            )
        )
    }
}
