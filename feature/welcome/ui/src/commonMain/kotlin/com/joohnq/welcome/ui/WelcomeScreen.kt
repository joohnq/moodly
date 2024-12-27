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
import com.joohnq.mood.CustomScreen
import com.joohnq.mood.sharedViewModel
import com.joohnq.mood.ui.state.UiState.Companion.fold
import com.joohnq.mood.viewmodel.UserPreferenceIntent
import com.joohnq.user.ui.viewmodel.UserPreferenceViewModel
import com.joohnq.welcome.ui.state.WelcomeState
import kotlinx.coroutines.launch

class WelcomeScreen : CustomScreen<WelcomeState>() {
    @Composable
    override fun Screen(): WelcomeState {
        val userPreferenceViewModel: UserPreferenceViewModel = sharedViewModel()
        val pagerState = rememberPagerState(initialPage = 0, pageCount = { 5 })
        val snackBarState = remember { SnackbarHostState() }
        val scope = rememberCoroutineScope()
        val userPreferencesState by userPreferenceViewModel.state.collectAsState()

        fun onNext() {
            scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
        }

        LaunchedEffect(userPreferencesState.updating) {
            userPreferencesState.updating.fold(
                onSuccess = {
//                    onNavigate(OnboardingMoodRateScreen(), true)
                },
                onError = {
                    scope.launch {
                        snackBarState.showSnackbar(it)
                    }
                }
            )
        }

        DisposableEffect(Unit) {
            onDispose {
                userPreferenceViewModel.onAction(UserPreferenceIntent.ResetUpdating)
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
