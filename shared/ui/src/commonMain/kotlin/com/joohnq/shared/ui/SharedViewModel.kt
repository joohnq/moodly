package com.joohnq.shared.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import org.koin.compose.currentKoinScope

@Composable inline fun <reified T : ViewModel> sharedViewModel(): T {
    val scope = currentKoinScope()
    return viewModel {
        scope.get<T>()
    }
//    return koinInject()
}
