package com.joohnq.moodapp.view.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeTitle(text: String) {
    Text(
        text = text,
        style = TextStyles.HomeTitle(),
        modifier = Modifier.padding(horizontal = 20.dp)
    )
}