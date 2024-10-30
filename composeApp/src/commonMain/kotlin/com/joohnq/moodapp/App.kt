package com.joohnq.moodapp

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.rememberNavController
import com.joohnq.moodapp.view.graph.CentralNavigation
import com.joohnq.moodapp.viewmodel.MoodsViewModel
import com.joohnq.moodapp.viewmodel.UserPreferenceViewModel
import com.joohnq.moodapp.viewmodel.UserViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import org.koin.compose.KoinContext
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App() {
    KoinContext {
        val scope = rememberCoroutineScope()
        val ioDispatcher: CoroutineDispatcher = koinInject()
        val userPreferenceViewModel: UserPreferenceViewModel = koinViewModel()
        val userViewModel: UserViewModel = koinViewModel()
        val moodsViewModel: MoodsViewModel = koinViewModel()
        val navController = rememberNavController()

        SideEffect {
            scope.launch(ioDispatcher) {
                userPreferenceViewModel.initUserPreferences()
                userViewModel.iniUser()
            }
        }

        MaterialTheme {
            CentralNavigation(
                navController = navController,
                userPreferenceViewModel = userPreferenceViewModel,
                userViewModel = userViewModel,
                moodsViewModel = moodsViewModel
            )
        }
    }
}