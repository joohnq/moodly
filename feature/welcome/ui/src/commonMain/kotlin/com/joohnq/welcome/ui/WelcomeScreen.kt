package com.joohnq.welcome.ui

import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.core.ui.mapper.fold
import com.joohnq.core.ui.sharedViewModel
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.user.ui.viewmodel.user_preferences.UserPreferenceIntent
import com.joohnq.user.ui.viewmodel.user_preferences.UserPreferencesViewModel
import kotlinx.coroutines.launch

@Composable
fun WelcomeScreen(onNavigateToOnboarding: () -> Unit) {
    val userPreferencesViewModel: UserPreferencesViewModel = sharedViewModel()
    val snackBarState = rememberSnackBarState()
    val scope = rememberCoroutineScope()
    val userPreferencesState by userPreferencesViewModel.state.collectAsState()
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 6 })

    fun onError(message: String) {
        scope.launch {
            snackBarState.showSnackbar(message)
        }
    }

    LaunchedEffect(userPreferencesState.updating) {
        userPreferencesState.updating.fold(
            onSuccess = { onNavigateToOnboarding() },
            onError = ::onError
        )
    }

    DisposableEffect(Unit) {
        onDispose {
            userPreferencesViewModel.onAction(UserPreferenceIntent.ResetUpdating)
        }
    }

    fun onNext() {
        scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
    }


    WelcomeUI(
        snackBarState = snackBarState,
        pagerState = pagerState,
        onNext = ::onNext,
        onAction = userPreferencesViewModel::onAction
    )
}
