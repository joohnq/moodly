package com.joohnq.moodapp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.entities.IconProps
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
fun IconAndTextRadioButton(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    text: String,
    icon: IconProps,
    selected: Boolean,
    selectedBackground: Color,
    selectedContent: Color,
    unSelectedBackground: Color,
    unSelectedContent: Color,
    shape: Shape,
    onClick: (String) -> Unit
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        shape = shape,
        colors = ButtonColors(
            containerColor = if (selected) selectedBackground else unSelectedBackground,
            contentColor = if (selected) selectedContent else unSelectedContent,
            disabledContainerColor = if (selected) selectedBackground else unSelectedBackground,
            disabledContentColor = if (selected) selectedContent else unSelectedContent
        ),
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
                    tint = if (selected) selectedContent else unSelectedContent,
                    modifier = icon.modifier
                )
                Text(
                    text = text,
                    style = CustomTextStyle.TextStyleWelcomeScreenButton()
                )
            }
            RadioButton(
                selected = selected,
                onClick = { onClick(text) },
                colors = RadioButtonDefaults.colors(
                    selectedColor = selectedContent,
                    unselectedColor = unSelectedContent,
                )
            )
        }
    }
}