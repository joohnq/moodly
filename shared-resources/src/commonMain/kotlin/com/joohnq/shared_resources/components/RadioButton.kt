package com.joohnq.shared_resources.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.joohnq.domain.entity.DIcon
import com.joohnq.domain.entity.TextRadioButtonColors
import com.joohnq.shared_resources.theme.ComponentColors
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun TextRadioButton(
    modifier: Modifier = Modifier,
    text: StringResource,
    selected: Boolean,
    colors: TextRadioButtonColors,
    shape: Shape,
    contentPaddingValues: PaddingValues = ButtonDefaults.ContentPadding,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier.fillMaxWidth().height(56.dp),
        shape = shape,
        colors = ComponentColors.Button.TextRadioButtonColors(selected = selected, colors = colors),
        onClick = onClick,
        contentPadding = contentPaddingValues,
        border = if (selected) BorderStroke(
            color = colors.selectedBorderColor,
            width = 4.dp
        ) else null,
    ) {
        Text(
            text = stringResource(text),
            style = TextStyles.TextLgExtraBold(),
        )
    }
}

