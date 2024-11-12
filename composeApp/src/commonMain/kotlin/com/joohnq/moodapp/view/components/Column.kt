package com.joohnq.moodapp.view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun DoubleText(firstText: StringResource, secondText: StringResource, color: Color) {
    Column {
        Text(
            stringResource(firstText),
            style = TextStyles.TextLgExtraBold()
                .copy(color = color)
        )
        Text(
            stringResource(secondText),
            style = TextStyles.LabelSm()
                .copy(color = color)
        )
    }
}