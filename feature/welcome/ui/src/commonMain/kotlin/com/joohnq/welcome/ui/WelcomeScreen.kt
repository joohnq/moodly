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
import com.joohnq.shared.ui.CustomScreen
import com.joohnq.shared.ui.sharedViewModel
import com.joohnq.shared.ui.state.UiState.Companion.fold
import com.joohnq.user.ui.viewmodel.user_preferences.UserPreferenceViewModel
import com.joohnq.user.ui.viewmodel.user_preferences.UserPreferenceViewModelIntent
import com.joohnq.welcome.ui.state.WelcomeState
import kotlinx.coroutines.launch

class WelcomeScreen(private val onNavigateToOnboarding: () -> Unit) : CustomScreen<WelcomeState>() {
    @Composable
    override fun Screen(): WelcomeState {
        val userPreferenceViewModel: UserPreferenceViewModel = sharedViewModel()
        val pagerState = rememberPagerState(initialPage = 0, pageCount = { 5 })
        val snackBarState = remember { SnackbarHostState() }
        val scope = rememberCoroutineScope()
        val userPreferencesState by userPreferenceViewModel.state.collectAsState()

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
                onSuccess = {
                    onNavigateToOnboarding()
                },
                onError = ::onError
            )
        }

        DisposableEffect(Unit) {
            onDispose {
                userPreferenceViewModel.onAction(UserPreferenceViewModelIntent.ResetUpdating)
            }
        }

        return WelcomeState(
            snackBarState = snackBarState,
            pagerState = pagerState,
            onNext = ::onNext,
            onAction = userPreferenceViewModel::onAction
        )
    }

    @Composable
    override fun UI(state: WelcomeState) = WelcomeUI(state)

    object WelcomeTestTag {
        const val HORIZONTAL_PAGER = "HORIZONTAL_PAGER"
        const val GO_NEXT = "GO_NEXT"
        const val SNACK_BAR = "SNACK_BAR"
    }
}
