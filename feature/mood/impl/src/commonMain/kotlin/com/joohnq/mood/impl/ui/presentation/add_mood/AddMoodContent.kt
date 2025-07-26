package com.joohnq.mood.impl.ui.presentation.add_mood

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.joohnq.mood.impl.ui.components.MoodFace
import com.joohnq.mood.impl.ui.components.MoodRadioGroup
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.add_mood
import com.joohnq.shared_resources.components.AppTopBar
import com.joohnq.shared_resources.components.button.TextAndCheckButton
import com.joohnq.shared_resources.components.spacer.VerticalSpacer
import com.joohnq.shared_resources.how_are_you_feeling_this_day
import com.joohnq.shared_resources.set_mood
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.stringResource

@Composable
fun AddMoodContent(
    state: AddMoodContract.State,
    onAction: (AddMoodContract.Intent) -> Unit = {},
    onEvent: (AddMoodContract.Event) -> Unit = {},
) {
    Scaffold(
        containerColor = Colors.Brown10,
        modifier = Modifier.fillMaxSize()
    ) { padding ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .background(color = state.record.mood.palette.moodScreenBackgroundColor)
                    .padding(padding)
                    .paddingHorizontalMedium(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppTopBar(
                isDark = false,
                text = Res.string.add_mood,
                onGoBack = { onEvent(AddMoodContract.Event.OnGoBack) }
            )
            VerticalSpacer(50.dp)
            Text(
                text = stringResource(Res.string.how_are_you_feeling_this_day),
                style = TextStyles.headingMdExtraBold(),
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
                style = TextStyles.text2xlSemiBold(),
                color = Colors.White,
                textAlign = TextAlign.Center
            )
            VerticalSpacer(80.dp)
            MoodRadioGroup(
                modifier = Modifier.widthIn(max = 500.dp),
                selectedMood = state.record,
                setSelectedMood = { resource ->
                    onAction(
                        AddMoodContract.Intent.UpdateAddingMoodRecordMood(resource)
                    )
                }
            )
            VerticalSpacer(40.dp)
            TextAndCheckButton(
                modifier = Modifier.fillMaxWidth(),
                text = Res.string.set_mood,
                onClick = { onEvent(AddMoodContract.Event.OnNavigateToExpressionAnalysis) }
            )
        }
    }
}
