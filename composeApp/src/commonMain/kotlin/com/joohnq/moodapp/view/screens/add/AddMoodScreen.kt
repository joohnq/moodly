package com.joohnq.moodapp.view.screens.add

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.entities.Mood
import com.joohnq.moodapp.entities.palette.MoodPalette
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.view.components.ButtonWithCheck
import com.joohnq.moodapp.view.components.MoodFace
import com.joohnq.moodapp.view.components.TextStyles
import com.joohnq.moodapp.view.constants.Colors
import com.joohnq.moodapp.view.state.UiState.Companion.getValue
import com.joohnq.moodapp.viewmodel.UserViewModel
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.hey_name
import moodapp.composeapp.generated.resources.how_are_you_feeling_this_day
import moodapp.composeapp.generated.resources.set_mood
import org.jetbrains.compose.resources.stringResource

@Composable
fun AddMoodScreenUi(
    padding: PaddingValues = PaddingValues(0.dp),
    userName: String,
    moods: List<Mood>,
    selectedMood: Int,
    moodPalette: MoodPalette,
    onSelectedMood: (Int) -> Unit = {}
) {
    Column(
        modifier = Modifier.background(color = moodPalette.moodScreenBackgroundColor).fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(Res.string.hey_name, userName),
            style = TextStyles.SemiBoldXL(),
            color = Colors.White,
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(Res.string.how_are_you_feeling_this_day),
            style = TextStyles.ExtraBoldMd(),
            color = Colors.White,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(48.dp))
        MoodFace(
            modifier = Modifier.size(160.dp),
            mood = moods[selectedMood],
            backgroundColor = moodPalette.moodScreenMoodFaceBackgroundColor,
            color = moodPalette.moodScreenMoodFaceColor
        )
        Spacer(modifier = Modifier.height(48.dp))
        Text(
            text = stringResource(moods[selectedMood].text),
            style = TextStyles.SemiBold2XL(),
            color = Colors.White,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(40.dp))
        BoxWithConstraints {
            val dividerWidth = (maxWidth - 180.dp - 40.dp) / 4
            LazyRow(
                modifier = Modifier.wrapContentSize(Alignment.Center).fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                val size = moods.size
                val dividerCount = size - 1

                items(size) { i ->
                    Box(
                        modifier = Modifier.size(36.dp).background(
                            color = if (i <= selectedMood) Colors.White else moodPalette.moodScreenInactiveColor,
                            shape = CircleShape
                        ).clickable { onSelectedMood(i) },
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier.size(8.dp).background(
                                color = moodPalette.moodScreenBackgroundColor,
                                shape = CircleShape
                            )
                        )
                    }

                    if (i < dividerCount) {
                        Box(
                            modifier = Modifier.width(dividerWidth).height(10.dp)
                                .padding(horizontal = 4.dp)
                                .background(color = if (selectedMood - 1 >= i) Colors.White else moodPalette.moodScreenInactiveColor)
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(40.dp))
        ButtonWithCheck(
            modifier = Modifier.fillMaxWidth(),
            text = Res.string.set_mood,
            onClick = { onSelectedMood(0) })
    }
}


@Composable
fun AddMoodScreen(
    padding: PaddingValues = PaddingValues(0.dp),
    userViewModel: UserViewModel = sharedViewModel()
) {
    val moods by remember { mutableStateOf(Mood.getAll()) }
    var selectedMood by remember { mutableStateOf(0) }
    val user by userViewModel.user.collectAsState()
    val moodPalette by remember { mutableStateOf(Mood.getPalette(moods[selectedMood])) }

    AddMoodScreenUi(
        padding = padding,
        userName = user.getValue().name,
        moods = moods,
        selectedMood = selectedMood,
        moodPalette = moodPalette,
        onSelectedMood = { selectedMood = it }
    )
}

@Preview
@Composable
fun AddMoodScreenPreview() {
    AddMoodScreenUi(
        userName = "Henrique",
        moods = Mood.getAll(),
        selectedMood = 3,
        moodPalette = Mood.getPalette(Mood.Overjoyed)
    )
}

