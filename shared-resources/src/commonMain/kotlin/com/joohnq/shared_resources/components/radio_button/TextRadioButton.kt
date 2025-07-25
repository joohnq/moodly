package com.joohnq.shared_resources.components.radio_button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.theme.ComponentColors
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.ui.entity.TextRadioButtonColors
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun TextRadioButton(
    modifier: Modifier = Modifier,
    text: StringResource,
    selected: Boolean,
    colors: TextRadioButtonColors,
    shape: Shape,
    paddingValues: PaddingValues = ButtonDefaults.ContentPadding,
    onClick: () -> Unit = {},
) {
    Button(
        modifier = modifier.fillMaxWidth().height(56.dp),
        shape = shape,
        colors = ComponentColors.Button.textRadioButtonColors(selected = selected, colors = colors),
        onClick = onClick,
        contentPadding = paddingValues,
        border = if (selected) BorderStroke(
            color = colors.selectedBorderColor,
            width = 4.dp
        ) else null,
    ) {
        Text(
            text = stringResource(text),
            style = TextStyles.textLgExtraBold(),
        )
    }
}