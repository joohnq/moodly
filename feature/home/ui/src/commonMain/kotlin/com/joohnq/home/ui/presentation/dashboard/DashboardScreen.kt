package com.joohnq.home.ui.presentation.dashboard

import androidx.compose.foundation.layout.size
import androidx.compose.material3.FabPosition
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.joohnq.home.ui.components.DashboardCentral
import com.joohnq.home.ui.presentation.dashboard.event.DashboardEvent
import com.joohnq.home.ui.presentation.dashboard.event.toDashboardEvent
import com.joohnq.home.ui.presentation.home.HomeScreen
import com.joohnq.home.ui.presentation.viewmodel.DashboardIntent
import com.joohnq.home.ui.presentation.viewmodel.DashboardSideEffect
import com.joohnq.home.ui.presentation.viewmodel.DashboardViewModel
import com.joohnq.navigation.Destination
import com.joohnq.navigation.isCurrentRoute
import com.joohnq.shared_resources.components.BottomNavigationActionButton
import com.joohnq.shared_resources.components.ScaffoldSnackBar
import com.joohnq.shared_resources.components.takeIf
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.ui.ObserverSideEffects
import com.joohnq.ui.mapper.plus
import com.joohnq.ui.sharedViewModel
import kotlinx.coroutines.launch

@Composable
fun DashboardScreen(
    onEvent: (DashboardEvent) -> Unit
) {
    val snackBarHostState = rememberSnackBarState()
    val scope = rememberCoroutineScope()
    val navigator = rememberNavController()
    val navBackStackEntry by navigator.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val hierarchy = currentDestination?.hierarchy

    var centralIsExpanded by remember { mutableStateOf(false) }
    val dashboardViewModel: DashboardViewModel = sharedViewModel()

    fun onError(error: String) {
        scope.launch {
            snackBarHostState.showSnackbar(error)
        }
    }

    fun onNavigateBottomNavigate(destination: Destination) {
        if (!hierarchy.isCurrentRoute(destination))
            navigator.navigate(destination)

        centralIsExpanded = false
    }

    fun onSideEffect(effect: DashboardSideEffect) {
        when (effect) {
            is DashboardSideEffect.ShowError -> onError(effect.message)
        }
    }

    LaunchedEffect(Unit) {
        dashboardViewModel.onAction(DashboardIntent.Get)
    }

    ObserverSideEffects(
        flow = dashboardViewModel.sideEffect,
        onEvent = ::onSideEffect
    )

    ScaffoldSnackBar(
        containerColor = Colors.Brown10,
        snackBarHostState = snackBarHostState,
        floatingActionButton = {
            BottomNavigationActionButton(
                modifier = Modifier.size(48.dp),
                onClick = {
                    centralIsExpanded = !centralIsExpanded
                },
                image = if (centralIsExpanded) Drawables.Icons.Outlined.Close else Drawables.Icons.Outlined.Logo,
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
    ) { padding ->
        NavHost(
            modifier = Modifier
                .takeIf(centralIsExpanded) {
                    Modifier.blur(
                        radius = 15.dp,
                    )
                },
            navController = navigator,
            startDestination = Destination.App.DashBoard.Home
        ) {
            composable<Destination.App.DashBoard.Home> {
                HomeScreen(
                    padding = padding,
                    onEvent = { event ->
                        onEvent(event.toDashboardEvent())
                    }
                )
            }
        }

        if (centralIsExpanded)
            DashboardCentral(
                padding = padding.plus(bottom = 60.dp),
                onEvent = onEvent
            )
    }
}
