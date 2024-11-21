package com.joohnq.moodapp.view.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.joohnq.moodapp.view.screens.Screens
import com.joohnq.moodapp.view.screens.addjournalingscreen.AddJournalingScreen
import com.joohnq.moodapp.view.screens.addsleepquality.AddSleepQualityScreen
import com.joohnq.moodapp.view.screens.addstats.AddMoodScreen
import com.joohnq.moodapp.view.screens.addstresslevel.AddStressLevelScreen
import com.joohnq.moodapp.view.screens.addstresslevel.StressStressorsScreen
import com.joohnq.moodapp.view.screens.editjournalingscreen.EditJournalingScreen
import com.joohnq.moodapp.view.screens.expressionanalysis.ExpressionAnalysisScreen
import com.joohnq.moodapp.view.screens.freudscore.FreudScoreScreen
import com.joohnq.moodapp.view.screens.healthjournal.HealthJournalScreen
import com.joohnq.moodapp.view.screens.mood.MoodScreen
import com.joohnq.moodapp.view.screens.sleepquality.SleepQualityScreen
import com.joohnq.moodapp.view.screens.stresslevel.StressLevelScreen

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
        FreudScoreScreen(navigation = navHostController)
    }
    composable<Screens.MoodScreen> { navBackStackEntry ->
        val id =
            navBackStackEntry.toRoute<Screens.MoodScreen>().id
        MoodScreen(id = id, navigation = navHostController)
    }
    composable<Screens.HealthJournalScreen> {
        HealthJournalScreen(navigation = navHostController)
    }
    composable<Screens.ExpressionAnalysisScreen> {
        ExpressionAnalysisScreen(navigation = navHostController)
    }
    composable<Screens.AddStressLevelScreen> {
        AddStressLevelScreen(navigation = navHostController)
    }
    composable<Screens.StressStressorsScreen> {
        StressStressorsScreen(navigation = navHostController)
    }
    composable<Screens.AddSleepQualityScreen> {
        AddSleepQualityScreen(navigation = navHostController)
    }
    composable<Screens.AddJournalingScreen> {
        AddJournalingScreen(navigation = navHostController)
    }
    composable<Screens.EditJournalingScreen> { navBackStackEntry ->
        val id =
            navBackStackEntry.toRoute<Screens.EditJournalingScreen>().id
        EditJournalingScreen(navigation = navHostController, id = id)
    }
}
