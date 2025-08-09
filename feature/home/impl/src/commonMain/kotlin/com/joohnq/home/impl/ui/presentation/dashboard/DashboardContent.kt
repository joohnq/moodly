package com.joohnq.home.impl.ui.presentation.dashboard

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FabPosition
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.joohnq.home.impl.ui.components.DashboardCentral
import com.joohnq.home.impl.ui.presentation.home.HomeScreen
import com.joohnq.home.impl.ui.toDashboardEvent
import com.joohnq.navigation.Destination
import com.joohnq.shared_resources.components.button.BottomNavigationButton
import com.joohnq.shared_resources.components.layout.AppScaffoldLayout
import com.joohnq.shared_resources.components.modifier.takeIf
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables

@Composable
fun DashboardContent(
    snackBarHostState: SnackbarHostState = rememberSnackBarState(),
    navigator: NavHostController = rememberNavController(),
    state: DashboardContract.State,
    onEvent: (DashboardContract.Event) -> Unit,
    onIntent: (DashboardContract.Intent) -> Unit,
) {
    AppScaffoldLayout(
        containerColor = Colors.Brown5,
        snackBarHostState = snackBarHostState,
        floatingActionButton = {
            BottomNavigationButton(
                modifier = Modifier.size(48.dp),
                onClick = {
                    onIntent(DashboardContract.Intent.ToggleCentralExpanded)
                },
                image = if (state.isCentralExpanded) Drawables.Icons.Outlined.Close else Drawables.Icons.Outlined.Logo
            )
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { _ ->
        val padding = PaddingValues(bottom = 108.dp)

        NavHost(
            modifier =
                Modifier
                    .takeIf(state.isCentralExpanded) {
                        Modifier.blur(
                            radius = 15.dp
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

        if (state.isCentralExpanded) {
            DashboardCentral(
                padding = padding,
                onEvent = onEvent
            )
        }
    }
}
