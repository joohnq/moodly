package com.joohnq.moodapp.ui.presentation.welcome

import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.joohnq.moodapp.ui.presentation.welcome.screens.CommunityScreen
import com.joohnq.moodapp.ui.presentation.welcome.screens.FirstScreen
import com.joohnq.moodapp.ui.presentation.welcome.screens.HealthStateScreen
import com.joohnq.moodapp.ui.presentation.welcome.screens.IntelligentScreen
import com.joohnq.moodapp.ui.presentation.welcome.screens.ResourcesScreen
import com.joohnq.moodapp.ui.presentation.welcome.state.WelcomeState
import com.joohnq.moodapp.ui.theme.Colors
import com.joohnq.moodapp.util.constants.TestConstants
import com.joohnq.moodapp.viewmodel.UserPreferenceIntent

@Composable
fun WelcomeUI(
    state: WelcomeState,
) {
    val (snackBarState, pagerState, onNext, onAction) = state
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarState) },
        containerColor = Colors.White
    ) { _ ->
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.testTag(TestConstants.WELCOME_SCREEN_HORIZONTAL_PAGER),
            key = { it }
        ) { page ->
            when (page) {
                0 -> FirstScreen(onGetStarted = onNext)
                1 -> HealthStateScreen(onNext = onNext)
                2 -> IntelligentScreen(onNext = onNext)
                3 -> ResourcesScreen(onNext = onNext)
                4 -> CommunityScreen {
                    onAction(UserPreferenceIntent.UpdateSkipWelcomeScreen())
                }
            }
        }
    }
}