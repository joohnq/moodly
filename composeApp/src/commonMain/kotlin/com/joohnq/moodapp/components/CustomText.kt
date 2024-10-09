package com.joohnq.moodapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TextWithBackground(text: String, borderColor: Color, backgroundColor: Color, textColor: Color) {
    Text(
        text,
        modifier = Modifier
            .border(2.dp, color = borderColor, shape = CircleShape)
            .background(color = backgroundColor, shape = CircleShape)
            .padding(vertical = 9.dp, horizontal = 16.dp),
        style = CustomTextStyle.TextStyleWelcomeScreenIndicatorIndex().copy(color = textColor)
    )
}