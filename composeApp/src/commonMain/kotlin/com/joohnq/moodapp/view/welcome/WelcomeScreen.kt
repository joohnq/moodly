@file:OptIn(ExperimentalFoundationApi::class)

package com.joohnq.moodapp.view.welcome

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.joohnq.moodapp.view.constants.Colors
import com.joohnq.moodapp.view.BasicScreen
import com.joohnq.moodapp.view.onboarding.MoodRateScreen
import com.joohnq.moodapp.viewmodel.UserPreferenceViewModel
import kotlinx.coroutines.launch
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.something_went_wrong
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

class WelcomeScreen : BasicScreen() {
    @Composable
    override fun Init() {
        val userPreferenceViewModel: UserPreferenceViewModel = koinInject()
        val pagerState = rememberPagerState(0) { 5 }
        val snackBarState = remember { SnackbarHostState() }
        val somethingWentWrong = stringResource(Res.string.something_went_wrong)

        val onNext: () -> Unit =
            { scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) } }

        Scaffold(snackbarHost = { SnackbarHost(hostState = snackBarState) },containerColor = Colors.White) { _ ->
            HorizontalPager(
                pagerState,
            ) { page ->
                when (page) {
                    0 -> FirstScreen(onGetStarted = onNext,)
                    1 -> HealthStateScreen(onNext = onNext)
                    2 -> IntelligentScreen(onNext = onNext)
                    3 -> ResourcesScreen(onNext = onNext)
                    4 -> CommunityScreen(onNext = {
                        scope.launch(ioDispatcher) {
                            val res = userPreferenceViewModel.setSkipWelcomeScreen()
                            if (!res) {
                                snackBarState.showSnackbar(somethingWentWrong)
                                return@launch
                            }
                            navigator.push(MoodRateScreen())
                        }
                    })
                }
            }
        }
    }
}
