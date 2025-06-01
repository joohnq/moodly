package com.joohnq.home.ui.presentation.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.joohnq.domain.mapper.getValue
import com.joohnq.home.ui.presentation.dashboard.viewmodel.DashboardContract
import com.joohnq.home.ui.presentation.dashboard.viewmodel.DashboardViewModel
import com.joohnq.ui.sharedViewModel
import com.joohnq.user.ui.viewmodel.UserViewModel

@Composable
fun HomeScreen(
    padding: PaddingValues,
    onEvent: (DashboardContract.Event) -> Unit
) {
    val dashboardViewModel: DashboardViewModel = sharedViewModel()
    val userViewModel: UserViewModel = sharedViewModel()
    val state by dashboardViewModel.state.collectAsState()
    val userState by userViewModel.state.collectAsState()

    LaunchedEffect(userState.user){

    }

    HomeUI(
        padding = padding,
        user = userState.user.getValue(),
        state = state,
        onEvent = onEvent
    )
}
