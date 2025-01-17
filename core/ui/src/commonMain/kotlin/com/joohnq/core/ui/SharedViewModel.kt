package com.joohnq.core.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import org.koin.compose.koinInject

@Composable inline fun <reified T : ViewModel> sharedViewModel(): T {
//    val scope = currentKoinScope()
//    return viewModel { scope.get<T>() }
    return koinInject<T>()
}
