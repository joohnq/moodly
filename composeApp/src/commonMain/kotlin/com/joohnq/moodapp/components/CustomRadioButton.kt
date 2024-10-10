package com.joohnq.moodapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun OnboardingCustomRadioButton(
    modifier: Modifier = Modifier,
    text: String,
    selected: Boolean,
    selectedBackground: Color,
    selectedContent: Color,
    unSelectedBackground: Color,
    unSelectedContent: Color,
    onClick: (String) -> Unit
) {
    Button(
        modifier = modifier.background(
            color = if (selected) selectedBackground else unSelectedBackground,
            shape = CircleShape
        ).fillMaxWidth().height(56.dp),
        shape = CircleShape,
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