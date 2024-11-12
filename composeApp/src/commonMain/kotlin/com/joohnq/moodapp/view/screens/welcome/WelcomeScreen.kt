package com.joohnq.moodapp.view.screens.welcome

import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.joohnq.moodapp.constants.TestConstants
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.view.routes.onNavigateToRoute
import com.joohnq.moodapp.view.ui.Colors
import com.joohnq.moodapp.viewmodel.UserPreferenceIntent
import com.joohnq.moodapp.viewmodel.UserPreferenceSideEffect
import com.joohnq.moodapp.viewmodel.UserPreferenceViewModel
import kotlinx.coroutines.launch

@Composable
fun WelcomeScreenUI(
    snackBarState: SnackbarHostState = remember { SnackbarHostState() },
    pagerState: PagerState,
    onNext: () -> Unit,
    onAction: (UserPreferenceIntent) -> Unit
) {
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarState) },
        containerColor = Colors.White
    ) { _ ->
        HorizontalPager(
            pagerState,
            modifier = Modifier.testTag(TestConstants.WELCOME_SCREEN_HORIZONTAL_PAGER),
            key = { it }
        ) { page ->
            when (page) {
                0 -> FirstScreen(onGetStarted = onNext)
                1 -> HealthStateScreen(onNext = onNext)
                2 -> IntelligentScreen(onNext = onNext)
                3 -> ResourcesScreen(onNext = onNext)
                4 -> CommunityScreen(onNext = {
                    onAction(
                        UserPreferenceIntent.UpdateSkipWelcomeScreen()
                    )
                })
            }
        }
    }
}

@Composable
fun WelcomeScreen(
    navigation: NavController = rememberNavController(),
    userPreferenceViewModel: UserPreferenceViewModel = sharedViewModel()
) {
    val pagerState = rememberPagerState(0) { 5 }
    val snackBarState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val onNext: () -> Unit =
        { scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) } }

    LaunchedEffect(true) {
        userPreferenceViewModel.sideEffect.collect { effect ->
            when (effect) {
                is UserPreferenceSideEffect.ShowToast -> {
                    scope.launch {
                        snackBarState.showSnackbar(effect.message)
                    }
                }

                is UserPreferenceSideEffect.NavigateTo -> navigation.onNavigateToRoute(effect.route)
            }
        }
    }

    WelcomeScreenUI(
        snackBarState = snackBarState,
        pagerState = pagerState,
        onNext = onNext,
        onAction = userPreferenceViewModel::onAction
    )
}
