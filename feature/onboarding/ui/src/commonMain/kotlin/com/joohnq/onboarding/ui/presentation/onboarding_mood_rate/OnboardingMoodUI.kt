package com.joohnq.onboarding.ui.presentation.onboarding_mood_rate

import androidx.compose.desktop.ui.tooling.preview.Preview
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
import com.joohnq.mood.components.IconContinueButton
import com.joohnq.mood.components.VerticalSpacer
import com.joohnq.mood.domain.entity.Mood
import com.joohnq.mood.theme.Colors
import com.joohnq.mood.theme.ComponentColors
import com.joohnq.mood.theme.TextStyles
import com.joohnq.mood.ui.components.MoodFace
import com.joohnq.mood.ui.components.RouletteMoods
import com.joohnq.onboarding.ui.presentation.OnboardingBaseComponent
import com.joohnq.onboarding.ui.presentation.onboarding_mood_rate.event.OnboardingMoodRateEvent
import com.joohnq.onboarding.ui.presentation.onboarding_mood_rate.state.OnboardingMoodRateState
import com.joohnq.onboarding.ui.viewmodel.OnboardingViewModelIntent
import com.joohnq.shared.ui.Res
import com.joohnq.shared.ui.mood_rate_desc
import com.joohnq.shared.ui.mood_rate_title
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
                setSelectedMood = { state.onAction(OnboardingViewModelIntent.UpdateMood(it)) }
            )
        }
    }
}

@Preview
@Composable
fun Preview() {
    OnboardingMoodRateUI(
        OnboardingMoodRateState(
            selectedMood = Mood.Happy,
            onEvent = {},
            onAction = {},
        )
    )
}
