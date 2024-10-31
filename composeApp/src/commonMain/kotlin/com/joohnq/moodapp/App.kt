package com.joohnq.moodapp

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.joohnq.moodapp.view.graph.CentralNavigation
import com.joohnq.moodapp.viewmodel.UserPreferenceViewModel
import com.joohnq.moodapp.viewmodel.UserViewModel
import kotlinx.coroutines.CoroutineDispatcher
import org.koin.compose.KoinContext
import org.koin.compose.currentKoinScope
import org.koin.compose.koinInject

@Composable
inline fun <reified T: ViewModel> sharedViewModel(): T {
    val scope = currentKoinScope()
    return viewModel {
        scope.get<T>()
    }
}

@Composable
fun App() {
    KoinContext {
        val userPreferenceViewModel: UserPreferenceViewModel = sharedViewModel()
        val userViewModel: UserViewModel = sharedViewModel()
        val navController = rememberNavController()

        LaunchedEffect(Unit) {
            userPreferenceViewModel.initUserPreferences()
            userViewModel.iniUser()
        }

        MaterialTheme {
            navController.CentralNavigation()
        }
    }
}