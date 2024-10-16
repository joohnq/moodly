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
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.joohnq.moodapp.Colors
import com.joohnq.moodapp.view.entities.User
import com.joohnq.moodapp.view.entities.UserPreferences
import com.joohnq.moodapp.view.home.HomeScreen
import com.joohnq.moodapp.view.onboarding.GetUserNameScreen
import com.joohnq.moodapp.view.onboarding.MoodRateScreen
import com.joohnq.moodapp.view.state.UiState
import com.joohnq.moodapp.view.state.fold
import com.joohnq.moodapp.view.state.onSuccess
import com.joohnq.moodapp.view.welcome.WelcomeScreen
import com.joohnq.moodapp.viewmodel.UserPreferenceViewModel
import com.joohnq.moodapp.viewmodel.UserViewModel
import org.koin.compose.koinInject

class LoadingScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val userViewModel: UserViewModel = koinInject()
        val userPreferenceViewModel: UserPreferenceViewModel = koinInject()
        val userPreferences: UiState<UserPreferences> by userPreferenceViewModel.userPreferences.collectAsState()
        val user: UiState<User> by userViewModel.user.collectAsState()

        LaunchedEffect(userPreferences) {
            userPreferences.fold(
                onSuccess = { userPreferences ->
                    val screen = when (true) {
                        userPreferences.skipWelcomeScreen -> WelcomeScreen()
                        userPreferences.skipOnboardingScreen -> MoodRateScreen()
                        else -> {
                            var screen: Screen = GetUserNameScreen()
                            user.onSuccess { user ->
                                if (user.name.isNotEmpty()) {
                                    screen = HomeScreen()
                                }
                            }
                            screen
                        }
                    }
                    navigator.push(screen)
                }
            )
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