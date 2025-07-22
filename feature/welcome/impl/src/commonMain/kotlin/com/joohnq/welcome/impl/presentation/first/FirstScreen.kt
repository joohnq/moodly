package com.joohnq.welcome.impl.presentation.first

import androidx.compose.runtime.Composable

@Composable
fun FirstScreen(onNext: () -> Unit) {
    FirstContent(
        onNext = onNext
    )
}
