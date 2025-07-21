package com.joohnq.home.ui.presentation.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.joohnq.ui.sharedViewModel
import com.joohnq.home.ui.presentation.home.event.HomeEvent
import com.joohnq.home.ui.presentation.viewmodel.DashboardViewModel

@Composable
fun HomeScreen(
    padding: PaddingValues,
    onEvent: (HomeEvent) -> Unit
) {
    val dashboardViewModel: DashboardViewModel = sharedViewModel()
    val state by dashboardViewModel.state.collectAsStateWithLifecycle()

    HomeUI(
        padding = padding,
        state = state,
        onEvent = onEvent
    )
}
