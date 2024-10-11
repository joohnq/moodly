@file:OptIn(ExperimentalFoundationApi::class)

package com.joohnq.moodapp.view.welcome

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.joohnq.moodapp.Colors
import com.joohnq.moodapp.view.onboarding.OnboardingScreen
import com.joohnq.moodapp.viewmodel.UserPreferenceViewModel
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

class WelcomeScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val userPreferenceViewModel: UserPreferenceViewModel = koinViewModel()
        val pagerState = rememberPagerState(0) { 5 }
        val scope = rememberCoroutineScope()
        val onNext: () -> Unit =
            { scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) } }
        Scaffold(containerColor = Colors.White) { _ ->
            HorizontalPager(
                pagerState,
            ) { page ->
                when (page) {
                    0 -> FirstScreen(
                        onGetStarted = onNext,
                        onSignIn = {}
                    )

                    1 -> HealthStateScreen(onNext = onNext)
                    2 -> IntelligentScreen(onNext = onNext)
                    3 -> ResourcesScreen(onNext = onNext)
                    4 -> CommunityScreen(onNext = {
                        userPreferenceViewModel.setSkipWelcomeScreen()
                        navigator.push(OnboardingScreen())
                    })
                }
            }
        }
    }
}
