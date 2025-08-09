package com.joohnq.home.impl.ui.presentation.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.joohnq.home.impl.ui.presentation.dashboard.DashboardViewModel
import com.joohnq.home.impl.ui.presentation.home.event.HomeEvent
import com.joohnq.ui.sharedViewModel

@Composable
fun HomeScreen(
    padding: PaddingValues,
    onEvent: (HomeEvent) -> Unit,
    dashboardViewModel: DashboardViewModel = sharedViewModel(),
) {
    val state by dashboardViewModel.state.collectAsStateWithLifecycle()

    HomeContent(
        state = state,
        padding = padding,
        onEvent = onEvent
    )
}
