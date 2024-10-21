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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.joohnq.moodapp.constants.TestConstants
import com.joohnq.moodapp.view.constants.Colors
import com.joohnq.moodapp.viewmodel.UserPreferenceViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.something_went_wrong
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Serializable
object WelcomeScreenObject

@Composable
fun WelcomeScreen(onNavigateToMoodRateScreen: () -> Unit) {
    val userPreferenceViewModel: UserPreferenceViewModel = koinViewModel()
    val pagerState = rememberPagerState(0) { 5 }
    val snackBarState = remember { SnackbarHostState() }
    val somethingWentWrong = stringResource(Res.string.something_went_wrong)
    val scope = rememberCoroutineScope()
    val ioDispatcher: CoroutineDispatcher = koinInject()

    val onNext: () -> Unit =
        { scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) } }

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
                    scope.launch(ioDispatcher) {
                        val res = userPreferenceViewModel.setSkipWelcomeScreen()
                        if (!res) {
                            snackBarState.showSnackbar(somethingWentWrong)
                            return@launch
                        }
                        onNavigateToMoodRateScreen()
                    }
                })
            }
        }
    }
}
