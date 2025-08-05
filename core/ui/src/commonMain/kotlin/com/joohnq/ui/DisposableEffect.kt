package com.joohnq.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect

@Composable
fun DisposableEffect(
    onDispose: () -> Unit,
) {
    DisposableEffect(Unit) {
        onDispose {
            onDispose()
        }
    }
}
