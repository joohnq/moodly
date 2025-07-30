package com.joohnq.moodapp.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.joohnq.freud_score.impl.ui.presentation.freud_score.FreudScoreScreen
import com.joohnq.home.impl.ui.presentation.dashboard.DashboardScreen
import com.joohnq.home.impl.ui.presentation.viewmodel.DashboardContract
import com.joohnq.mood.impl.ui.presentation.add.AddMoodScreen
import com.joohnq.mood.impl.ui.presentation.expression_analysis.ExpressionAnalysisScreen
import com.joohnq.mood.impl.ui.presentation.history.MoodHistoryScreen
import com.joohnq.mood.impl.ui.presentation.overview.MoodOverviewScreen
import com.joohnq.navigation.Destination
import com.joohnq.navigation.NavigationGraph
import com.joohnq.self_journal.impl.ui.presentation.add_self_journal.AddSelfJournalScreen
import com.joohnq.self_journal.impl.ui.presentation.edit_self_journal.EditJournalingScreen
import com.joohnq.self_journal.impl.ui.presentation.self_journal.SelfJournalScreen
import com.joohnq.self_journal.impl.ui.presentation.self_journal_history.SelfJournalHistoryScreen
import com.joohnq.sleep_quality.impl.ui.presentation.add_sleep_quality.AddSleepQualityScreen
import com.joohnq.sleep_quality.impl.ui.presentation.sleep_quality.SleepQualityScreen
import com.joohnq.sleep_quality.impl.ui.presentation.sleep_quality_history.SleepQualityHistoryScreen
import com.joohnq.stress_level.impl.ui.presentation.add_stress_level.AddStressLevelScreen
import com.joohnq.stress_level.impl.ui.presentation.stress_level.StressLevelScreen
import com.joohnq.stress_level.impl.ui.presentation.stress_level_history.StressLevelHistoryScreen
import com.joohnq.stress_level.impl.ui.presentation.stress_stressors.StressStressorsScreen

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
                        DashboardContract.Event.OnNavigateToAddJournaling -> {
                            onNavigate(Destination.App.AddSelfJournal)
                        }

                        DashboardContract.Event.OnNavigateToAddSleep -> {
                            onNavigate(Destination.App.AddSleepQuality)
                        }

                        DashboardContract.Event.OnNavigateToAddMood -> {
                            onNavigate(Destination.App.AddMood)
                        }

                        DashboardContract.Event.OnNavigateToAddStress -> {
                            onNavigate(Destination.App.AddStressLevel)
                        }

                        DashboardContract.Event.OnNavigateToSelfJournalHistory -> {
                            onNavigate(Destination.App.SelfJournalHistory)
                        }

                        is DashboardContract.Event.OnNavigateToEditJournaling -> {
                            onNavigate(Destination.App.EditSelfJournal(event.id))
                        }

                        DashboardContract.Event.OnNavigateToFreudScore -> {
                            onNavigate(Destination.App.FreudScore)
                        }

                        DashboardContract.Event.OnNavigateToSelfJournal -> {
                            onNavigate(Destination.App.SelfJournal)
                        }

                        DashboardContract.Event.OnNavigateToMood -> {
                            onNavigate(Destination.App.Mood)
                        }

                        DashboardContract.Event.OnNavigateToSleepQuality -> {
                            onNavigate(Destination.App.SleepQuality)
                        }

                        DashboardContract.Event.OnNavigateToStressLevel -> {
                            onNavigate(Destination.App.StressLevel)
                        }

                        is DashboardContract.Event.OnNavigateTo -> {
                            onNavigate(event.destination)
                        }

                        DashboardContract.Event.OnNavigateToAddStressLevel -> {
                            onNavigate(Destination.App.AddStressLevel)
                        }
                    }
                }
            )
        }
        composable<Destination.App.FreudScore> {
            FreudScoreScreen(
                onGoBack = onGoBack
            )
        }
        composable<Destination.App.SelfJournal> {
            SelfJournalScreen(
                onNavigateAddSelfJournal = {
                    onNavigate(Destination.App.AddSelfJournal)
                },
                onNavigateToSelfJournalHistory = {
                    onNavigate(Destination.App.SelfJournalHistory)
                },
                onGoBack = {
                    onNavigateBack(Destination.App.DashBoard)
                },
                onEditSelfJournal = { id ->
                    onNavigate(Destination.App.EditSelfJournal(id))
                }
            )
        }
        composable<Destination.App.Mood> {
            MoodOverviewScreen(
                onGoBack = {
                    onNavigateBack(Destination.App.DashBoard)
                },
                onNavigateToAddMood = {
                    onNavigate(Destination.App.AddMood)
                },
                onNavigateToMoodHistory = {
                    onNavigate(Destination.App.MoodHistory)
                }
            )
        }
        composable<Destination.App.MoodHistory> {
            MoodHistoryScreen(
                onGoBack = onGoBack
            )
        }
        composable<Destination.App.AddMood> {
            AddMoodScreen(
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
                onGoBack = {
                    onNavigateBack(Destination.App.DashBoard)
                }
            )
        }
        composable<Destination.App.StressHistory> {
            StressLevelHistoryScreen(
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
                onNavigateToStressLevel = { onNavigate(Destination.App.StressLevel) }
            )
        }
        composable<Destination.App.SleepQuality> {
            SleepQualityScreen(
                onNavigateAddSleepQuality = { onNavigate(Destination.App.AddSleepQuality) },
                onGoBack = {
                    onNavigateBack(Destination.App.DashBoard)
                },
                onNavigateToSleepHistory = {
                    onNavigate(Destination.App.SleepQualityHistory)
                }
            )
        }
        composable<Destination.App.SleepQualityHistory> {
            SleepQualityHistoryScreen(
                onGoBack = onGoBack
            )
        }
        composable<Destination.App.AddSleepQuality> {
            AddSleepQualityScreen(
                onGoBack = onGoBack,
                onNavigateToSleepQuality = {
                    onNavigate(Destination.App.SleepQuality)
                }
            )
        }
        composable<Destination.App.AddSelfJournal> {
            AddSelfJournalScreen(
                onGoBack = onGoBack
            )
        }
        composable<Destination.App.EditSelfJournal> { backStackEntry ->
            val editSelfJournal =
                backStackEntry.toRoute<Destination.App.EditSelfJournal>()

            EditJournalingScreen(
                id = editSelfJournal.id,
                onGoBack = onGoBack
            )
        }
        composable<Destination.App.SelfJournalHistory> {
            SelfJournalHistoryScreen(
                onGoBack = onGoBack,
                onNavigateEditJournaling = { id ->
                    onNavigate(Destination.App.EditSelfJournal(id))
                }
            )
        }
    }
}
