package com.joohnq.moodapp.view.onboarding

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.joohnq.moodapp.Colors
import com.joohnq.moodapp.view.components.ButtonWithArrowRight
import com.joohnq.moodapp.view.components.CustomTextStyle
import com.joohnq.moodapp.view.components.MoodFace
import com.joohnq.moodapp.view.components.MoodRoulette
import com.joohnq.moodapp.view.entities.Mood
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.mood_rate_desc
import moodapp.composeapp.generated.resources.mood_rate_title
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun OnboardingMoodRateScreen() {
    val navigator = LocalNavigator.currentOrThrow

    val moods =
        remember {
            listOf(
                Mood.Neutral,
                Mood.Happy,
                Mood.Overjoyed,
                Mood.Depressed,
                Mood.Sad,
                Mood.Neutral,
                Mood.Happy,
                Mood.Overjoyed,
                Mood.Depressed,
                Mood.Sad,
            )
        }
    var mood by remember { mutableStateOf<Mood>(Mood.Neutral) }
    var selectedMood by remember { mutableStateOf(0) }

    LaunchedEffect(selectedMood) {
        mood = moods[selectedMood]
    }

    Scaffold(modifier = Modifier.fillMaxSize(), containerColor = Colors.Brown10) { padding ->
        Column(
            modifier = Modifier.padding(padding).fillMaxSize().padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OnboardingTopBar(1)
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                stringResource(Res.string.mood_rate_title),
                style = CustomTextStyle.TextStyleOnboardingScreenTitle()
            )
            Spacer(modifier = Modifier.height(48.dp))
            Text(
                stringResource(Res.string.mood_rate_desc, stringResource(mood.text)),
                style = CustomTextStyle.TextStyleOnboardingScreenMood()
            )
            Spacer(modifier = Modifier.height(24.dp))
            MoodFace(
                modifier = Modifier.size(120.dp),
                mood = mood,
            )
            Spacer(modifier = Modifier.height(24.dp))
            ButtonWithArrowRight(
                text = "Next",
                onClick = { navigator.push(OnboardingSoughtProfessionalHelp()) })
        }
    }
    BoxWithConstraints {
        val screenWidth = maxWidth
        val screenHeight = maxHeight
        val carouselOffset = screenHeight - (screenWidth / 2) + 60.dp

        Box(
            modifier = Modifier
                .size(screenWidth)
                .offset(y = carouselOffset),
            contentAlignment = Alignment.TopCenter
        ) {
            MoodRoulette(
                moods = moods,
                selectedMood = selectedMood,
                setSelectedMood = { selectedMood = it }
            )
        }
    }
}
