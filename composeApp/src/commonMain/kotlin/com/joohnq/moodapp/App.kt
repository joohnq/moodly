package com.joohnq.moodapp

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.ViewModel
import com.joohnq.moodapp.view.graph.AppNavigation
import com.joohnq.moodapp.viewmodel.UserPreferenceIntent
import com.joohnq.moodapp.viewmodel.UserPreferenceViewModel
import org.koin.compose.KoinContext
import org.koin.compose.koinInject

@Composable inline fun <reified T : ViewModel> sharedViewModel(): T {
    return koinInject<T>()
}

@Composable
fun App() {
    KoinContext {
        val userPreferenceViewModel: UserPreferenceViewModel = sharedViewModel()

        LaunchedEffect(Unit) {
            userPreferenceViewModel.onAction(UserPreferenceIntent.AddUserPreferences)
        }

        MaterialTheme { AppNavigation() }
    }
}