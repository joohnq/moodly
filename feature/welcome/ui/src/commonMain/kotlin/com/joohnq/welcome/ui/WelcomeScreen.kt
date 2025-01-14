package com.joohnq.welcome.ui

import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.core.ui.mapper.fold
import com.joohnq.core.ui.sharedViewModel
import com.joohnq.user.ui.viewmodel.user_preferences.UserPreferenceViewModelIntent
import com.joohnq.user.ui.viewmodel.user_preferences.UserPreferencesViewModel
import com.joohnq.welcome.ui.state.WelcomeState
import kotlinx.coroutines.launch

@Composable
fun WelcomeScreen(onNavigateToOnboarding: () -> Unit) {
    val userPreferencesViewModel: UserPreferencesViewModel = sharedViewModel()
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 5 })
    val snackBarState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val userPreferencesState by userPreferencesViewModel.state.collectAsState()

    fun onError(message: String) {
        scope.launch {
            snackBarState.showSnackbar(message)
        }
    }

    fun onNext() {
        scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
    }

    LaunchedEffect(userPreferencesState.updating) {
        userPreferencesState.updating.fold(
            onSuccess = { onNavigateToOnboarding() },
            onError = ::onError
        )
    }

    DisposableEffect(Unit) {
        onDispose {
            userPreferencesViewModel.onAction(UserPreferenceViewModelIntent.ResetUpdating)
        }
    }

    WelcomeUI(
        WelcomeState(
            snackBarState = snackBarState,
            pagerState = pagerState,
            onNext = ::onNext,
            onAction = userPreferencesViewModel::onAction
        )
    )
}
