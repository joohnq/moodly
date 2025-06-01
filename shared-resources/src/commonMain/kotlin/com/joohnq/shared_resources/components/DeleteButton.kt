package com.joohnq.shared_resources.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.remove
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun DeleteButton(onClick: () -> Unit) {
    FilledIconButton(
        onClick = onClick,
        modifier = Modifier.size(40.dp),
        colors = IconButtonColors(
            containerColor = Colors.Pink40,
            contentColor = Colors.White,
            disabledContainerColor = Colors.Pink40,
            disabledContentColor = Colors.White
        )
    ) {
        Icon(
            painter = painterResource(Drawables.Icons.Filled.Trash),
            contentDescription = stringResource(Res.string.remove),
            tint = Colors.White,
            modifier = Modifier.size(28.dp)
        )
    }
}
