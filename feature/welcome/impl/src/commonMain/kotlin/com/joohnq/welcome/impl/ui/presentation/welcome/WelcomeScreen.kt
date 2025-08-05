package com.joohnq.welcome.impl.ui.presentation.welcome

import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.ui.sharedViewModel
import kotlinx.coroutines.launch

@Composable
fun WelcomeScreen(
    onNavigateToOnboarding: () -> Unit,
    viewModel: WelcomeViewModel = sharedViewModel(),
) {
    val snackBarState = rememberSnackBarState()
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(initialPage = 0, pageCount = { 6 })

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is WelcomeContract.SideEffect.ShowError ->
                    snackBarState.showSnackbar(sideEffect.message)

                WelcomeContract.SideEffect.NavigateNext -> onNavigateToOnboarding()
            }
        }
    }

    fun onEvent(event: WelcomeContract.Event) {
        when (event) {
            WelcomeContract.Event.Next ->
                scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
        }
    }

    WelcomeContent(
        snackBarState = snackBarState,
        pagerState = pagerState,
        onEvent = ::onEvent,
        onIntent = viewModel::onIntent
    )
}