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
                    moodsViewModel = moodsViewModel,
                    userViewModel = userViewModel,
                )
            }
            composable<Screens.HomeGraph.AddMoodScreen> {
                AddMoodScreen( padding = padding,)
            }
            composable<Screens.HomeGraph.JournalingScreen> {
                JournalingScreen( padding = padding,)
            }
        }
        AppBottomNavigation(navController = navController)
    }
}