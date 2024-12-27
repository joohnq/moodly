package com.joohnq.mood.ui.presentation.loading

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.mood.CustomScreenNothing
import com.joohnq.mood.state.UiState.Companion.onSuccess
import com.joohnq.mood.sharedViewModel
import com.joohnq.mood.ui.presentation.dashboard_screen.DashboardScreen
import com.joohnq.mood.ui.presentation.get_user_name.GetUserNameScreen
import com.joohnq.mood.ui.presentation.onboarding.onboarding_mood_rate.OnboardingMoodRateScreen
import com.joohnq.mood.ui.presentation.welcome.WelcomeScreen
import com.joohnq.mood.viewmodel.UserPreferenceIntent
import com.joohnq.mood.viewmodel.UserPreferenceViewModel

class LoadingScreen : CustomScreenNothing() {
    @Composable
    override fun Screen() {
        val userPreferenceViewModel: UserPreferenceViewModel = sharedViewModel()
        val userPreferencesState by userPreferenceViewModel.userPreferencesState.collectAsState()

        SideEffect {
            userPreferenceViewModel.onAction(UserPreferenceIntent.GetUserPreferences)
        }

        LaunchedEffect(userPreferencesState.userPreferences)
        {
            userPreferencesState.userPreferences.onSuccess { userPreferences ->
                when (false) {
                    userPreferences.skipWelcomeScreen -> {
                        onNavigate(WelcomeScreen(), true)
                        return@onSuccess
                    }

                    userPreferences.skipOnboardingScreen -> {
                        onNavigate(OnboardingMoodRateScreen(), true)
                        return@onSuccess
                    }

                    userPreferences.skipGetUserNameScreen -> {
                        onNavigate(GetUserNameScreen(), true)
                        return@onSuccess
                    }

                    else -> {
                        onNavigate(DashboardScreen(), false)
                        return@onSuccess
                    }
                }
            }
        }
    }

    @Composable
    override fun UI() = LoadingUI()
}
