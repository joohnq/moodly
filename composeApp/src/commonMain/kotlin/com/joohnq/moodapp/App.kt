package com.joohnq.moodapp

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import cafe.adriel.voyager.navigator.Navigator
import com.joohnq.core.ui.presentation.loading.LoadingScreen
import com.joohnq.mood.sharedViewModel
import com.joohnq.user.ui.viewmodel.UserPreferenceViewModel
import com.joohnq.user.ui.viewmodel.UserPreferenceViewModelIntent
import com.joohnq.user.ui.viewmodel.UserViewModel
import com.joohnq.user.ui.viewmodel.UserViewModelIntent
import org.koin.compose.KoinContext

@Composable
fun App() {
    KoinContext {
        val userViewModel: UserViewModel = sharedViewModel()
        val userPreferenceViewModel: UserPreferenceViewModel = sharedViewModel()

        SideEffect {
            userPreferenceViewModel.onAction(UserPreferenceViewModelIntent.AddUserPreferences)
            userViewModel.onAction(UserViewModelIntent.InitUser)
        }

        MaterialTheme { Navigator(LoadingScreen()) }
    }
}