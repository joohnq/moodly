package com.joohnq.moodapp.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.Colors
import com.joohnq.moodapp.components.ButtonWithArrowOpen
import com.joohnq.moodapp.components.CustomTextStyle
import com.joohnq.moodapp.components.MoodRoulette
import com.joohnq.moodapp.components.MoodFace
import com.joohnq.moodapp.components.TextWithBackground
import com.joohnq.moodapp.entities.Mood
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun Int.toDp(): Dp =
    with(LocalDensity.current) { this@toDp.toDp() }

@Composable
fun Double.toDp(): Dp =
    this@toDp.toInt().toDp()

@Composable
@Preview
fun OnboardingMoodRateScreen() {
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
    val (mood, setMood) = remember { mutableStateOf<Mood>(Mood.Neutral) }
    val (selectedMood, setSelectedMood) = remember { mutableStateOf(2) }

    LaunchedEffect(selectedMood) {
        setMood(moods[selectedMood])
    }

    Scaffold(modifier = Modifier.fillMaxSize(), containerColor = Colors.Brown10) { padding ->
        Column(
            modifier = Modifier.padding(padding).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp, horizontal = 16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    ButtonWithArrowOpen {}
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        "Assessments",
                        style = CustomTextStyle.TextStyleOnboardingScreenSession()
                    )
                }
                TextWithBackground(
                    "1 of 7",
                    borderColor = Colors.Transparent,
                    backgroundColor = Colors.Brown20,
                    textColor = Colors.Brown60
                )
            }
            Spacer(modifier = Modifier.height(48.dp))
            Text(
                "How would you describe your mood?",
                style = CustomTextStyle.TextStyleOnboardingScreenTitle()
            )
            Spacer(modifier = Modifier.height(48.dp))
            Text(
                "I Feel ${mood.text}",
                style = CustomTextStyle.TextStyleOnboardingScreenMood()
            )
            Spacer(modifier = Modifier.height(24.dp))
            MoodFace(mood)
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
    BoxWithConstraints {
        val screenWidth = maxWidth
        val screenHeight = maxHeight
        val carouselOffset = screenHeight - (screenWidth / 2)

        Box(
            modifier = Modifier
                .size(screenWidth)
                .offset(y = carouselOffset),
            contentAlignment = Alignment.TopCenter
        ) {
            MoodRoulette(moods, setSelectedMood = setSelectedMood)
        }
    }
}
