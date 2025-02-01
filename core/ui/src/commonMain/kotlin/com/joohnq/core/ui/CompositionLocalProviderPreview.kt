package com.joohnq.core.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import org.koin.compose.LocalKoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module

@Composable
fun CompositionLocalProviderPreview(
    module: Module,
    content: @Composable () -> Unit
) {
    val koinApp = startKoin {
        modules(module)
    }.koin
    CompositionLocalProvider(
        LocalKoinApplication provides koinApp
    ) {
        Column {
            content()
        }
    }
}