package com.joohnq.moodapp

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.joohnq.moodapp.model.entities.UserPreferences
import com.joohnq.moodapp.utils.printLn
import com.joohnq.moodapp.view.loading.LoadingScreen
import com.joohnq.moodapp.view.onboarding.OnboardingScreen
import com.joohnq.moodapp.view.welcome.WelcomeScreen
import com.joohnq.moodapp.viewmodel.UserPreferenceViewModel
import org.koin.compose.KoinContext
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App() {
    KoinContext {
        val userPreferenceViewModel: UserPreferenceViewModel = koinViewModel()

        SideEffect {
            userPreferenceViewModel.initUserPreferences()
            userPreferenceViewModel.getUserPreferences()
        }

        MaterialTheme {
            Navigator(screen = LoadingScreen())
        }
    }
}