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
import com.joohnq.user.ui.viewmodel.user_preferences.UserPreferenceViewModelIntent
import com.joohnq.user.ui.viewmodel.user_preferences.UserPreferencesViewModel

class LoadingScreen(
    private val onNavigateToWelcome: () -> Unit,
    private val onNavigateToOnboarding: () -> Unit,
    private val onNavigateToUserName: () -> Unit,
    private val onNavigateToDashboard: () -> Unit,
) : CustomScreenNothing() {
    @Composable
    override fun Screen() {
        val userPreferencesViewModel: UserPreferencesViewModel = sharedViewModel()
        val userPreferencesState by userPreferencesViewModel.state.collectAsState()

        SideEffect {
            userPreferencesViewModel.onAction(UserPreferenceViewModelIntent.GetUserPreferences)
        }

        LaunchedEffect(userPreferencesState.userPreferences)
        {
            userPreferencesState.userPreferences.onSuccess { userPreferences: UserPreferences ->
                when (false) {
                    userPreferences.skipWelcome -> onNavigateToWelcome()
                    userPreferences.skipOnboarding -> onNavigateToOnboarding()
                    userPreferences.skipAuth -> onNavigateToUserName()
                    else -> onNavigateToDashboard()
                }
            }
        }
    }

    @Composable
    override fun UI() = LoadingUI()
}
