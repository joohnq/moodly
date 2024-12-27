package com.joohnq.mood

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.lifecycle.ViewModel
import cafe.adriel.voyager.navigator.Navigator
import com.joohnq.mood.ui.presentation.loading.LoadingScreen
import com.joohnq.mood.viewmodel.UserIntent
import com.joohnq.mood.viewmodel.UserPreferenceIntent
import com.joohnq.mood.viewmodel.UserPreferenceViewModel
import com.joohnq.mood.viewmodel.UserViewModel
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