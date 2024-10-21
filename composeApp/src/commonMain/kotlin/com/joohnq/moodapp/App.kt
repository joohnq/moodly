package com.joohnq.moodapp

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import cafe.adriel.voyager.navigator.Navigator
import com.joohnq.moodapp.view.loading.LoadingScreen
import com.joohnq.moodapp.viewmodel.UserPreferenceViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import org.koin.compose.KoinContext
import org.koin.compose.koinInject

@Composable
fun App() {
    KoinContext {
        val scope = rememberCoroutineScope()
        val ioDispatcher: CoroutineDispatcher = koinInject()
        val userPreferenceViewModel: UserPreferenceViewModel = koinInject()

        SideEffect {
            scope.launch(ioDispatcher) {
                userPreferenceViewModel.initUserPreferences()
            }
            userPreferenceViewModel.getUserPreferences()
        }

        MaterialTheme {
            Navigator(screen = LoadingScreen())
        }
    }
}