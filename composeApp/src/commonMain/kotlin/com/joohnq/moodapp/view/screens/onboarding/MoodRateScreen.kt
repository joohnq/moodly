package com.joohnq.moodapp.view.screens.onboarding

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.joohnq.moodapp.constants.TestConstants
import com.joohnq.moodapp.entities.Mood
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.view.ScreenDimensions
import com.joohnq.moodapp.view.components.ButtonWithArrowRight
import com.joohnq.moodapp.view.components.MoodFace
import com.joohnq.moodapp.view.components.MoodRoulette
import com.joohnq.moodapp.view.components.TextStyles
import com.joohnq.moodapp.view.constants.Colors
import com.joohnq.moodapp.view.routes.onNavigateToProfessionalHelp
import com.joohnq.moodapp.viewmodel.OnboardingViewModel
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.mood_rate_desc
import moodapp.composeapp.generated.resources.mood_rate_title
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

@Composable
fun MoodRateScreenUi(
    snackBarState: SnackbarHostState = remember { SnackbarHostState() },
    moodRatePadding: Int,
    selectedMood: Mood,
    setSelectedMood: (Mood) -> Unit = {},
    onAction: () -> Unit = {},
    onGoBack: () -> Unit = {},
) {
    OnboardingBaseComponent(
        page = 1,
        snackBarState = snackBarState,
        title = Res.string.mood_rate_title,
        isContinueButtonVisible = false,
        onGoBack = onGoBack,
    ) {
        Text(
            stringResource(
                Res.string.mood_rate_desc,
                stringResource(selectedMood.text)
            ),
            style = TextStyles.OnboardingScreenMood()
        )
        Spacer(modifier = Modifier.height(24.dp))
        MoodFace(
            modifier = Modifier.size(120.dp),
            mood = selectedMood,
        )
        Spacer(modifier = Modifier.height(24.dp))
    }
    Box(
        modifier = Modifier.fillMaxSize().padding(all = 16.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        ButtonWithArrowRight(
            modifier = Modifier.size(60.dp).testTag(TestConstants.NEXT_BUTTON),
            colors = ButtonDefaults.buttonColors(
                containerColor = Colors.Brown80,
                contentColor = Colors.White
            ),
            onClick = onAction
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
            MoodRoulette(paddingBottom = moodRatePadding, setSelectedMood = setSelectedMood)
        }
    }
}

@Composable
fun MoodRateScreen(
    onboardingViewModel: OnboardingViewModel = sharedViewModel(),
    navigation: NavController
) {
    val screenDimensions: ScreenDimensions = koinInject()
    var selectedMood by rememberSaveable(stateSaver = Mood.getSaver()) { mutableStateOf(Mood.Neutral) }
    val snackBarState = remember { SnackbarHostState() }

    MoodRateScreenUi(
        snackBarState = snackBarState,
        moodRatePadding = screenDimensions.moodRatePadding,
        selectedMood = selectedMood,
        setSelectedMood = { selectedMood = it },
        onAction = {
            onboardingViewModel.updateMood(selectedMood)
            navigation.onNavigateToProfessionalHelp()
        },
        onGoBack = navigation::popBackStack
    )
}

@Preview
@Composable
fun MoodRateScreenPreview() {
    MoodRateScreenUi(
        moodRatePadding = 0,
        selectedMood = Mood.Neutral
    )
}
