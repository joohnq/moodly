package com.joohnq.moodapp.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.joohnq.add.presentation.AddMoodScreen
import com.joohnq.add.presentation.ExpressionAnalysisScreen
import com.joohnq.freud_score.impl.ui.presentation.freud_score.FreudScoreScreen
import com.joohnq.history.presentation.MoodHistoryScreen
import com.joohnq.home.impl.ui.presentation.dashboard.DashboardContract
import com.joohnq.home.impl.ui.presentation.dashboard.DashboardScreen
import com.joohnq.navigation.Destination
import com.joohnq.navigation.NavigationGraph
import com.joohnq.overview.presentation.MoodOverviewScreen
import com.joohnq.self_journal.presentation.AddSelfJournalScreen
import com.joohnq.self_journal.presentation.EditSelfJournalScreen
import com.joohnq.self_journal.presentation.SelfJournalHistoryScreen
import com.joohnq.self_journal.presentation.SelfJournalOverviewScreen
import com.joohnq.sleep_quality.add.presentation.AddSleepQualityScreen
import com.joohnq.sleep_quality.history.presentation.SleepQualityHistoryScreen
import com.joohnq.sleep_quality.overview.presentation.SleepQualityOverviewScreen
import com.joohnq.stress_level.add.presentation.AddStressLevelScreen
import com.joohnq.stress_level.add.presentation.StressStressorsScreen
import com.joohnq.stress_level.history.presentation.StressLevelHistoryScreen
import com.joohnq.stress_level.overview.presentation.StressLevelOverviewScreen

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
                        DashboardContract.Event.NavigateToAddSelfJournal -> {
                            onNavigate(Destination.App.AddSelfJournal)
                        }

                        DashboardContract.Event.NavigateToAddSleepQuality -> {
                            onNavigate(Destination.App.AddSleepQuality)
                        }

                        DashboardContract.Event.NavigateToAddMood -> {
                            onNavigate(Destination.App.AddMood)
                        }

                        DashboardContract.Event.NavigateToAddStressLevel -> {
                            onNavigate(Destination.App.AddStressLevel)
                        }

                        DashboardContract.Event.NavigateToSelfJournalHistory -> {
                            onNavigate(Destination.App.SelfJournalHistory)
                        }

                        is DashboardContract.Event.NavigateToEditSelfJournal -> {
                            onNavigate(Destination.App.EditSelfJournal(event.id))
                        }

                        DashboardContract.Event.NavigateToFreudScore -> {
                            onNavigate(Destination.App.FreudScore)
                        }

                        DashboardContract.Event.NavigateToSelfJournal -> {
                            onNavigate(Destination.App.SelfJournalOverview)
                        }

                        DashboardContract.Event.NavigateToMoodOverview -> {
                            onNavigate(Destination.App.MoodOverview)
                        }

                        DashboardContract.Event.NavigateToSleepQuality -> {
                            onNavigate(Destination.App.SleepQualityOverview)
                        }

                        DashboardContract.Event.NavigateToStressLevelOverview -> {
                            onNavigate(Destination.App.StressLevelOverview)
                        }

                        is DashboardContract.Event.NavigateTo -> {
                            onNavigate(event.destination)
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
        composable<Destination.App.SelfJournalOverview> {
            SelfJournalOverviewScreen(
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
        composable<Destination.App.MoodOverview> {
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
                    onNavigate(Destination.App.MoodOverview)
                },
                onGoBack = onGoBack
            )
        }
        composable<Destination.App.StressLevelOverview> {
            StressLevelOverviewScreen(
                onNavigateAddStressLevel = {
                    onNavigate(Destination.App.AddStressLevel)
                },
                onGoBack = {
                    onNavigateBack(Destination.App.DashBoard)
                }
            )
        }
        composable<Destination.App.StressLevelHistory> {
            StressLevelHistoryScreen(
                onNavigateStressLevel = {
                    onNavigate(Destination.App.StressLevelOverview)
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
                onNavigateToStressLevelOverview = { onNavigate(Destination.App.StressLevelOverview) }
            )
        }
        composable<Destination.App.SleepQualityOverview> {
            SleepQualityOverviewScreen(
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
                    onNavigate(Destination.App.SleepQualityOverview)
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

            EditSelfJournalScreen(
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
