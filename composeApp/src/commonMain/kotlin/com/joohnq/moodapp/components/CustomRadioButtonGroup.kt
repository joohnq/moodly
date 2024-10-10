package com.joohnq.moodapp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.Colors

@Composable
fun CustomRadioButtonGroup(
    modifier: Modifier = Modifier,
    spaceBetween: Dp,
    options: List<String>,
    content: @Composable (String) -> Unit
) {

}

@Composable
fun YesOrNoRadioButtonGroup(selectedOption: Boolean?, onOptionSelected: (Boolean) -> Unit) {
    CustomRadioButtonGroup(
        modifier = Modifier.fillMaxWidth(),
        spaceBetween = 10.dp,
        options = listOf("Yes", "No")
    ) { option ->

    }
}
