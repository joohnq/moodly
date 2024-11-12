package com.joohnq.moodapp.view.components

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
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.entities.Icon
import com.joohnq.moodapp.entities.TextRadioButtonColors
import com.joohnq.moodapp.view.ui.ComponentColors
import com.joohnq.moodapp.view.ui.TextStyles

@Composable
fun TextRadioButton(
    modifier: Modifier = Modifier,
    text: String,
    selected: Boolean,
    colors: TextRadioButtonColors,
    shape: Shape,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier.fillMaxWidth().height(56.dp),
        shape = shape,
        colors = ComponentColors.Button.TextRadioButtonColors(selected = selected, colors = colors),
        onClick = onClick,
        border = if (selected) BorderStroke(
            color = colors.selectedBorderColor,
            width = 4.dp
        ) else null,
    ) {
        Text(
            text = text,
            style = TextStyles.TextLgExtraBold(),
        )
    }
}

@Composable
fun IconAndTextRadioButtonHorizontal(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    text: String,
    icon: Icon,
    selected: Boolean,
    colors: TextRadioButtonColors,
    shape: Shape,
    textStyle: TextStyle,
    onClick: () -> Unit
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

@Composable
fun IconAndTextRadioButtonVertical(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    text: String,
    icon: Icon,
    selected: Boolean,
    colors: TextRadioButtonColors,
    shape: Shape,
    textStyle: TextStyle,
    onClick: () -> Unit
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
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            horizontalAlignment = Alignment.Start
        ) {
            Icon(
                icon.copy(tint = if (selected) colors.selectedContentColor else colors.unSelectedContentColor)
            )
            Text(
                text = text,
                style = textStyle.copy(color = if (selected) colors.selectedContentColor else colors.unSelectedContentColor)
            )
        }
    }
}