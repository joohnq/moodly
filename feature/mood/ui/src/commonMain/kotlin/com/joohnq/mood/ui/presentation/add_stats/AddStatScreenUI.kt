package com.joohnq.mood.ui.presentation.add_stats

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
import com.joohnq.domain.entity.Mood
import com.joohnq.mood.components.AddMoodRadioGroup
import com.joohnq.mood.components.ButtonTextAndCheck
import com.joohnq.mood.components.MoodFace
import com.joohnq.mood.components.TopBar
import com.joohnq.mood.components.VerticalSpacer
import com.joohnq.mood.theme.Colors
import com.joohnq.mood.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.mood.theme.TextStyles
import com.joohnq.mood.ui.presentation.add_stats.event.AddStatEvent
import com.joohnq.mood.ui.presentation.add_stats.state.AddStatState
import com.joohnq.shared.ui.Res
import com.joohnq.shared.ui.add_mood
import com.joohnq.shared.ui.hey_name
import com.joohnq.shared.ui.how_are_you_feeling_this_day
import com.joohnq.shared.ui.set_mood
import org.jetbrains.compose.resources.stringResource

@Composable
fun AddStatScreenUI(
    state: AddStatState,
) {
    val moods by remember { mutableStateOf(Mood.getAll()) }
    val moodIndex = moods.indexOf(state.selectedMood)

    Scaffold(
        containerColor = Colors.Brown10,
        modifier = Modifier.fillMaxSize()
    ) { padding ->
        Column(
            Modifier.fillMaxSize()
                .background(color = state.selectedMood.palette.moodScreenBackgroundColor)
                .padding(padding)
                .paddingHorizontalMedium(),
        ) {
            TopBar(
                isDark = false,
                text = Res.string.add_mood,
                onGoBack = { state.onEvent(AddStatEvent.OnGoBack) }
            )
            VerticalSpacer(50.dp)
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = stringResource(Res.string.hey_name, state.username),
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
                    mood = state.selectedMood,
                    backgroundColor = state.selectedMood.palette.moodScreenMoodFaceBackgroundColor,
                    color = state.selectedMood.palette.moodScreenMoodFaceColor
                )
                VerticalSpacer(48.dp)
                Text(
                    text = stringResource(state.selectedMood.text),
                    style = TextStyles.Text2xlSemiBold(),
                    color = Colors.White,
                    textAlign = TextAlign.Center
                )
                VerticalSpacer(40.dp)
                VerticalSpacer(40.dp)
                AddMoodRadioGroup(
                    moodsSize = moods.size,
                    moodIndex = moodIndex,
                    selectedMood = state.selectedMood,
                    setSelectedMood = {
                        state.onAddAction(
                            AddStatIntent.UpdateAddingStatsRecordMood(
                                moods[it]
                            )
                        )
                    }
                )
                VerticalSpacer(40.dp)
                ButtonTextAndCheck(
                    modifier = Modifier.fillMaxWidth(),
                    text = Res.string.set_mood,
                    onClick = { state.onEvent(AddStatEvent.OnNavigateToExpressionAnalysis) }
                )
            }
        }
    }
}
