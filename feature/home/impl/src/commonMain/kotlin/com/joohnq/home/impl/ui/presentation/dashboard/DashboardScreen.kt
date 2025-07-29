package com.joohnq.home.impl.ui.presentation.dashboard

import androidx.compose.foundation.layout.size
import androidx.compose.material3.FabPosition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.joohnq.home.impl.ui.components.DashboardCentral
import com.joohnq.home.impl.ui.presentation.home.HomeScreen
import com.joohnq.home.impl.ui.presentation.viewmodel.DashboardContract
import com.joohnq.home.impl.ui.presentation.viewmodel.DashboardViewModel
import com.joohnq.home.impl.ui.toDashboardEvent
import com.joohnq.navigation.Destination
import com.joohnq.shared_resources.components.button.BottomNavigationButton
import com.joohnq.shared_resources.components.layout.AppScaffoldLayout
import com.joohnq.shared_resources.components.modifier.takeIf
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.ui.ObserverSideEffects
import com.joohnq.ui.mapper.PaddingValuesMapper.plus
import com.joohnq.ui.sharedViewModel
import kotlinx.coroutines.launch

@Composable
fun DashboardScreen(onEvent: (DashboardContract.Event) -> Unit) {
    val snackBarHostState = rememberSnackBarState()
    val scope = rememberCoroutineScope()
    val navigator = rememberNavController()

    var centralIsExpanded by remember { mutableStateOf(false) }
    val dashboardViewModel: DashboardViewModel = sharedViewModel()

    fun onError(error: String) {
        scope.launch {
            snackBarHostState.showSnackbar(error)
        }
    }

    LaunchedEffect(Unit) {
        dashboardViewModel.onIntent(DashboardContract.Intent.Get)
    }

    ObserverSideEffects(
        flow = dashboardViewModel.sideEffect,
        onEvent = { effect ->
            when (effect) {
                is DashboardContract.SideEffect.ShowError -> onError(effect.message)
            }
        }
    )

    AppScaffoldLayout(
        containerColor = Colors.Brown10,
        snackBarHostState = snackBarHostState,
        floatingActionButton = {
            BottomNavigationButton(
                modifier = Modifier.size(48.dp),
                onClick = {
                    centralIsExpanded = !centralIsExpanded
                },
                image = if (centralIsExpanded) Drawables.Icons.Outlined.Close else Drawables.Icons.Outlined.Logo
            )
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { padding ->
        NavHost(
            modifier =
                Modifier
                    .takeIf(centralIsExpanded) {
                        Modifier.blur(
                            radius = 15.dp
                        )
                    },
            navController = navigator,
            startDestination = Destination.App.DashBoard.Home
        ) {
            composable<Destination.App.DashBoard.Home> {
                HomeScreen(
                    padding = padding.plus(bottom = 60.dp),
                    onEvent = { event ->
                        onEvent(event.toDashboardEvent())
                    }
                )
            }
        }

        if (centralIsExpanded) {
            DashboardCentral(
                padding = padding.plus(bottom = 50.dp),
                onEvent = onEvent
            )
        }
    }
}
