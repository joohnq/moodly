package com.joohnq.moodapp.view.graph

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.joohnq.moodapp.view.components.BottomNavigation
import com.joohnq.moodapp.view.routes.onNavigateToAddJournalingScreen
import com.joohnq.moodapp.view.routes.onNavigateToAddMood
import com.joohnq.moodapp.view.routes.onNavigateToRoute
import com.joohnq.moodapp.view.screens.Screens
import com.joohnq.moodapp.view.screens.home.HomeScreen
import com.joohnq.moodapp.view.screens.journaling.JournalingScreen
import com.joohnq.moodapp.view.ui.Colors

@Composable
fun HomeNavGraph(navHostController: NavHostController) {
    val homeNavController = rememberNavController()
    val snackBarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) },
        containerColor = Colors.Brown10,
    ) { scaffoldPadding ->
        val padding = PaddingValues(
            top = scaffoldPadding.calculateTopPadding(),
            bottom = scaffoldPadding.calculateBottomPadding() + 100.dp,
            start = scaffoldPadding.calculateStartPadding(LayoutDirection.Ltr),
            end = scaffoldPadding.calculateEndPadding(LayoutDirection.Rtl)
        )
        NavHost(
            navController = homeNavController,
            startDestination = Screens.HomeGraph.HomeScreen,
            modifier = Modifier.fillMaxSize()
        ) {
            composable<Screens.HomeGraph.HomeScreen> {
                HomeScreen(
                    snackBarHostState = snackBarHostState,
                    padding = padding,
                    navigation = navHostController
                )
            }
            composable<Screens.HomeGraph.JournalingScreen> {
                JournalingScreen(padding = padding)
            }
        }

        homeNavController.BottomNavigation(
            onNavigateToRoute = homeNavController::onNavigateToRoute,
            onNavigateToAdd = { screen ->
                if (screen == Screens.HomeGraph.HomeScreen) {
                    navHostController.onNavigateToAddMood()
                } else if (screen == Screens.HomeGraph.JournalingScreen) {
                    navHostController.onNavigateToAddJournalingScreen()
                }
            }
        )
    }
}