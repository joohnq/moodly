package com.joohnq.moodapp.view.screens.add

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
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
import com.joohnq.moodapp.view.components.ButtonWithCheck
import com.joohnq.moodapp.view.components.MoodFace
import com.joohnq.moodapp.view.components.TextStyles
import com.joohnq.moodapp.view.components.TopBarLight
import com.joohnq.moodapp.view.constants.Colors
import com.joohnq.moodapp.view.routes.onNavigateToExpressionAnalysis
import com.joohnq.moodapp.view.state.UiState.Companion.getValue
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
        Modifier.background(color = selectedMood.palette.moodScreenBackgroundColor).fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 30.dp),
    ) {
        TopBarLight(onGoBack = onGoBack)
        Spacer(modifier = Modifier.height(50.dp))
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
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
                mood = selectedMood,
                backgroundColor = selectedMood.palette.moodScreenMoodFaceBackgroundColor,
                color = selectedMood.palette.moodScreenMoodFaceColor
            )
            Spacer(modifier = Modifier.height(48.dp))
            Text(
                text = stringResource(selectedMood.text),
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
                    val dividerCount = moods.size - 1

                    itemsIndexed(moods) { i, mood ->
                        Button(
                            modifier = Modifier.size(36.dp),
                            colors = ButtonColors(
                                containerColor = if (i <= moodIndex) Colors.White else selectedMood.palette.moodScreenInactiveColor,
                                contentColor = selectedMood.palette.moodScreenBackgroundColor,
                                disabledContainerColor = if (i <= moodIndex) Colors.White else selectedMood.palette.moodScreenInactiveColor,
                                disabledContentColor = selectedMood.palette.moodScreenBackgroundColor
                            ),
                            shape = CircleShape,
                            onClick = {
                                setSelectedMood(moods[i])
                            },
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Box(
                                modifier = Modifier.size(8.dp).background(
                                    color = selectedMood.palette.moodScreenBackgroundColor,
                                    shape = CircleShape
                                )
                            )
                        }

                        if (i < dividerCount) {
                            Box(
                                modifier = Modifier.width(dividerWidth).height(10.dp)
                                    .padding(horizontal = 4.dp)
                                    .background(color = if (moodIndex - 1 >= i) Colors.White else selectedMood.palette.moodScreenInactiveColor)
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
            ButtonWithCheck(
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
    val userState by userViewModel.userState.collectAsState()

    AddMoodScreenUi(
        userName = userState.user.getValue().name,
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

