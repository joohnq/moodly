package com.joohnq.moodapp.ui.presentation.onboarding.onboarding_mood_rate

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.ui.components.IconContinueButton
import com.joohnq.moodapp.ui.components.MoodFace
import com.joohnq.moodapp.ui.components.RouletteMoods
import com.joohnq.moodapp.ui.components.VerticalSpacer
import com.joohnq.moodapp.ui.presentation.onboarding.OnboardingBaseComponent
import com.joohnq.moodapp.ui.presentation.onboarding.onboarding_mood_rate.event.OnboardingMoodRateEvent
import com.joohnq.moodapp.ui.presentation.onboarding.onboarding_mood_rate.state.OnboardingMoodRateState
import com.joohnq.moodapp.ui.theme.Colors
import com.joohnq.moodapp.ui.theme.ComponentColors
import com.joohnq.moodapp.ui.theme.TextStyles
import com.joohnq.moodapp.viewmodel.OnboardingIntent
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.mood_rate_desc
import moodapp.composeapp.generated.resources.mood_rate_title
import org.jetbrains.compose.resources.stringResource

@Composable
fun OnboardingMoodRateUI(
    state: OnboardingMoodRateState,
) {
    OnboardingBaseComponent(
        page = 1,
        title = Res.string.mood_rate_title,
        isContinueButtonVisible = false,
        onGoBack = { state.onEvent(OnboardingMoodRateEvent.OnGoBack) },
    ) {
        Text(
            text = stringResource(
                Res.string.mood_rate_desc,
                stringResource(state.selectedMood.text)
            ),
            style = TextStyles.TextXlSemiBold(),
            color = Colors.Brown100Alpha64,
        )
        VerticalSpacer(24.dp)
        MoodFace(
            modifier = Modifier.size(120.dp),
            mood = state.selectedMood,
        )
        VerticalSpacer(24.dp)
    }
    Box(
        modifier = Modifier.fillMaxSize().padding(all = 16.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        IconContinueButton(
            modifier = Modifier.size(60.dp),
            colors = ComponentColors.IconButton.ContinueButtonColors(),
            onClick = { state.onEvent(OnboardingMoodRateEvent.OnNavigateToOnboardingProfessionalHelpScreen) }
        )
    }

    BoxWithConstraints(modifier = Modifier.testTag(OnboardingMoodRateScreen.OnboardingMoodRateTestTag.ROULETTE)) {
        val carouselOffset = maxHeight - (maxWidth / 2) + 60.dp

        Box(
            modifier = Modifier
                .size(maxWidth)
                .offset(y = carouselOffset),
            contentAlignment = Alignment.TopCenter
        ) {
            RouletteMoods(
                paddingBottom = state.moodRatePadding,
                setSelectedMood = { state.onAction(OnboardingIntent.UpdateMood(it)) })
        }
    }
}