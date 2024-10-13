package com.joohnq.moodapp.view.onboarding

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
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
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.joohnq.moodapp.Colors
import com.joohnq.moodapp.view.components.ButtonWithArrowRight
import com.joohnq.moodapp.view.components.TextStyles
import com.joohnq.moodapp.view.components.MoodFace
import com.joohnq.moodapp.view.components.MoodRoulette
import com.joohnq.moodapp.view.entities.Mood
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.mood_rate_desc
import moodapp.composeapp.generated.resources.mood_rate_title
import org.jetbrains.compose.resources.stringResource

class MoodRateScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

//        SideEffect {
//            navigator.push(ExpressionAnalysisScreen())
//        }

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

        OnboardingBaseComponent(
            page = 1,
            title = Res.string.mood_rate_title,
            isContinueButtonVisible = false,
            onContinue = { },
        ) {
            Text(
                stringResource(Res.string.mood_rate_desc, stringResource(mood.text)),
                style = TextStyles.OnboardingScreenMood()
            )
            Spacer(modifier = Modifier.height(24.dp))
            MoodFace(
                modifier = Modifier.size(120.dp),
                mood = mood,
            )
            Spacer(modifier = Modifier.height(24.dp))
        }

        Box(modifier = Modifier.fillMaxSize().padding(all = 16.dp), contentAlignment = Alignment.CenterEnd) {
            ButtonWithArrowRight(
                modifier = Modifier.size(60.dp), colors = ButtonDefaults.buttonColors(
                    containerColor = Colors.Brown80,
                    contentColor = Colors.White
                ), onClick = { navigator.push(ProfessionalHelpScreen()) }
            )
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
}
