package com.joohnq.shared_resources.components.button

import androidx.compose.foundation.layout.size
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.add
import com.joohnq.shared_resources.components.modifier.dpOffset
import com.joohnq.shared_resources.theme.ComponentColors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun SmallAddButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    FilledIconButton(
        onClick = onClick,
        shape = Dimens.Shape.Circle,
        colors = ComponentColors.IconButton.mainButtonColors(),
        modifier = modifier.size(56.dp).dpOffset(y = 30.dp)
    ) {
        Icon(
            painter = painterResource(Drawables.Icons.Outlined.Add),
            contentDescription = stringResource(Res.string.add),
            modifier = Modifier.size(Dimens.Icon)
        )
    }
}