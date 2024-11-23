package com.joohnq.moodapp.ui.presentation.add_stats

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.domain.Mood
import com.joohnq.moodapp.ui.components.AddMoodRadioGroup
import com.joohnq.moodapp.ui.components.ButtonTextAndCheck
import com.joohnq.moodapp.ui.components.MoodFace
import com.joohnq.moodapp.ui.components.TopBar
import com.joohnq.moodapp.ui.components.VerticalSpacer
import com.joohnq.moodapp.ui.presentation.add_stats.event.AddStatEvent
import com.joohnq.moodapp.ui.presentation.add_stats.state.AddStatState
import com.joohnq.moodapp.ui.theme.Colors
import com.joohnq.moodapp.ui.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.moodapp.ui.theme.TextStyles
import com.joohnq.moodapp.viewmodel.StatsIntent
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.add_mood
import moodapp.composeapp.generated.resources.hey_name
import moodapp.composeapp.generated.resources.how_are_you_feeling_this_day
import moodapp.composeapp.generated.resources.set_mood
import org.jetbrains.compose.resources.stringResource

@Composable
fun AddStatScreenUI(
    state: AddStatState,
) {
    val (username, selectedMood, onAction, onNavigation) = state
    val moods by remember { mutableStateOf(Mood.getAll()) }
    val moodIndex = moods.indexOf(selectedMood)

    Scaffold(
        containerColor = Colors.Brown10,
        modifier = Modifier.fillMaxSize()
    ) { padding ->
        Column(
            Modifier.fillMaxSize()
                .background(color = selectedMood.palette.moodScreenBackgroundColor)
                .padding(padding)
                .paddingHorizontalMedium(),
        ) {
            TopBar(
                isDark = false,
                text = Res.string.add_mood,
                onGoBack = { onNavigation(AddStatEvent.OnGoBack) }
            )
            VerticalSpacer(50.dp)
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = stringResource(Res.string.hey_name, username),
                    style = TextStyles.TextXlSemiBold(),
                    color = Colors.White,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = stringResource(Res.string.how_are_you_feeling_this_day),
                    style = TextStyles.HeadingMdExtraBold(),
                    color = Colors.White,
                    textAlign = TextAlign.Center
                )
                VerticalSpacer(48.dp)
                MoodFace(
                    modifier = Modifier.size(160.dp),
                    mood = selectedMood,
                    backgroundColor = selectedMood.palette.moodScreenMoodFaceBackgroundColor,
                    color = selectedMood.palette.moodScreenMoodFaceColor
                )
                VerticalSpacer(48.dp)
                Text(
                    text = stringResource(selectedMood.text),
                    style = TextStyles.Text2xlSemiBold(),
                    color = Colors.White,
                    textAlign = TextAlign.Center
                )
                VerticalSpacer(40.dp)
                VerticalSpacer(40.dp)
                AddMoodRadioGroup(
                    moodsSize = moods.size,
                    moodIndex = moodIndex,
                    selectedMood = selectedMood,
                    setSelectedMood = { onAction(StatsIntent.UpdateAddingStatsRecordMood(moods[it])) }
                )
                VerticalSpacer(40.dp)
                ButtonTextAndCheck(
                    modifier = Modifier.fillMaxWidth(),
                    text = Res.string.set_mood,
                    onClick = { onNavigation(AddStatEvent.OnNavigateToExpressionAnalysis) }
                )
            }
        }
    }
}
