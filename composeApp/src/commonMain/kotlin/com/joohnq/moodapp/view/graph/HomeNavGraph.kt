package com.joohnq.moodapp.view.graph

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.joohnq.moodapp.view.AppBottomNavigation
import com.joohnq.moodapp.view.Screens
import com.joohnq.moodapp.view.addMood.AddMoodScreen
import com.joohnq.moodapp.view.constants.Colors
import com.joohnq.moodapp.view.home.HomeScreen
import com.joohnq.moodapp.view.journaling.JournalingScreen
import com.joohnq.moodapp.viewmodel.MoodsViewModel
import com.joohnq.moodapp.viewmodel.UserPreferenceViewModel
import com.joohnq.moodapp.viewmodel.UserViewModel

fun NavGraphBuilder.homeNavGraph(
    userPreferenceViewModel: UserPreferenceViewModel,
    userViewModel: UserViewModel,
    moodsViewModel: MoodsViewModel
) {
    composable<Screens.HomeGraph> {
        HomeNavGraph(
            moodsViewModel = moodsViewModel,
            userViewModel = userViewModel,
            userPreferenceViewModel = userPreferenceViewModel
        )
    }
}

@Composable
fun HomeNavGraph(
    userPreferenceViewModel: UserPreferenceViewModel,
    userViewModel: UserViewModel,
    moodsViewModel: MoodsViewModel
) {
    val navController = rememberNavController()

    Scaffold(
        containerColor = Colors.Brown10,
        bottomBar = {
            AppBottomNavigation(navController = navController)
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screens.HomeGraph.HomeScreen,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable<Screens.HomeGraph.HomeScreen> {
                HomeScreen(
                    navController = navController,
                    moodsViewModel = moodsViewModel,
                    userViewModel = userViewModel,
                )
            }
            composable<Screens.HomeGraph.AddMoodScreen> {
                AddMoodScreen()
            }
            composable<Screens.HomeGraph.JournalingScreen> {
                JournalingScreen()
            }
        }
    }
}