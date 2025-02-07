package com.joohnq.mood.ui.presentation.add_mood

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import com.joohnq.mood.ui.components.AddMoodRadioGroup
import com.joohnq.mood.ui.components.MoodFace
import com.joohnq.mood.ui.mapper.getAllMoodResource
import com.joohnq.mood.ui.presentation.add_mood.event.AddMoodEvent
import com.joohnq.mood.ui.presentation.add_mood.viewmodel.AddMoodIntent
import com.joohnq.mood.ui.presentation.add_mood.viewmodel.AddMoodState
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.add_mood
import com.joohnq.shared_resources.components.ButtonTextAndCheck
import com.joohnq.shared_resources.components.TopBar
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.how_are_you_feeling_this_day
import com.joohnq.shared_resources.set_mood
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.stringResource

@Composable
fun AddMoodUI(
    state: AddMoodState,
    onAction: (AddMoodIntent) -> Unit,
    onEvent: (AddMoodEvent) -> Unit,
) {
    val moodsResources by remember { mutableStateOf(getAllMoodResource()) }
    val moodIndex = state.record.id

    Scaffold(
        containerColor = Colors.Brown10,
        modifier = Modifier.fillMaxSize()
    ) { padding ->
        Column(
            Modifier.fillMaxSize()
                .background(color = state.record.mood.palette.moodScreenBackgroundColor)
                .padding(padding)
                .paddingHorizontalMedium(),
        ) {
            TopBar(
                isDark = false,
                text = Res.string.add_mood,
                onGoBack = { onEvent(AddMoodEvent.OnGoBack) }
            )
            VerticalSpacer(50.dp)
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = stringResource(Res.string.how_are_you_feeling_this_day),
                    style = TextStyles.HeadingMdExtraBold(),
                    color = Colors.White,
                    textAlign = TextAlign.Center
                )
                VerticalSpacer(48.dp)
                MoodFace(
                    modifier = Modifier.size(160.dp),
                    resource = state.record.mood,
                    backgroundColor = state.record.mood.palette.moodScreenMoodFaceBackgroundColor,
                    color = state.record.mood.palette.moodScreenMoodFaceColor
                )
                VerticalSpacer(48.dp)
                Text(
                    text = stringResource(state.record.mood.text),
                    style = TextStyles.Text2xlSemiBold(),
                    color = Colors.White,
                    textAlign = TextAlign.Center
                )
                VerticalSpacer(40.dp)
                VerticalSpacer(40.dp)
                AddMoodRadioGroup(
                    moodsSize = moodsResources.size,
                    moodIndex = moodIndex,
                    selectedMood = state.record,
                    setSelectedMood = {
                        onAction(
                            AddMoodIntent.UpdateAddingStatsRecordMood(moodsResources[it])
                        )
                    }
                )
                VerticalSpacer(40.dp)
                ButtonTextAndCheck(
                    modifier = Modifier.fillMaxWidth(),
                    text = Res.string.set_mood,
                    onClick = { onEvent(AddMoodEvent.OnNavigateToExpressionAnalysis) }
                )
            }
        }
    }
}
