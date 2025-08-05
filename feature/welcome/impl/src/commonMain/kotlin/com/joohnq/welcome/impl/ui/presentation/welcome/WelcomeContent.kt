package com.joohnq.welcome.impl.ui.presentation.welcome

import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.ai
import com.joohnq.shared_resources.community_first_title
import com.joohnq.shared_resources.community_span
import com.joohnq.shared_resources.components.layout.AppScaffoldLayout
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
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.shared_resources.resource_first_title
import com.joohnq.shared_resources.resource_second_title
import com.joohnq.shared_resources.resource_span
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.three
import com.joohnq.shared_resources.two
import com.joohnq.welcome.api.entity.Welcome
import com.joohnq.welcome.impl.ui.WelcomeBaseLayout
import com.joohnq.welcome.impl.ui.presentation.first.FirstScreen

@Composable
fun WelcomeContent(
    snackBarState: SnackbarHostState = rememberSnackBarState(),
    pagerState: PagerState,
    onEvent: (WelcomeContract.Event) -> Unit = {},
    onIntent: (WelcomeContract.Intent) -> Unit = {},
) {
    AppScaffoldLayout(
        snackBarHostState = snackBarState,
        containerColor = Colors.White
    ) { padding ->
        HorizontalPager(
            state = pagerState,
            key = { it }
        ) { page ->
            when (page) {
                0 -> FirstScreen(onNext = { onEvent(WelcomeContract.Event.OnNext) })
                1 ->
                    WelcomeBaseLayout(
                        welcome =
                            Welcome(
                                image = Drawables.Images.WelcomeHealthState,
                                step = Res.string.one,
                                index = 1,
                                backgroundColor = Colors.Green30,
                                firstTitle = Res.string.health_state_first_title,
                                secondTitle = Res.string.health_state_second_title,
                                span = Res.string.health_state_span,
                                spanColor = Colors.Green50
                            ),
                        paddingTop = padding.calculateTopPadding(),
                        onNext = { onEvent(WelcomeContract.Event.OnNext) }
                    )

                2 ->
                    WelcomeBaseLayout(
                        welcome =
                            Welcome(
                                image = Drawables.Images.WelcomeIntelligent,
                                step = Res.string.two,
                                index = 2,
                                backgroundColor = Colors.Orange20,
                                secondTitle = Res.string.intelligent_first_second_title,
                                span = Res.string.intelligent_first_span,
                                spanColor = Colors.Orange50
                            ),
                        paddingTop = padding.calculateTopPadding(),
                        onNext = { onEvent(WelcomeContract.Event.OnNext) }
                    )

                3 ->
                    WelcomeBaseLayout(
                        welcome =
                            Welcome(
                                image = Drawables.Images.WelcomeMental,
                                step = Res.string.three,
                                index = 3,
                                backgroundColor = Colors.Gray10,
                                firstTitle = Res.string.ai,
                                secondTitle = Res.string.journaling_ai_therapy_chatbot,
                                span = Res.string.mental,
                                spanColor = Colors.Gray60
                            ),
                        paddingTop = padding.calculateTopPadding(),
                        onNext = { onEvent(WelcomeContract.Event.OnNext) }
                    )

                4 ->
                    WelcomeBaseLayout(
                        welcome =
                            Welcome(
                                image = Drawables.Images.WelcomeResources,
                                step = Res.string.four,
                                index = 4,
                                backgroundColor = Colors.Yellow20,
                                firstTitle = Res.string.resource_first_title,
                                span = Res.string.resource_span,
                                secondTitle = Res.string.resource_second_title,
                                spanColor = Colors.Yellow60
                            ),
                        paddingTop = padding.calculateTopPadding(),
                        onNext = { onEvent(WelcomeContract.Event.OnNext) }
                    )

                5 ->
                    WelcomeBaseLayout(
                        welcome =
                            Welcome(
                                image = Drawables.Images.WelcomeCommunity,
                                step = Res.string.five,
                                index = 5,
                                backgroundColor = Colors.Purple30,
                                firstTitle = Res.string.community_first_title,
                                span = Res.string.community_span,
                                spanColor = Colors.Purple40
                            ),
                        paddingTop = padding.calculateTopPadding(),
                        onNext = { onIntent(WelcomeContract.Intent.UpdateSkipWelcome) }
                    )
            }
        }
    }
}
