package com.joohnq.moodapp

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import cafe.adriel.voyager.navigator.Navigator
import com.joohnq.moodapp.view.loading.LoadingScreen
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