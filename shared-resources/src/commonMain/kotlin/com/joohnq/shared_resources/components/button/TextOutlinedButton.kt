package com.joohnq.shared_resources.components.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.theme.*
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun TextOutlinedButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: StringResource,
    colors: ButtonColors,
    shape: Shape,
    onClick: () -> Unit,
) {
    OutlinedButton(
        modifier = modifier.height(56.dp),
        colors = colors,
        enabled = enabled,
        shape = shape,
        onClick = onClick,
        border = BorderStroke(2.dp, colors.contentColor)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(text),
                style = TextStyles.TextLgExtraBold(),
            )
        }
    }
}