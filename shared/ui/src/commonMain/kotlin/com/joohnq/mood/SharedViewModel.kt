package com.joohnq.mood

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable inline fun <reified T : ViewModel> sharedViewModel(): T {
    return koinViewModel<T>()
}
