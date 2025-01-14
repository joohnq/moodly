package com.joohnq.moodapp.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.joohnq.freud_score.ui.presentation.freud_score.FreudScoreScreen
import com.joohnq.health_journal.ui.presentation.add_journaling_screen.AddJournalingScreen
import com.joohnq.health_journal.ui.presentation.all_journals.AllJournalScreen
import com.joohnq.health_journal.ui.presentation.edit_journaling_screen.EditJournalingScreen
import com.joohnq.health_journal.ui.presentation.health_journal.HealthJournalScreen
import com.joohnq.home.ui.presentation.dashboard.DashboardScreen
import com.joohnq.mood.ui.presentation.add_stats.AddStatScreen
import com.joohnq.mood.ui.presentation.expression_analysis.ExpressionAnalysisScreen
import com.joohnq.mood.ui.presentation.mood.MoodScreen
import com.joohnq.navigation.Destination
import com.joohnq.navigation.NavigationGraph
import com.joohnq.sleep_quality.ui.presentation.add_sleep_quality.AddSleepQualityScreen
import com.joohnq.sleep_quality.ui.presentation.sleep_quality.SleepQualityScreen
import com.joohnq.stress_level.ui.presentation.add_stress_level.AddStressLevelScreen
import com.joohnq.stress_level.ui.presentation.stress_level.StressLevelScreen
import com.joohnq.stress_level.ui.presentation.stress_stressors.StressStressorsScreen

fun NavGraphBuilder.appNavigation(
    onNavigate: (Destination, Boolean) -> Unit,
    onNavigateBack: (Destination) -> Unit,
    onGoBack: () -> Unit,
) {
    navigation<NavigationGraph.App>(startDestination = Destination.App.DashBoard) {
        composable<Destination.App.DashBoard> {
            DashboardScreen(
                onNavigateAddJournaling = {
                    onNavigate(
                        Destination.App.AddJournaling,
                        false
                    )
                },
                onNavigateAddStatScreen = { onNavigate(Destination.App.AddStat, false) },
                onNavigateFreudScore = { onNavigate(Destination.App.FreudScore, false) },
                onNavigateToMood = { onNavigate(Destination.App.Mood(), false) },
                onNavigateToHealthJournal = {
                    onNavigate(
                        Destination.App.HealthJournal,
                        false
                    )
                },
                onNavigateToMindfulJournal = {
                    onNavigate(
                        Destination.App.MindfulJournal,
                        false
                    )
                },
                onNavigateToSleepQuality = {
                    onNavigate(
                        Destination.App.SleepQuality,
                        false
                    )
                },
                onNavigateToStressLevel = { onNavigate(Destination.App.StressLevel, false) },
                onNavigateToEditJournaling = { id ->
                    onNavigate(
                        Destination.App.EditJournaling(id),
                        false
                    )
                },
                onNavigateToAllJournals = { onNavigate(Destination.App.AllJournals, false) },
            ).Content()
        }
        composable<Destination.App.FreudScore> {
            FreudScoreScreen(
                onGoBack = onGoBack,
                onNavigateMood = { id ->
                    onNavigate(
                        Destination.App.Mood(id),
                        false
                    )
                },
                onNavigateAddStat = {
                    onNavigate(
                        Destination.App.AddStat,
                        false
                    )
                }
            )
        }
        composable<Destination.App.HealthJournal> {
            HealthJournalScreen(
                onNavigateAddJournaling = {
                    onNavigate(
                        Destination.App.AddJournaling,
                        false
                    )
                },
                onNavigateAllJournaling = {
                    onNavigate(
                        Destination.App.AllJournaling,
                        false
                    )
                },
                onGoBack = onGoBack
            ).Content()
        }
        composable<Destination.App.Mood> { backStackEntry ->
            val mood = backStackEntry.toRoute<Destination.App.Mood>()
            MoodScreen(
                id = mood.id,
                onNavigateBackToHome = {
                    onNavigateBack(Destination.App.DashBoard)
                },
                onNavigateAddStat = {
                    onNavigate(
                        Destination.App.AddStat,
                        false
                    )
                }
            ).Content()
        }
        composable<Destination.App.AddStat> {
            AddStatScreen(
                onNavigateToExpressionAnalysis = {
                    onNavigate(
                        Destination.App.ExpressionAnalysis,
                        false
                    )
                },
                onGoBack = onGoBack
            )
        }
        composable<Destination.App.ExpressionAnalysis> {
            ExpressionAnalysisScreen(
                onNavigateToMood = {
                    onNavigateBack(Destination.App.Mood())
                },
                onGoBack = onGoBack
            ).Content()
        }
        composable<Destination.App.StressLevel> {
            StressLevelScreen(
                onNavigateAddStressLevel = {
                    onNavigate(Destination.App.AddStressLevel, false)
                },
                onGoBack = onGoBack
            ).Content()
        }
        composable<Destination.App.AddStressLevel> {
            AddStressLevelScreen(
                onNavigateToStressStressors = {
                    onNavigate(Destination.App.StressStressors, false)
                },
                onGoBack = onGoBack
            ).Content()
        }
        composable<Destination.App.StressStressors> {
            StressStressorsScreen(
                onGoBack = onGoBack,
                onNavigateBackToStressLevel = { onNavigateBack(Destination.App.StressLevel) }
            ).Content()
        }
        composable<Destination.App.SleepQuality> {
            SleepQualityScreen(
                onNavigateAddSleepQuality = { onNavigate(Destination.App.AddSleepQuality, false) },
                onGoBack = onGoBack
            ).Content()
        }
        composable<Destination.App.AddSleepQuality> {
            AddSleepQualityScreen(
                onGoBack = onGoBack
            ).Content()
        }
        composable<Destination.App.AddJournaling> {
            AddJournalingScreen(
                onGoBack = onGoBack
            ).Content()
        }
        composable<Destination.App.EditJournaling> { backStackEntry ->
            val editJournalingScreen =
                backStackEntry.toRoute<Destination.App.EditJournaling>()
            EditJournalingScreen(
                id = editJournalingScreen.id,
                onGoBack = onGoBack
            ).Content()
        }
        composable<Destination.App.AllJournal> {
            AllJournalScreen(
                onGoBack = onGoBack,
                onNavigateEditJournaling = { id ->
                    onNavigate(
                        Destination.App.EditJournaling(id),
                        false
                    )
                }
            ).Content()
        }
    }
}

