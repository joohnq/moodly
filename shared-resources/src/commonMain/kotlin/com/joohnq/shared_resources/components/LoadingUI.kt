package com.joohnq.shared_resources.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.theme.Colors

@Composable
fun LoadingUI() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(
            color = Colors.Brown80,
            strokeWidth = 4.dp,
            modifier = Modifier.size(40.dp)
        )
    }
}