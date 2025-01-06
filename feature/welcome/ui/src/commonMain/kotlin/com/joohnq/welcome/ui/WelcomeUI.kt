package com.joohnq.welcome.ui

import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.user.ui.viewmodel.user_preferences.UserPreferenceViewModelIntent
import com.joohnq.welcome.ui.screens.CommunityScreen
import com.joohnq.welcome.ui.screens.FirstScreen
import com.joohnq.welcome.ui.screens.HealthStateScreen
import com.joohnq.welcome.ui.screens.IntelligentScreen
import com.joohnq.welcome.ui.screens.ResourcesScreen
import com.joohnq.welcome.ui.state.WelcomeState

@Composable
fun WelcomeUI(
    state: WelcomeState,
) {
    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = state.snackBarState, modifier = Modifier.testTag(
                    WelcomeScreen.WelcomeTestTag.SNACK_BAR
                )
            )
        },
        containerColor = Colors.White
    ) { _ ->
        HorizontalPager(
            state = state.pagerState,
            modifier = Modifier.testTag(WelcomeScreen.WelcomeTestTag.HORIZONTAL_PAGER),
            key = { it }
        ) { page ->
            when (page) {
                0 -> FirstScreen(onGetStarted = state.onNext)
                1 -> HealthStateScreen(onNext = state.onNext)
                2 -> IntelligentScreen(onNext = state.onNext)
                3 -> ResourcesScreen(onNext = state.onNext)
                4 -> CommunityScreen {
                    state.onAction(UserPreferenceViewModelIntent.UpdateSkipWelcomeScreen())
                }
            }
        }
    }
}