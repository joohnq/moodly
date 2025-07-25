package com.joohnq.shared_resources.components.modifier

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Modifier.takeIf(condition: Boolean, thenModifier: Modifier.() -> Modifier): Modifier {
    val modifier = this
    return then(
        if (condition) thenModifier() else modifier
    )
}