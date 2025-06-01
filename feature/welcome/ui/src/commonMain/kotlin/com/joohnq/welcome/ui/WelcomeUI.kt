package com.joohnq.welcome.ui

import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import com.joohnq.preferences.ui.viewmodel.PreferencesContract
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.ai
import com.joohnq.shared_resources.community_first_title
import com.joohnq.shared_resources.community_span
import com.joohnq.shared_resources.components.ScaffoldSnackBar
import com.joohnq.shared_resources.five
import com.joohnq.shared_resources.four
import com.joohnq.shared_resources.health_state_first_title
import com.joohnq.shared_resources.health_state_second_title
import com.joohnq.shared_resources.health_state_span
import com.joohnq.shared_resources.intelligent_first_second_title
import com.joohnq.shared_resources.intelligent_first_span
import com.joohnq.shared_resources.journaling_ai_therapy_chatbot
import com.joohnq.shared_resources.mental
import com.joohnq.shared_resources.one
import com.joohnq.shared_resources.resource_first_title
import com.joohnq.shared_resources.resource_second_title
import com.joohnq.shared_resources.resource_span
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.three
import com.joohnq.shared_resources.two
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun WelcomeUI(
    snackBarState: SnackbarHostState,
    pagerState: PagerState,
    onNext: () -> Unit,
    onIntent: (PreferencesContract.Intent) -> Unit,
) {
    ScaffoldSnackBar(
        snackBarHostState = snackBarState,
        containerColor = Colors.White
    ) { padding ->
        HorizontalPager(
            state = pagerState,
            key = { it }
        ) { page ->
            when (page) {
                0 -> FirstScreen(onNext = onNext)
                1 -> WelcomeBase(
                    state = WelcomeContract.State(
                        image = Drawables.Images.WelcomeHealthState,
                        step = Res.string.one,
                        index = 1,
                        backgroundColor = Colors.Green30,
                        firstTitle = Res.string.health_state_first_title,
                        secondTitle = Res.string.health_state_second_title,
                        span = Res.string.health_state_span,
                        spanColor = Colors.Green50,
                    ),
                    paddingTop = padding.calculateTopPadding(),
                    onNext = onNext
                )


                2 -> WelcomeBase(
                    state = WelcomeContract.State(
                        image = Drawables.Images.WelcomeIntelligent,
                        step = Res.string.two,
                        index = 2,
                        backgroundColor = Colors.Orange20,
                        secondTitle = Res.string.intelligent_first_second_title,
                        span = Res.string.intelligent_first_span,
                        spanColor = Colors.Orange50,
                    ),
                    paddingTop = padding.calculateTopPadding(),
                    onNext = onNext
                )

                3 -> WelcomeBase(
                    state = WelcomeContract.State(
                        image = Drawables.Images.WelcomeMental,
                        step = Res.string.three,
                        index = 3,
                        backgroundColor = Colors.Gray10,
                        firstTitle = Res.string.ai,
                        secondTitle = Res.string.journaling_ai_therapy_chatbot,
                        span = Res.string.mental,
                        spanColor = Colors.Gray60,
                    ),
                    paddingTop = padding.calculateTopPadding(),
                    onNext = onNext
                )

                4 -> WelcomeBase(
                    state = WelcomeContract.State(
                        image = Drawables.Images.WelcomeResources,
                        step = Res.string.four,
                        index = 4,
                        backgroundColor = Colors.Yellow20,
                        firstTitle = Res.string.resource_first_title,
                        span = Res.string.resource_span,
                        secondTitle = Res.string.resource_second_title,
                        spanColor = Colors.Yellow60,
                    ),
                    paddingTop = padding.calculateTopPadding(),
                    onNext = onNext
                )

                5 -> WelcomeBase(
                    state = WelcomeContract.State(
                        image = Drawables.Images.WelcomeCommunity,
                        step = Res.string.five,
                        index = 5,
                        backgroundColor = Colors.Purple30,
                        firstTitle = Res.string.community_first_title,
                        span = Res.string.community_span,
                        spanColor = Colors.Purple40,
                    ),
                    paddingTop = padding.calculateTopPadding(),
                    onNext = { onIntent(PreferencesContract.Intent.UpdateSkipWelcome()) }
                )
            }
        }
    }
}

@Preview
@Composable
fun WelcomeUIPreviewGetStarted() {
    WelcomeUI(
        snackBarState = SnackbarHostState(),
        pagerState = rememberPagerState(0) { 6 },
        onNext = {},
        onIntent = {},
    )
}

@Preview
@Composable
fun WelcomeUIPreviewOne() {
    WelcomeUI(
        snackBarState = SnackbarHostState(),
        pagerState = rememberPagerState(1) { 6 },
        onNext = {},
        onIntent = {},
    )
}

@Preview
@Composable
fun WelcomeUIPreviewTwo() {
    WelcomeUI(
        snackBarState = SnackbarHostState(),
        pagerState = rememberPagerState(2) { 6 },
        onNext = {},
        onIntent = {},
    )
}

@Preview
@Composable
fun WelcomeUIPreviewThree() {
    WelcomeUI(
        snackBarState = SnackbarHostState(),
        pagerState = rememberPagerState(3) { 6 },
        onNext = {},
        onIntent = {},
    )
}

@Preview
@Composable
fun WelcomeUIPreviewFour() {
    WelcomeUI(
        snackBarState = SnackbarHostState(),
        pagerState = rememberPagerState(4) { 6 },
        onNext = {},
        onIntent = {},
    )
}

@Preview
@Composable
fun WelcomeUIPreviewFive() {
    WelcomeUI(
        snackBarState = SnackbarHostState(),
        pagerState = rememberPagerState(5) { 6 },
        onNext = {},
        onIntent = {},
    )
}

@Preview
@Composable
fun WelcomeUIPreviewSix() {
    WelcomeUI(
        snackBarState = SnackbarHostState(),
        pagerState = rememberPagerState(6) { 6 },
        onNext = {},
        onIntent = {},
    )
}

