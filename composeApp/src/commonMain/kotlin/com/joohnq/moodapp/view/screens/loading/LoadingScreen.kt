package com.joohnq.moodapp.view.screens.loading

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.view.constants.Colors
import com.joohnq.moodapp.view.routes.onNavigateToGetUserNameScreen
import com.joohnq.moodapp.view.routes.onNavigateToHomeGraph
import com.joohnq.moodapp.view.routes.onNavigateToOnboardingScreen
import com.joohnq.moodapp.view.routes.onNavigateToWelcomeScreen
import com.joohnq.moodapp.view.state.UiState.Companion.onSuccess
import com.joohnq.moodapp.viewmodel.UserPreferenceIntent
import com.joohnq.moodapp.viewmodel.UserPreferenceViewModel
import com.joohnq.moodapp.viewmodel.UserViewModel

@Composable
fun LoadingScreen(
    navigation: NavController = rememberNavController()
) {
    val userPreferenceViewModel: UserPreferenceViewModel = sharedViewModel()
    val userViewModel: UserViewModel = sharedViewModel()
    val userPreferencesState by userPreferenceViewModel.userPreferencesState.collectAsState()

    SideEffect {
        userViewModel.initUser()
        userPreferenceViewModel.onAction(UserPreferenceIntent.GetUserPreferences)
    }

    LaunchedEffect(userPreferencesState.userPreferences) {
        userPreferencesState.userPreferences.onSuccess { userPreferences ->
            when (false) {
                userPreferences.skipWelcomeScreen -> navigation.onNavigateToWelcomeScreen()
                userPreferences.skipOnboardingScreen -> navigation.onNavigateToOnboardingScreen()
                userPreferences.skipGetUserNameScreen -> navigation.onNavigateToGetUserNameScreen()
                else -> navigation.onNavigateToHomeGraph()
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center

    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(50.dp),
            color = Colors.Brown60,
            strokeWidth = 6.dp,
            strokeCap = StrokeCap.Round
        )
    }
}
