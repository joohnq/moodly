package com.joohnq.core.ui.presentation.loading

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.domain.entity.UserPreferences
import com.joohnq.mood.CustomScreenNothing
import com.joohnq.mood.sharedViewModel
import com.joohnq.mood.state.UiState.Companion.onSuccess
import com.joohnq.user.ui.viewmodel.UserPreferenceViewModel
import com.joohnq.user.ui.viewmodel.UserPreferenceViewModelIntent

class LoadingScreen : CustomScreenNothing() {
    @Composable
    override fun Screen() {
        val userPreferenceViewModel: UserPreferenceViewModel = sharedViewModel()
        val userPreferencesState by userPreferenceViewModel.state.collectAsState()

        SideEffect {
            userPreferenceViewModel.onAction(UserPreferenceViewModelIntent.GetUserPreferences)
        }

        LaunchedEffect(userPreferencesState.userPreferences)
        {
            userPreferencesState.userPreferences.onSuccess { userPreferences: UserPreferences ->
                when (false) {
                    userPreferences.skipWelcomeScreen -> {
//                        onNavigate(WelcomeScreen(), true)
                        return@onSuccess
                    }

                    userPreferences.skipOnboardingScreen -> {
//                        onNavigate(OnboardingMoodRateScreen(), true)
                        return@onSuccess
                    }

                    userPreferences.skipGetUserNameScreen -> {
//                        onNavigate(GetUserNameScreen(), true)
                        return@onSuccess
                    }

                    else -> {
//                        onNavigate(DashboardScreen(), false)
                        return@onSuccess
                    }
                }
            }
        }
    }

    @Composable
    override fun UI() = LoadingUI()
}
