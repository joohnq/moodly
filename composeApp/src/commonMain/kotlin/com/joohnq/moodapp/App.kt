package com.joohnq.moodapp

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import cafe.adriel.voyager.navigator.Navigator
import com.joohnq.moodapp.utils.Log
import com.joohnq.moodapp.view.loading.LoadingScreen
import com.joohnq.moodapp.viewmodel.UserPreferenceViewModel
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import org.koin.compose.KoinContext
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App() {
    KoinContext {
        val userPreferenceViewModel: UserPreferenceViewModel = koinInject()

        SideEffect {
            userPreferenceViewModel.initUserPreferences()
            userPreferenceViewModel.getUserPreferences()
            Log(Clock.System.todayIn(TimeZone.currentSystemDefault()))
        }

        MaterialTheme {
            Navigator(screen = LoadingScreen())
        }
    }
}