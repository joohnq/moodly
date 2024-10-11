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
import com.joohnq.moodapp.model.entities.UserPreferences
import com.joohnq.moodapp.view.onboarding.OnboardingScreen
import com.joohnq.moodapp.view.welcome.WelcomeScreen
import com.joohnq.moodapp.viewmodel.UserPreferenceViewModel
import org.koin.compose.viewmodel.koinViewModel

class LoadingScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val userPreferenceViewModel: UserPreferenceViewModel = koinViewModel()
        val userPreferences: UserPreferences? by userPreferenceViewModel.userPreferences.collectAsState()

        LaunchedEffect(userPreferences) {
            navigator.push(if (userPreferences?.skipWelcomeScreen == true) OnboardingScreen() else WelcomeScreen())
        }

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(modifier = Modifier.size(50.dp), color = Colors.Brown60, strokeWidth = 6.dp, strokeCap = StrokeCap.Round)
        }
    }
}