package com.joohnq.moodapp.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.joohnq.freud_score.ui.presentation.freud_score.FreudScoreScreen
import com.joohnq.home.ui.presentation.dashboard.DashboardScreen
import com.joohnq.home.ui.presentation.dashboard.event.DashboardEvent
import com.joohnq.mood.ui.presentation.add_stats.AddStatScreen
import com.joohnq.mood.ui.presentation.expression_analysis.ExpressionAnalysisScreen
import com.joohnq.mood.ui.presentation.mood.MoodScreen
import com.joohnq.navigation.Destination
import com.joohnq.navigation.NavigationGraph
import com.joohnq.self_journal.ui.presentation.add_self_journal.AddJournalingScreen
import com.joohnq.self_journal.ui.presentation.edit_self_journal.EditJournalingScreen
import com.joohnq.self_journal.ui.presentation.self_journal.SelfJournalScreen
import com.joohnq.self_journal.ui.presentation.self_journal_history.AllJournalScreen
import com.joohnq.sleep_quality.ui.presentation.add_sleep_quality.AddSleepQualityScreen
import com.joohnq.sleep_quality.ui.presentation.sleep_history.SleepHistoryScreen
import com.joohnq.sleep_quality.ui.presentation.sleep_quality.SleepQualityScreen
import com.joohnq.stress_level.ui.presentation.add_stress_level.AddStressLevelScreen
import com.joohnq.stress_level.ui.presentation.stress_history.StressHistoryScreen
import com.joohnq.stress_level.ui.presentation.stress_level.StressLevelScreen
import com.joohnq.stress_level.ui.presentation.stress_stressors.StressStressorsScreen
import kotlinx.datetime.LocalDate

fun NavGraphBuilder.appNavigation(
    onNavigate: (Destination) -> Unit,
    onNavigateBack: (Destination) -> Unit,
    onGoBack: () -> Unit,
) {
    navigation<NavigationGraph.App>(startDestination = Destination.App.DashBoard) {
        composable<Destination.App.DashBoard> {
            DashboardScreen(
                onEvent = { event ->
                    when (event) {
                        DashboardEvent.OnNavigateToAddJournaling -> onNavigate(Destination.App.AddSelfJournaling)
                        DashboardEvent.OnNavigateToAddSleep -> onNavigate(Destination.App.AddSleepQuality)
                        DashboardEvent.OnNavigateToAddStat -> onNavigate(Destination.App.AddStat)
                        DashboardEvent.OnNavigateToAddStress -> onNavigate(Destination.App.AddStressLevel)
                        DashboardEvent.OnNavigateToAllJournals -> onNavigate(Destination.App.AllJournals())
                        is DashboardEvent.OnNavigateToEditJournaling -> onNavigate(Destination.App.EditJournaling(event.id))
                        DashboardEvent.OnNavigateToFreudScore -> onNavigate(Destination.App.FreudScore)
                        DashboardEvent.OnNavigateToSelfJournal -> onNavigate(Destination.App.SelfJournal)
                        DashboardEvent.OnNavigateToMindfulJournal -> onNavigate(Destination.App.MindfulJournal)
                        DashboardEvent.OnNavigateToMood -> onNavigate(Destination.App.Mood)
                        DashboardEvent.OnNavigateToSelfJournalHistory -> onNavigate(Destination.App.AllJournals())
                        DashboardEvent.OnNavigateToSleepQuality -> onNavigate(Destination.App.SleepQuality)
                        DashboardEvent.OnNavigateToStressLevel -> onNavigate(Destination.App.StressLevel)
                        is DashboardEvent.OnNavigateTo -> {
                            onNavigate(event.destination)
                        }
                    }
                },
            )
        }
        composable<Destination.App.FreudScore> {
            FreudScoreScreen(
                onGoBack = onGoBack,
                onNavigateMood = { id ->
                    onNavigate(Destination.App.Mood)
                },
                onNavigateAddStat = {
                    onNavigate(Destination.App.AddStat)
                }
            )
        }
        composable<Destination.App.SelfJournal> {
            SelfJournalScreen(
                onNavigateAddSelfJournal = {
                    onNavigate(Destination.App.AddSelfJournaling)
                },
                onNavigateAllJournals = {
                    onNavigate(Destination.App.AllJournals(it.toString()))
                },
                onGoBack = onGoBack
            )
        }
        composable<Destination.App.Mood> {
            MoodScreen(
                onNavigateBackToHome = {
                    onNavigateBack(Destination.App.DashBoard)
                },
                onNavigateAddStat = {
                    onNavigate(Destination.App.AddStat)
                }
            )
        }
        composable<Destination.App.AddStat> {
            AddStatScreen(
                onNavigateToExpressionAnalysis = {
                    onNavigate(Destination.App.ExpressionAnalysis)
                },
                onGoBack = onGoBack
            )
        }
        composable<Destination.App.ExpressionAnalysis> {
            ExpressionAnalysisScreen(
                onNavigateToMood = {
                    onNavigate(Destination.App.Mood)
                },
                onGoBack = onGoBack
            )
        }
        composable<Destination.App.StressLevel> {
            StressLevelScreen(
                onNavigateAddStressLevel = {
                    onNavigate(Destination.App.AddStressLevel)
                },
                onGoBack = onGoBack
            )
        }
        composable<Destination.App.StressHistory> {
            StressHistoryScreen(
                onNavigateStressLevel = {
                    onNavigate(Destination.App.StressLevel)
                },
                onAddStressLevel = {
                    onNavigate(Destination.App.AddStressLevel)
                },
                onGoBack = onGoBack
            )
        }
        composable<Destination.App.AddStressLevel> {
            AddStressLevelScreen(
                onNavigateToStressStressors = {
                    onNavigate(Destination.App.StressStressors)
                },
                onGoBack = onGoBack
            )
        }
        composable<Destination.App.StressStressors> {
            StressStressorsScreen(
                onGoBack = onGoBack,
                onNavigateBackToStressLevel = { onNavigateBack(Destination.App.StressHistory) }
            )
        }
        composable<Destination.App.SleepQuality> {
            SleepQualityScreen(
                onNavigateAddSleepQuality = { onNavigate(Destination.App.AddSleepQuality) },
                onGoBack = onGoBack
            )
        }
        composable<Destination.App.SleepHistory> {
            SleepHistoryScreen(
                onNavigateToSleepQuality = {
                    onNavigate(Destination.App.SleepQuality)
                },
                onGoBack = onGoBack,
                onNavigateToAddSleepQuality = {
                    onNavigate(Destination.App.AddSleepQuality)
                },
            )
        }
        composable<Destination.App.AddSleepQuality> {
            AddSleepQualityScreen(
                onGoBack = onGoBack
            )
        }
        composable<Destination.App.AddSelfJournaling> {
            AddJournalingScreen(
                onGoBack = onGoBack
            )
        }
        composable<Destination.App.EditJournaling> { backStackEntry ->
            val editJournaling =
                backStackEntry.toRoute<Destination.App.EditJournaling>()
            EditJournalingScreen(
                id = editJournaling.id,
                onGoBack = onGoBack
            )
        }
        composable<Destination.App.AllJournals> { backStackEntry ->
            val allJournals =
                backStackEntry.toRoute<Destination.App.AllJournals>()

            val date = allJournals.localDate?.let { LocalDate.parse(it) }

            AllJournalScreen(
                localDate = date,
                onGoBack = onGoBack,
                onNavigateEditJournaling = { id ->
                    onNavigate(Destination.App.EditJournaling(id))
                }
            )
        }
    }
}

