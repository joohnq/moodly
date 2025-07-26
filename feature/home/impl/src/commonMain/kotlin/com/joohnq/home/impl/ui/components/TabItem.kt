package com.joohnq.home.impl.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.ui.entity.IconResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun TabItem(
    icon: IconResource,
    selected: Boolean,
    onNavigate: () -> Unit = {},
) {
    FilledIconButton(
        shape = Dimens.Shape.Circle,
        onClick = onNavigate,
        modifier = Modifier.size(48.dp),
        colors =
            IconButtonColors(
                containerColor = if (selected) Colors.Brown10 else Colors.White,
                contentColor = if (selected) Colors.Brown80 else Colors.Brown30,
                disabledContainerColor = Color.Unspecified,
                disabledContentColor = Color.Unspecified
            )
    ) {
        Icon(
            painter = painterResource(icon.icon),
            contentDescription = stringResource(icon.contentDescription),
            modifier = icon.modifier
        )
    }
}
