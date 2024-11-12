package com.joohnq.moodapp.view.screens.add

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.joohnq.moodapp.entities.Mood
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.view.components.AddMoodRadioGroup
import com.joohnq.moodapp.view.components.ButtonTextAndCheck
import com.joohnq.moodapp.view.components.CurvedConnectedDotsRow
import com.joohnq.moodapp.view.components.MoodFace
import com.joohnq.moodapp.view.components.TopBar
import com.joohnq.moodapp.view.components.VerticalSpacer
import com.joohnq.moodapp.view.routes.onNavigateToExpressionAnalysis
import com.joohnq.moodapp.view.state.UiState.Companion.getValue
import com.joohnq.moodapp.view.ui.Colors
import com.joohnq.moodapp.view.ui.TextStyles
import com.joohnq.moodapp.viewmodel.AddMoodViewModel
import com.joohnq.moodapp.viewmodel.UserViewModel
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.hey_name
import moodapp.composeapp.generated.resources.how_are_you_feeling_this_day
import moodapp.composeapp.generated.resources.set_mood
import org.jetbrains.compose.resources.stringResource

@Composable
fun AddMoodScreenUi(
    userName: String,
    selectedMood: Mood,
    setSelectedMood: (Mood) -> Unit = {},
    onGoBack: () -> Unit = {},
    onContinue: () -> Unit = {}
) {
    val moods by remember { mutableStateOf(Mood.getAll()) }
    val moodIndex = moods.indexOf(selectedMood)

    Column(
        Modifier.fillMaxSize().background(color = selectedMood.palette.moodScreenBackgroundColor)
            .padding(horizontal = 20.dp, vertical = 30.dp),
    ) {
        TopBar(isDark = false, onGoBack = onGoBack)
        VerticalSpacer(50.dp)
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(Res.string.hey_name, userName),
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
            CurvedConnectedDotsRow(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 40.dp),
                dotColor = selectedMood.palette.moodScreenInactiveColor,
                lineColor = selectedMood.palette.moodScreenTraceColor,
                backgroundColor = selectedMood.palette.moodScreenBackgroundColor,
                numberOfDots = 5,
                dotSize = 36.dp,
                curveHeight = 60.dp,
                onDotClick = {
                    setSelectedMood(moods[it])
                }
            )
            VerticalSpacer(40.dp)
            AddMoodRadioGroup(
                moodsSize = moods.size,
                moodIndex = moodIndex,
                selectedMood = selectedMood,
                setSelectedMood = { setSelectedMood(moods[it]) }
            )
            VerticalSpacer(40.dp)
            ButtonTextAndCheck(
                modifier = Modifier.fillMaxWidth(),
                text = Res.string.set_mood,
                onClick = onContinue
            )
        }
    }
}

@Composable
fun AddMoodScreen(
    userViewModel: UserViewModel = sharedViewModel(),
    addMoodViewModel: AddMoodViewModel = sharedViewModel(),
    navigation: NavHostController
) {
    val addMoodState by addMoodViewModel.addMoodState.collectAsState()
    val user by userViewModel.user.collectAsState()

    AddMoodScreenUi(
        userName = user.getValue().name,
        selectedMood = addMoodState.statsRecord.mood,
        setSelectedMood = addMoodViewModel::updateStatsRecordMood,
        onGoBack = navigation::popBackStack,
        onContinue = navigation::onNavigateToExpressionAnalysis
    )
}

@Preview
@Composable
fun AddMoodScreenPreview() {
    AddMoodScreenUi(
        userName = "Henrique",
        selectedMood = Mood.Neutral,
    )
}

