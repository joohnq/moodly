package com.joohnq.moodapp

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.joohnq.moodapp.view.Screens
import com.joohnq.moodapp.view.home.HomeScreen
import com.joohnq.moodapp.view.loading.CompilingData
import com.joohnq.moodapp.view.loading.LoadingScreen
import com.joohnq.moodapp.view.onboarding.ExpressionAnalysisScreen
import com.joohnq.moodapp.view.onboarding.GetUserNameScreen
import com.joohnq.moodapp.view.onboarding.MedicationsSupplementsScreen
import com.joohnq.moodapp.view.onboarding.MoodRateScreen
import com.joohnq.moodapp.view.onboarding.PhysicalSymptomsScreen
import com.joohnq.moodapp.view.onboarding.ProfessionalHelpScreen
import com.joohnq.moodapp.view.onboarding.SleepQualityScreen
import com.joohnq.moodapp.view.onboarding.StressRateScreen
import com.joohnq.moodapp.view.welcome.WelcomeScreen
import com.joohnq.moodapp.viewmodel.MoodsViewModel
import com.joohnq.moodapp.viewmodel.UserPreferenceViewModel
import com.joohnq.moodapp.viewmodel.UserViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import org.koin.compose.KoinContext
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App() {
    KoinContext {
        val scope = rememberCoroutineScope()
        val ioDispatcher: CoroutineDispatcher = koinInject()
        val userPreferenceViewModel: UserPreferenceViewModel = koinViewModel()
        val userViewModel: UserViewModel = koinViewModel()
        val moodsViewModel: MoodsViewModel = koinViewModel()
        val navController = rememberNavController()

        SideEffect {
            scope.launch(ioDispatcher) {
                userPreferenceViewModel.initUserPreferences()
                userViewModel.iniUser()
            }
        }

        MaterialTheme {
            NavHost(navController, startDestination = Screens.LoadingScreen) {
                composable<Screens.LoadingScreen> {
                    LoadingScreen(navigation = navController)
                }
                composable<Screens.WelcomeScreen> {
                    WelcomeScreen(
                        navigation = navController,
                        userPreferenceViewModel = userPreferenceViewModel
                    )
                }
                navigation<Screens.OnboardingScreenGraph>(startDestination = Screens.OnboardingScreenGraph.MoodRateScreen) {
                    composable<Screens.OnboardingScreenGraph.MoodRateScreen> {
                        MoodRateScreen(navigation = navController, moodsViewModel = moodsViewModel)
                    }
                    composable<Screens.OnboardingScreenGraph.ProfessionalHelpScreen> {
                        ProfessionalHelpScreen(
                            navigation = navController,
                            userViewModel = userViewModel
                        )
                    }
                    composable<Screens.OnboardingScreenGraph.PhysicalSymptomsScreen> {
                        PhysicalSymptomsScreen(
                            navigation = navController,
                            userViewModel = userViewModel
                        )
                    }
                    composable<Screens.OnboardingScreenGraph.SleepQualityScreen> {
                        SleepQualityScreen(
                            navigation = navController,
                            moodsViewModel = moodsViewModel
                        )
                    }
                    composable<Screens.OnboardingScreenGraph.MedicationsSupplementsScreen> {
                        MedicationsSupplementsScreen(
                            navigation = navController,
                            userViewModel = userViewModel
                        )
                    }
                    composable<Screens.OnboardingScreenGraph.StressRateScreen> {
                        StressRateScreen(
                            navigation = navController,
                            moodsViewModel = moodsViewModel
                        )
                    }
                    composable<Screens.OnboardingScreenGraph.ExpressionAnalysisScreen> {
                        ExpressionAnalysisScreen(
                            navigation = navController,
                            moodsViewModel = moodsViewModel,
                            userViewModel = userViewModel,
                            userPreferencesViewModel = userPreferenceViewModel
                        )
                    }
                }
                composable<Screens.GetUserNameScreen> {
                    GetUserNameScreen(
                        navigation = navController,
                        userViewModel = userViewModel,
                        userPreferencesViewModel = userPreferenceViewModel
                    )
                }
                composable<Screens.CompilingDataScreen> {
                    CompilingData(
                        navigation = navController,
                        moodsViewModel = moodsViewModel,
                        userViewModel = userViewModel,
                    )
                }
                composable<Screens.HomeScreen> {
                    HomeScreen(
                        navigation = navController,
                        moodsViewModel = moodsViewModel,
                        userViewModel = userViewModel,
                    )
                }
            }
        }

    }
}