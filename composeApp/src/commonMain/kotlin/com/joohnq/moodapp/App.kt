package com.joohnq.moodapp

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.joohnq.moodapp.view.home.HomeScreen
import com.joohnq.moodapp.view.home.HomeScreenObject
import com.joohnq.moodapp.view.loading.LoadingScreen
import com.joohnq.moodapp.view.loading.LoadingScreenObject
import com.joohnq.moodapp.view.onboarding.ExpressionAnalysisScreen
import com.joohnq.moodapp.view.onboarding.ExpressionAnalysisScreenObject
import com.joohnq.moodapp.view.onboarding.GetUserNameScreen
import com.joohnq.moodapp.view.onboarding.GetUserNameScreenObject
import com.joohnq.moodapp.view.onboarding.MedicationsSupplementsScreen
import com.joohnq.moodapp.view.onboarding.MedicationsSupplementsScreenObject
import com.joohnq.moodapp.view.onboarding.MoodRateScreen
import com.joohnq.moodapp.view.onboarding.MoodRateScreenObject
import com.joohnq.moodapp.view.onboarding.OnboardingScreenObject
import com.joohnq.moodapp.view.onboarding.PhysicalSymptomsScreen
import com.joohnq.moodapp.view.onboarding.PhysicalSymptomsScreenObject
import com.joohnq.moodapp.view.onboarding.ProfessionalHelpScreen
import com.joohnq.moodapp.view.onboarding.ProfessionalHelpScreenObject
import com.joohnq.moodapp.view.onboarding.SleepQualityScreen
import com.joohnq.moodapp.view.onboarding.SleepQualityScreenObject
import com.joohnq.moodapp.view.onboarding.StressRateScreen
import com.joohnq.moodapp.view.onboarding.StressRateScreenObject
import com.joohnq.moodapp.view.routes.onNavigateToExpressionAnalysis
import com.joohnq.moodapp.view.routes.onNavigateToGetUserName
import com.joohnq.moodapp.view.routes.onNavigateToGetUserNameScreen
import com.joohnq.moodapp.view.routes.onNavigateToHomeScreen
import com.joohnq.moodapp.view.routes.onNavigateToMedicationsSupplements
import com.joohnq.moodapp.view.routes.onNavigateToOnboardingScreen
import com.joohnq.moodapp.view.routes.onNavigateToPhysicalSymptoms
import com.joohnq.moodapp.view.routes.onNavigateToProfessionalHelp
import com.joohnq.moodapp.view.routes.onNavigateToSleepQuality
import com.joohnq.moodapp.view.routes.onNavigateToStressRate
import com.joohnq.moodapp.view.routes.onNavigateToWelcomeScreen
import com.joohnq.moodapp.view.welcome.WelcomeScreen
import com.joohnq.moodapp.view.welcome.WelcomeScreenObject
import com.joohnq.moodapp.viewmodel.UserPreferenceViewModel
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
        val navController = rememberNavController()

        SideEffect {
            scope.launch(ioDispatcher) {
                userPreferenceViewModel.initUserPreferences()
            }
        }

        MaterialTheme {
            NavHost(navController, startDestination = LoadingScreenObject) {
                composable<LoadingScreenObject> {
                    LoadingScreen(
                        onNavigateToHomeScreen = navController::onNavigateToHomeScreen,
                        onNavigateToWelcomeScreen = navController::onNavigateToWelcomeScreen,
                        onNavigateToMoodRateScreen = navController::onNavigateToOnboardingScreen,
                        onNavigateToGetUserNameScreen = navController::onNavigateToGetUserNameScreen
                    )
                }
                composable<WelcomeScreenObject> {
                    WelcomeScreen(
                        onNavigateToMoodRateScreen = navController::onNavigateToOnboardingScreen
                    )
                }
                navigation<OnboardingScreenObject>(startDestination = MoodRateScreenObject) {
                    composable<MoodRateScreenObject> {
                        MoodRateScreen(
                            onGoBack = navController::popBackStack,
                            onNavigateToProfessionalHelp = navController::onNavigateToProfessionalHelp
                        )
                    }
                    composable<ProfessionalHelpScreenObject> {
                        ProfessionalHelpScreen(
                            onGoBack = navController::popBackStack,
                            onNavigateToPhysicalSymptoms = navController::onNavigateToPhysicalSymptoms
                        )
                    }
                    composable<PhysicalSymptomsScreenObject> {
                        PhysicalSymptomsScreen(
                            onGoBack = navController::popBackStack,
                            onNavigateToSleepQuality = navController::onNavigateToSleepQuality
                        )
                    }
                    composable<SleepQualityScreenObject> {
                        SleepQualityScreen(
                            onGoBack = navController::popBackStack,
                            onNavigateToMedicationsSupplements = navController::onNavigateToMedicationsSupplements
                        )
                    }
                    composable<MedicationsSupplementsScreenObject> {
                        MedicationsSupplementsScreen(
                            onGoBack = navController::popBackStack,
                            onNavigateToStressRate = navController::onNavigateToStressRate
                        )
                    }
                    composable<StressRateScreenObject> {
                        StressRateScreen(
                            onGoBack = navController::popBackStack,
                            onNavigateToExpressionAnalysis = navController::onNavigateToExpressionAnalysis
                        )
                    }
                    composable<ExpressionAnalysisScreenObject> {
                        ExpressionAnalysisScreen(
                            onGoBack = navController::popBackStack,
                            onNavigateToGetUserName = navController::onNavigateToGetUserName
                        )
                    }
                }
                composable<GetUserNameScreenObject> {
                    GetUserNameScreen(onNavigateToHomeScreen = navController::onNavigateToHomeScreen)
                }
                composable<HomeScreenObject> {
                    HomeScreen()
                }
            }
        }

    }
}