package com.joohnq.moodapp.presentation.loading

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.joohnq.core.ui.CustomScreenNothing
import com.joohnq.core.ui.mapper.onSuccess
import com.joohnq.core.ui.sharedViewModel
import com.joohnq.domain.entity.UserPreferences
import com.joohnq.user.ui.viewmodel.user_preferences.UserPreferenceViewModel
import com.joohnq.user.ui.viewmodel.user_preferences.UserPreferenceViewModelIntent

class LoadingScreen(
    private val onNavigateToWelcome: () -> Unit,
    private val onNavigateToOnboarding: () -> Unit,
    private val onNavigateToUserName: () -> Unit,
    private val onNavigateToDashboard: () -> Unit,
) : CustomScreenNothing() {
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
                    userPreferences.skipWelcomeScreen -> onNavigateToWelcome()
                    userPreferences.skipOnboardingScreen -> onNavigateToOnboarding()
                    userPreferences.skipUserNameScreen -> onNavigateToUserName()
                    else -> onNavigateToDashboard()
                }
            }
        }
    }

    @Composable
    override fun UI() = LoadingUI()
}
