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
import com.joohnq.moodapp.view.components.AppBottomNavigation
import com.joohnq.moodapp.view.constants.Colors
import com.joohnq.moodapp.view.screens.Screens
import com.joohnq.moodapp.view.screens.add.AddMoodScreen
import com.joohnq.moodapp.view.screens.freudscore.FreudScoreScreen
import com.joohnq.moodapp.view.screens.healthjournal.HealthJournalScreen
import com.joohnq.moodapp.view.screens.home.HomeScreen
import com.joohnq.moodapp.view.screens.mood.MoodScreen
import com.joohnq.moodapp.view.screens.journaling.JournalingScreen

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
    ) {
        val padding = PaddingValues(
            top = it.calculateTopPadding(),
            bottom = it.calculateBottomPadding() + 100.dp,
            start = it.calculateStartPadding(LayoutDirection.Ltr),
            end = it.calculateEndPadding(LayoutDirection.Rtl)
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
                FreudScoreScreen(padding = padding)
            }
            composable<Screens.HomeGraph.MoodScreen> {
                MoodScreen(padding = padding)
            }
            composable<Screens.HomeGraph.HealthJournalScreen> {
                HealthJournalScreen(padding = padding)
            }
        }
        AppBottomNavigation(navController = navController)
    }
}