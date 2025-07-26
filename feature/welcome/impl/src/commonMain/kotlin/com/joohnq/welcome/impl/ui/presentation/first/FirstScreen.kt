package com.joohnq.welcome.impl.ui.presentation.first

import androidx.compose.runtime.Composable

@Composable
fun FirstScreen(onNext: () -> Unit) {
    FirstContent(
        onNext = onNext
    )
}