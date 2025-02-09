package com.joohnq.welcome.ui

import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.core.ui.ObserverSideEffects
import com.joohnq.core.ui.sharedViewModel
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.user.ui.viewmodel.user_preferences.UserPreferencesSideEffect
import com.joohnq.user.ui.viewmodel.user_preferences.UserPreferencesViewModel
import kotlinx.coroutines.launch

@Composable
fun WelcomeScreen(onNavigateToOnboarding: () -> Unit) {
    val userPreferencesViewModel: UserPreferencesViewModel = sharedViewModel()
    val snackBarState = rememberSnackBarState()
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 6 })

    fun onError(message: String) {
        scope.launch {
            snackBarState.showSnackbar(message)
        }
    }

    ObserverSideEffects(
        flow = userPreferencesViewModel.sideEffect,
        onEvent = { effect ->
            when (effect) {
                is UserPreferencesSideEffect.ShowError -> onError(effect.message)
                UserPreferencesSideEffect.UpdatedUserPreferences -> onNavigateToOnboarding()
                else -> Unit
            }
        }
    )

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
