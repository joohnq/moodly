package com.joohnq.shared_resources.components.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.components.IconResource
import com.joohnq.shared_resources.components.spacer.HorizontalSpacer
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.ui.entity.IconResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun TextAndIconButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: StringResource,
    icon: IconResource,
    colors: ButtonColors,
    shape: Shape,
    onClick: () -> Unit = {}
) {
    Button(
        modifier = modifier.height(56.dp),
        colors = colors,
        enabled = enabled,
        shape = shape,
        onClick = onClick
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(text),
                style = TextStyles.textLgExtraBold()
            )
            HorizontalSpacer(12.dp)
            IconResource(icon.copy(tint = if (enabled) colors.contentColor else colors.disabledContentColor))
        }
    }
}
