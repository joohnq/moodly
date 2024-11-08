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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.joohnq.moodapp.entities.StatsRecord
import com.joohnq.moodapp.view.components.BottomNavigation
import com.joohnq.moodapp.view.components.BottomNavigationAction
import com.joohnq.moodapp.view.constants.Colors
import com.joohnq.moodapp.view.routes.onNavigateToAddMood
import com.joohnq.moodapp.view.routes.onNavigateToFreudScore
import com.joohnq.moodapp.view.routes.onNavigateToHealthJournal
import com.joohnq.moodapp.view.routes.onNavigateToMood
import com.joohnq.moodapp.view.routes.onNavigateToRoute
import com.joohnq.moodapp.view.screens.Screens
import com.joohnq.moodapp.view.screens.add.AddMoodScreen
import com.joohnq.moodapp.view.screens.freudscore.FreudScoreAction
import com.joohnq.moodapp.view.screens.freudscore.FreudScoreScreen
import com.joohnq.moodapp.view.screens.healthjournal.HealthJournalScreen
import com.joohnq.moodapp.view.screens.home.HomeAction
import com.joohnq.moodapp.view.screens.home.HomeScreen
import com.joohnq.moodapp.view.screens.journaling.JournalingScreen
import com.joohnq.moodapp.view.screens.mood.MoodAction
import com.joohnq.moodapp.view.screens.mood.MoodScreen
import com.joohnq.moodapp.view.screens.sleepquality.SleepQualityScreen
import com.joohnq.moodapp.view.screens.stresslevel.StressLevelScreen
import kotlin.reflect.typeOf

fun NavGraphBuilder.mainNavGraph(navHostController: NavHostController) {
    composable<Screens.HomeGraph> {
        HomeNavGraph(navHostController = navHostController)
    }
    composable<Screens.AddMoodScreen> {
        AddMoodScreen(navigation = navHostController)
    }
    composable<Screens.SleepQualityScreen> {
        SleepQualityScreen(navigation = navHostController)
    }
    composable<Screens.StressLevelScreen> {
        StressLevelScreen(navigation = navHostController)
    }
    composable<Screens.FreudScoreScreen> {
        FreudScoreScreen { action ->
            when (action) {
                is FreudScoreAction.OnGoBack -> navHostController.popBackStack()
                is FreudScoreAction.OnNavigateToMood -> navHostController.onNavigateToMood(action.statsRecord)
            }
        }
    }
    composable<Screens.MoodScreen>(
        typeMap = mapOf(typeOf<StatsRecord>() to StatsRecord.navType()),
    ) { navBackStackEntry ->
        val statsRecord =
            navBackStackEntry.toRoute<Screens.MoodScreen>().statsRecord
        MoodScreen(statsRecord = statsRecord) { action ->
            when (action) {
                is MoodAction.OnGoBack -> navHostController.popBackStack()
            }
        }
    }
    composable<Screens.HealthJournalScreen> {
        HealthJournalScreen()
    }
}

@Composable
fun HomeNavGraph(navHostController: NavHostController) {
    val homeNavController = rememberNavController()

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
            navController = homeNavController,
            startDestination = Screens.HomeGraph.HomeScreen,
            modifier = Modifier.fillMaxSize()
        ) {
            composable<Screens.HomeGraph.HomeScreen> {
                HomeScreen(
                    padding = padding,
                    onAction = { action ->
                        when (action) {
                            is HomeAction.OnNavigateToFreudScore ->
                                navHostController.onNavigateToFreudScore()

                            is HomeAction.OnNavigateToMood ->
                                navHostController.onNavigateToMood(action.statsRecord)

                            is HomeAction.OnNavigateToHealthJournal ->
                                homeNavController.onNavigateToHealthJournal()
                        }
                    }
                )
            }
            composable<Screens.HomeGraph.JournalingScreen> {
                JournalingScreen(padding = padding)
            }
        }

        homeNavController.BottomNavigation { action ->
            when (action) {
                is BottomNavigationAction.OnNavigateToRoute ->
                    homeNavController.onNavigateToRoute(action.route)

                is BottomNavigationAction.OnNavigateToAddMood ->
                    navHostController.onNavigateToAddMood()
            }
        }
    }
}