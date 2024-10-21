package com.joohnq.moodapp.view.loading

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
import com.joohnq.moodapp.model.entities.UserPreferences
import com.joohnq.moodapp.view.constants.Colors
import com.joohnq.moodapp.view.state.UiState
import com.joohnq.moodapp.view.state.onSuccess
import com.joohnq.moodapp.viewmodel.UserPreferenceViewModel
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel

@Serializable
object LoadingScreenObject

@Composable
fun LoadingScreen(
    onNavigateToWelcomeScreen: () -> Unit,
    onNavigateToMoodRateScreen: () -> Unit,
    onNavigateToGetUserNameScreen: () -> Unit,
    onNavigateToHomeScreen: () -> Unit
) {
    val userPreferenceViewModel: UserPreferenceViewModel = koinViewModel()
    val userPreferences: UiState<UserPreferences> by userPreferenceViewModel.userPreferences.collectAsState()

    SideEffect {
        userPreferenceViewModel.getUserPreferences()
    }

    LaunchedEffect(userPreferences) {
        println("rodou")
        userPreferences.onSuccess { userPreferences ->
            println("rodou2")
            when (false) {
                userPreferences.skipWelcomeScreen -> onNavigateToWelcomeScreen()
                userPreferences.skipOnboardingScreen -> onNavigateToMoodRateScreen()
                userPreferences.skipGetUserNameScreen -> onNavigateToGetUserNameScreen()
                else -> onNavigateToHomeScreen()
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
