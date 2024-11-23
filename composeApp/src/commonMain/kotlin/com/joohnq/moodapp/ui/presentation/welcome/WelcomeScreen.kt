package com.joohnq.moodapp.ui.presentation.welcome

import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.ui.CustomScreen
import com.joohnq.moodapp.ui.presentation.onboarding.onboarding_mood_rate.OnboardingMoodRateScreen
import com.joohnq.moodapp.ui.presentation.welcome.state.WelcomeState
import com.joohnq.moodapp.ui.state.UiState.Companion.fold
import com.joohnq.moodapp.viewmodel.UserPreferenceIntent
import com.joohnq.moodapp.viewmodel.UserPreferenceViewModel
import kotlinx.coroutines.launch

class WelcomeScreen : CustomScreen<WelcomeState>() {
    @Composable
    override fun Screen(): WelcomeState {
        val userPreferenceViewModel: UserPreferenceViewModel = sharedViewModel()
        val pagerState = rememberPagerState(initialPage = 0, pageCount = { 5 })
        val snackBarState = remember { SnackbarHostState() }
        val scope = rememberCoroutineScope()
        val userPreferencesState by userPreferenceViewModel.userPreferencesState.collectAsState()

        fun onNext() {
            scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
        }

        LaunchedEffect(userPreferencesState.updating) {
            userPreferencesState.updating.fold(
                onSuccess = {
                    onNavigate(OnboardingMoodRateScreen())
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
}
