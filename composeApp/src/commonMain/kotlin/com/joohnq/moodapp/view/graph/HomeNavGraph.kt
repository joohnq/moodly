package com.joohnq.moodapp.view.graph

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.joohnq.moodapp.entities.StatsRecord
import com.joohnq.moodapp.view.components.BottomNavigation
import com.joohnq.moodapp.view.constants.Colors
import com.joohnq.moodapp.view.routes.onNavigateToMood
import com.joohnq.moodapp.view.screens.Screens
import com.joohnq.moodapp.view.screens.add.AddMoodScreen
import com.joohnq.moodapp.view.screens.freudscore.FreudScoreScreen
import com.joohnq.moodapp.view.screens.healthjournal.HealthJournalScreen
import com.joohnq.moodapp.view.screens.home.HomeScreen
import com.joohnq.moodapp.view.screens.journaling.JournalingScreen
import com.joohnq.moodapp.view.screens.mood.MoodScreen
import kotlin.reflect.typeOf

fun NavGraphBuilder.homeNavGraph() {
    composable<Screens.HomeGraph> {
        HomeNavGraph()
    }
}

@Composable
fun HomeNavGraph() {
    val navController = rememberNavController()

    Scaffold(
        containerColor = Colors.Brown10,
    ) { scaffoldPadding ->
        val padding = PaddingValues(
            top = scaffoldPadding.calculateTopPadding(),
            bottom = scaffoldPadding.calculateBottomPadding() + 100.dp,
            start = scaffoldPadding.calculateStartPadding(LayoutDirection.Ltr),
            end = scaffoldPadding.calculateEndPadding(LayoutDirection.Rtl)
        )
        NavHost(
            navController = navController,
            startDestination = Screens.HomeGraph.HomeScreen,
            modifier = Modifier.fillMaxSize()
        ) {
            composable<Screens.HomeGraph.HomeScreen> {
                HomeScreen(
                    padding = padding,
                    navController = navController,
                )
            }
            composable<Screens.HomeGraph.AddMoodScreen> {
                AddMoodScreen(padding = padding)
            }
            composable<Screens.HomeGraph.JournalingScreen> {
                JournalingScreen(padding = padding)
            }
            composable<Screens.HomeGraph.FreudScoreScreen> {
                FreudScoreScreen(
                    padding = padding,
                    onGoBack = navController::popBackStack,
                    onClickOnItem = navController::onNavigateToMood
                )
            }
            composable<Screens.HomeGraph.MoodScreen>(
                typeMap = mapOf(typeOf<StatsRecord>() to StatsRecord.navType()),
            ) { navBackStackEntry ->
                val statsRecord =
                    navBackStackEntry.toRoute<Screens.HomeGraph.MoodScreen>().statsRecord
                MoodScreen(
                    padding = padding,
                    statsRecord = statsRecord,
                    onGoBack = navController::popBackStack
                )
            }
            composable<Screens.HomeGraph.HealthJournalScreen> {
                HealthJournalScreen(padding = padding)
            }
        }
        BottomNavigation(navController = navController)
    }
}