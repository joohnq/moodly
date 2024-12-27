package com.joohnq.mood

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import org.koin.compose.koinInject

@Composable inline fun <reified T : ViewModel> sharedViewModel(): T {
    return koinInject<T>()
}
