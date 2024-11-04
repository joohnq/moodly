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
import com.joohnq.moodapp.view.screens.AddMoodScreen
import com.joohnq.moodapp.view.screens.FreudScoreScreen
import com.joohnq.moodapp.view.screens.HealthJournalScreen
import com.joohnq.moodapp.view.screens.HomeGraph
import com.joohnq.moodapp.view.screens.MoodScreen
import com.joohnq.moodapp.view.screens.add.AddMoodAction
import com.joohnq.moodapp.view.screens.add.AddMoodScreen
import com.joohnq.moodapp.view.screens.freudscore.FreudScoreAction
import com.joohnq.moodapp.view.screens.freudscore.FreudScoreScreen
import com.joohnq.moodapp.view.screens.healthjournal.HealthJournalScreen
import com.joohnq.moodapp.view.screens.home.HomeAction
import com.joohnq.moodapp.view.screens.home.HomeScreen
import com.joohnq.moodapp.view.screens.journaling.JournalingScreen
import com.joohnq.moodapp.view.screens.mood.MoodAction
import com.joohnq.moodapp.view.screens.mood.MoodScreen
import kotlin.reflect.typeOf

fun NavGraphBuilder.mainNavGraph(navHostController: NavHostController) {
    composable<HomeGraph> {
        HomeNavGraph(navHostController = navHostController)
    }
    composable<AddMoodScreen> {
        AddMoodScreen { action ->
            when (action) {
                is AddMoodAction.OnGoBack -> navHostController.popBackStack()
            }
        }
    }
    composable<FreudScoreScreen> {
        FreudScoreScreen { action ->
            when (action) {
                is FreudScoreAction.OnGoBack -> navHostController.popBackStack()
                is FreudScoreAction.OnNavigateToMood -> navHostController.onNavigateToMood(action.statsRecord)
            }
        }
    }
    composable<MoodScreen>(
        typeMap = mapOf(typeOf<StatsRecord>() to StatsRecord.navType()),
    ) { navBackStackEntry ->
        val statsRecord =
            navBackStackEntry.toRoute<MoodScreen>().statsRecord
        MoodScreen(statsRecord = statsRecord) { action ->
            when (action) {
                is MoodAction.OnGoBack -> navHostController.popBackStack()
            }
        }
    }
    composable<HealthJournalScreen> {
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
            startDestination = HomeGraph.HomeScreen,
            modifier = Modifier.fillMaxSize()
        ) {
            composable<HomeGraph.HomeScreen> {
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
            composable<HomeGraph.JournalingScreen> {
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