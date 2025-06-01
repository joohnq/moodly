package com.joohnq.welcome.ui

import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.preferences.ui.viewmodel.PreferencesContract
import com.joohnq.preferences.ui.viewmodel.PreferencesViewModel
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.ui.ObserverSideEffects
import com.joohnq.ui.sharedViewModel
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun WelcomeScreen(
    onNavigateToOnboarding: () -> Unit = {}
) {
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
                is PreferencesContract.SideEffect.ShowError -> onError(effect.error.message.toString())
                PreferencesContract.SideEffect.PreferencesUpdated -> onNavigateToOnboarding()
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
        onIntent = preferencesViewModel::onIntent
    )
}

@Preview
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen()
}
