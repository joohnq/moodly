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
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.view.entities.IconProps
import org.jetbrains.compose.resources.painterResource

@Composable
fun TextRadioButton(
    modifier: Modifier = Modifier,
    text: String,
    selected: Boolean,
    selectedBackground: Color,
    selectedContent: Color,
    unSelectedBackground: Color,
    unSelectedContent: Color,
    shape: Shape,
    onClick: (String) -> Unit
) {
    Button(
        modifier = modifier.fillMaxWidth().height(56.dp),
        shape = shape,
        colors = ButtonColors(
            containerColor = if (selected) selectedBackground else unSelectedBackground,
            contentColor = if (selected) selectedContent else unSelectedContent,
            disabledContainerColor = if (selected) selectedBackground else unSelectedBackground,
            disabledContentColor = if (selected) selectedContent else unSelectedContent
        ),
        onClick = { onClick(text) }
    ) {
        Text(
            text = text,
            style = CustomTextStyle.TextStyleWelcomeScreenButton()
        )
    }
}

@Composable
fun IconAndTextRadioButtonHorizontal(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    text: String,
    icon: IconProps,
    selected: Boolean,
    iconAndTextRadioButtonColors: IconAndTextRadioButtonColors,
    shape: Shape,
    textStyle: TextStyle,
    onClick: (String) -> Unit
) {
    Button(
        modifier = modifier,
        shape = shape,
        colors = ButtonColors(
            containerColor = if (selected) iconAndTextRadioButtonColors.selectedBackgroundColor else iconAndTextRadioButtonColors.unSelectedBackgroundColor,
            contentColor = if (selected) iconAndTextRadioButtonColors.selectedContentColor else iconAndTextRadioButtonColors.unSelectedContentColor,
            disabledContainerColor = if (selected) iconAndTextRadioButtonColors.selectedBackgroundColor else iconAndTextRadioButtonColors.unSelectedBackgroundColor,
            disabledContentColor = if (selected) iconAndTextRadioButtonColors.selectedContentColor else iconAndTextRadioButtonColors.unSelectedContentColor
        ),
        border = if (selected) BorderStroke(
            color = iconAndTextRadioButtonColors.selectedBorderColor,
            width = 4.dp
        ) else null,
        onClick = { onClick(text) }
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
                    painter = painterResource(icon.icon),
                    contentDescription = icon.contentDescription,
                    tint = if (selected) iconAndTextRadioButtonColors.selectedContentColor else iconAndTextRadioButtonColors.unSelectedContentColor,
                    modifier = icon.modifier
                )
                Text(
                    text = text,
                    style = textStyle
                )
            }
            RadioButton(
                selected = selected,
                onClick = { onClick(text) },
                colors = RadioButtonDefaults.colors(
                    selectedColor = iconAndTextRadioButtonColors.selectedContentColor,
                    unselectedColor = iconAndTextRadioButtonColors.unSelectedContentColor,
                )
            )
        }
    }
}

data class IconAndTextRadioButtonColors(
    val selectedBackgroundColor: Color,
    val selectedContentColor: Color,
    val selectedBorderColor: Color,
    val unSelectedBackgroundColor: Color,
    val unSelectedContentColor: Color,
)

@Composable
fun IconAndTextRadioButtonVertical(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    text: String,
    icon: IconProps,
    selected: Boolean,
    iconAndTextRadioButtonColors: IconAndTextRadioButtonColors,
    shape: Shape,
    textStyle: TextStyle,
    onClick: (String) -> Unit
) {
    Button(
        modifier = modifier,
        shape = shape,
        colors = ButtonColors(
            containerColor = if (selected) iconAndTextRadioButtonColors.selectedBackgroundColor else iconAndTextRadioButtonColors.unSelectedBackgroundColor,
            contentColor = if (selected) iconAndTextRadioButtonColors.selectedContentColor else iconAndTextRadioButtonColors.unSelectedContentColor,
            disabledContainerColor = if (selected) iconAndTextRadioButtonColors.selectedBackgroundColor else iconAndTextRadioButtonColors.unSelectedBackgroundColor,
            disabledContentColor = if (selected) iconAndTextRadioButtonColors.selectedContentColor else iconAndTextRadioButtonColors.unSelectedContentColor
        ),
        border = if (selected) BorderStroke(
            color = iconAndTextRadioButtonColors.selectedBorderColor,
            width = 4.dp
        ) else null,
        contentPadding = PaddingValues(0.dp),
        onClick = { onClick(text) }
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            horizontalAlignment = Alignment.Start
        ) {
            Icon(
                painter = painterResource(icon.icon),
                contentDescription = icon.contentDescription,
                tint = if (selected) iconAndTextRadioButtonColors.selectedContentColor else iconAndTextRadioButtonColors.unSelectedContentColor,
                modifier = icon.modifier
            )
            Text(
                text = text,
                style = textStyle.copy(color = if (selected) iconAndTextRadioButtonColors.selectedContentColor else iconAndTextRadioButtonColors.unSelectedContentColor)
            )
        }
    }
}