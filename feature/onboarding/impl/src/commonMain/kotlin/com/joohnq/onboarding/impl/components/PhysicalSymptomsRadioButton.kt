package com.joohnq.onboarding.impl.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.joohnq.ui.entity.DIcon
import com.joohnq.ui.entity.TextRadioButtonColors
import com.joohnq.shared_resources.components.Icon
import com.joohnq.shared_resources.theme.ComponentColors

@Composable
fun PhysicalSymptomsRadioButton(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    text: String,
    icon: DIcon,
    selected: Boolean,
    colors: TextRadioButtonColors,
    shape: Shape,
    textStyle: TextStyle,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier,
        shape = shape,
        colors = ComponentColors.Button.TextRadioButtonColors(selected = selected, colors = colors),
        border = if (selected) BorderStroke(
            color = colors.selectedBorderColor,
            width = 4.dp
        ) else null,
        contentPadding = PaddingValues(0.dp),
        onClick = onClick
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth().padding(paddingValues),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    icon.copy(tint = if (selected) colors.selectedContentColor else colors.unSelectedContentColor)
                )
                Text(
                    text = text,
                    style = textStyle
                )
            }
            RadioButton(
                selected = selected,
                onClick = onClick,
                colors = ComponentColors.RadioButton.IconAndTextRadioButtonHorizontalColors(colors)
            )
        }
    }
}