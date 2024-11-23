package com.joohnq.moodapp

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.lifecycle.ViewModel
import cafe.adriel.voyager.navigator.Navigator
import com.joohnq.moodapp.ui.presentation.loading.LoadingScreen
import com.joohnq.moodapp.viewmodel.UserIntent
import com.joohnq.moodapp.viewmodel.UserPreferenceIntent
import com.joohnq.moodapp.viewmodel.UserPreferenceViewModel
import com.joohnq.moodapp.viewmodel.UserViewModel
import org.koin.compose.KoinContext
import org.koin.compose.koinInject

@Composable inline fun <reified T : ViewModel> sharedViewModel(): T {
    return koinInject<T>()
}

@Composable
fun App() {
    KoinContext {
        val userViewModel: UserViewModel = sharedViewModel()
        val userPreferenceViewModel: UserPreferenceViewModel = sharedViewModel()

        SideEffect {
            userPreferenceViewModel.onAction(UserPreferenceIntent.AddUserPreferences)
            userViewModel.onAction(UserIntent.InitUser)
        }

        MaterialTheme {
            Navigator(
                screen = LoadingScreen()
            )
        }
    }
}