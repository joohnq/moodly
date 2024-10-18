package com.joohnq.moodapp.view.loading

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.view.constants.Colors
import com.joohnq.moodapp.model.entities.UserPreferences
import com.joohnq.moodapp.view.BasicScreen
import com.joohnq.moodapp.view.home.HomeScreen
import com.joohnq.moodapp.view.onboarding.GetUserNameScreen
import com.joohnq.moodapp.view.onboarding.MoodRateScreen
import com.joohnq.moodapp.view.state.UiState
import com.joohnq.moodapp.view.state.onSuccess
import com.joohnq.moodapp.view.welcome.WelcomeScreen
import com.joohnq.moodapp.viewmodel.UserPreferenceViewModel
import org.koin.compose.koinInject

class LoadingScreen : BasicScreen() {
    @Composable
    override fun Init() {
        val userPreferenceViewModel: UserPreferenceViewModel = koinInject()
        val userPreferences: UiState<UserPreferences> by userPreferenceViewModel.userPreferences.collectAsState()

        LaunchedEffect(userPreferences) {
            userPreferences.onSuccess { userPreferences ->
                val screen = when (true) {
                    !userPreferences.skipWelcomeScreen -> WelcomeScreen()
                    !userPreferences.skipOnboardingScreen -> MoodRateScreen()
                    !userPreferences.skipGetUserNameScreen -> GetUserNameScreen()
                    else -> HomeScreen()
                }
                navigator.push(screen)
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
}