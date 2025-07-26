package com.joohnq.welcome.impl.ui.presentation.welcome

import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.preferences.impl.ui.viewmodel.PreferencesContract
import com.joohnq.preferences.impl.ui.viewmodel.PreferencesViewModel
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.ui.ObserverSideEffects
import com.joohnq.ui.sharedViewModel
import kotlinx.coroutines.launch

@Composable
fun WelcomeScreen(onNavigateToOnboarding: () -> Unit) {
    val preferencesViewModel: PreferencesViewModel = sharedViewModel()
    val snackBarState = rememberSnackBarState()
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 6 })

    fun onError(message: String) {
        scope.launch {
            snackBarState.showSnackbar(message)
        }
    }

    ObserverSideEffects(
        flow = preferencesViewModel.sideEffect,
        onEvent = { effect ->
            when (effect) {
                is PreferencesContract.SideEffect.ShowError -> onError(effect.message)
                PreferencesContract.SideEffect.UpdatedPreferences -> onNavigateToOnboarding()
            }
        }
    )

    fun onNext() {
        scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
    }

    WelcomeContent(
        snackBarState = snackBarState,
        pagerState = pagerState,
        onNext = ::onNext,
        onAction = preferencesViewModel::onIntent
    )
}
