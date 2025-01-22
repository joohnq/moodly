package com.joohnq.welcome.ui

import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
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
import com.joohnq.user.ui.viewmodel.user_preferences.UserPreferenceIntent

@Composable
fun WelcomeUI(
    snackBarState: SnackbarHostState,
    pagerState: PagerState,
    onNext: () -> Unit,
    onAction: (UserPreferenceIntent) -> Unit,
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
                    welcome = Welcome(
                        image = Drawables.Images.WelcomeHealthStateImage,
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
                    welcome = Welcome(
                        image = Drawables.Images.WelcomeIntelligentImage,
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
                    welcome = Welcome(
                        image = Drawables.Images.WelcomeMentalImage,
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
                    welcome = Welcome(
                        image = Drawables.Images.WelcomeResourcesImage,
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
                    welcome = Welcome(
                        image = Drawables.Images.WelcomeCommunityImage,
                        step = Res.string.five,
                        index = 5,
                        backgroundColor = Colors.Purple30,
                        firstTitle = Res.string.community_first_title,
                        span = Res.string.community_span,
                        spanColor = Colors.Purple40,
                    ),
                    paddingTop = padding.calculateTopPadding(),
                    onNext = { onAction(UserPreferenceIntent.UpdateSkipWelcome()) }
                )
            }
        }
    }
}