package com.joohnq.moodapp.view.screens.onboarding

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.joohnq.moodapp.constants.TestConstants
import com.joohnq.moodapp.entities.Mood
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.view.NextAndBackAction
import com.joohnq.moodapp.view.ScreenDimensions
import com.joohnq.moodapp.view.components.IconContinueButton
import com.joohnq.moodapp.view.components.MoodFace
import com.joohnq.moodapp.view.components.RouletteMoods
import com.joohnq.moodapp.view.components.VerticalSpacer
import com.joohnq.moodapp.view.routes.onNavigateToProfessionalHelp
import com.joohnq.moodapp.view.ui.Colors
import com.joohnq.moodapp.view.ui.ComponentColors
import com.joohnq.moodapp.view.ui.TextStyles
import com.joohnq.moodapp.viewmodel.OnboardingIntent
import com.joohnq.moodapp.viewmodel.OnboardingViewModel
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.mood_rate_desc
import moodapp.composeapp.generated.resources.mood_rate_title
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

@Composable
fun OnboardingMoodRateScreenUi(
    moodRatePadding: Int,
    selectedMood: Mood,
    onNavigation: (NextAndBackAction) -> Unit = {},
    onAction: (OnboardingIntent) -> Unit = {}
) {
    OnboardingBaseComponent(
        page = 1,
        title = Res.string.mood_rate_title,
        isContinueButtonVisible = false,
        onAction = onNavigation
    ) {
        Text(
            text = stringResource(
                Res.string.mood_rate_desc,
                stringResource(selectedMood.text)
            ),
            style = TextStyles.TextXlSemiBold(),
            color = Colors.Brown100Alpha64,
        )
        VerticalSpacer(24.dp)
        MoodFace(
            modifier = Modifier.size(120.dp),
            mood = selectedMood,
        )
        VerticalSpacer(24.dp)
    }
    Box(
        modifier = Modifier.fillMaxSize().padding(all = 16.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        IconContinueButton(
            modifier = Modifier.size(60.dp).testTag(TestConstants.NEXT_BUTTON),
            colors = ComponentColors.IconButton.ContinueButtonColors(),
            onClick = { onNavigation(NextAndBackAction.OnContinue) }
        )
    }

    BoxWithConstraints(modifier = Modifier.testTag(TestConstants.ONBOARDING_ROULETTE)) {
        val carouselOffset = maxHeight - (maxWidth / 2) + 60.dp

        Box(
            modifier = Modifier
                .size(maxWidth)
                .offset(y = carouselOffset),
            contentAlignment = Alignment.TopCenter
        ) {
            RouletteMoods(
                paddingBottom = moodRatePadding,
                setSelectedMood = { onAction(OnboardingIntent.UpdateMood(it)) })
        }
    }
}

@Composable
fun OnboardingMoodRateScreen(
    onboardingViewModel: OnboardingViewModel = sharedViewModel(),
    navigation: NavController
) {
    val screenDimensions: ScreenDimensions = koinInject()
    val onboardingState by onboardingViewModel.onboardingState.collectAsState()

    OnboardingMoodRateScreenUi(
        moodRatePadding = screenDimensions.moodRatePadding,
        selectedMood = onboardingState.statsRecord.mood,
        onAction = onboardingViewModel::onAction,
        onNavigation = { action ->
            when (action) {
                NextAndBackAction.OnContinue -> navigation.onNavigateToProfessionalHelp()
                NextAndBackAction.OnGoBack -> navigation.popBackStack()
            }
        }
    )
}

@Preview
@Composable
fun OnboardingMoodRateScreenPreview() {
    OnboardingMoodRateScreenUi(
        moodRatePadding = 0,
        selectedMood = Mood.Neutral
    )
}
