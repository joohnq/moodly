package com.joohnq.health_journal.ui.presentation.add_journaling_screen

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.unit.dp
import com.joohnq.mood.domain.Mood
import com.joohnq.mood.components.ContinueButton
import com.joohnq.mood.ui.components.CustomOutlinedTextField
import com.joohnq.mood.ui.components.ExpressionAnalysisTextField
import com.joohnq.mood.ui.components.MediumTitle
import com.joohnq.mood.ui.components.MoodFace
import com.joohnq.mood.ui.components.TopBar
import com.joohnq.mood.components.VerticalSpacer
import com.joohnq.health_journal.ui.presentation.add_journaling_screen.event.AddJournalingEvent
import com.joohnq.health_journal.ui.presentation.add_journaling_screen.state.AddJournalingState
import com.joohnq.mood.ui.theme.Colors
import com.joohnq.mood.ui.theme.ComponentColors
import com.joohnq.mood.ui.theme.Dimens
import com.joohnq.mood.ui.theme.Drawables
import com.joohnq.mood.ui.theme.PaddingModifier.Companion.paddingHorizontalMedium
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.enter_the_title
import moodapp.composeapp.generated.resources.journal_title
import moodapp.composeapp.generated.resources.mood
import moodapp.composeapp.generated.resources.new_journal_entry
import moodapp.composeapp.generated.resources.write_your_entry
import org.jetbrains.compose.resources.painterResource

@Composable
fun AddJournalingUI(
    state: AddJournalingState,
) {
    val canContinue by derivedStateOf {
        state.title.isNotEmpty() && state.selectedMood != null && state.desc.isNotEmpty()
    }
    val focusRequester = FocusRequester()
    var isFocused by remember { mutableStateOf(false) }
    val moods = remember { Mood.getAll() }
    Scaffold(
        containerColor = Colors.Brown10,
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(state.snackBarState) }
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).verticalScroll(rememberScrollState())
        ) {
            Column(
                Modifier.fillMaxSize()
                    .paddingHorizontalMedium(),
            ) {
                TopBar(
                    onGoBack = { state.onEvent(AddJournalingEvent.OnGoBack) },
                    text = Res.string.new_journal_entry
                )
                MediumTitle(Res.string.journal_title)
                CustomOutlinedTextField(
                    text = state.title,
                    placeholder = Res.string.enter_the_title,
                    errorText = state.titleError,
                    colors = ComponentColors.TextField.MainTextFieldColors(),
                    leadingIcon = {
                        Icon(
                            painter = painterResource(Drawables.Icons.Document),
                            contentDescription = null,
                            tint = Colors.Brown80,
                            modifier = Modifier.size(Dimens.Icon)
                        )
                    },
                    trailingIcon = {
                        Icon(
                            painter = painterResource(Drawables.Icons.Edit),
                            contentDescription = null,
                            tint = Colors.Brown80,
                            modifier = Modifier.size(Dimens.Icon)
                        )
                    },
                    modifier = Modifier.fillMaxWidth().height(56.dp)
                        .onFocusChanged { focusState ->
                            isFocused = focusState.isFocused
                        }.focusRequester(focusRequester),
                    onValueChange = { state.onAddingAction(AddingJournalingIntent.UpdateTitle(it)) },
                )
                MediumTitle(Res.string.mood)
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    items(moods) {
                        MoodFace(
                            modifier = Modifier.size(32.dp),
                            mood = it,
                            backgroundColor = if (state.selectedMood == it) it.palette.faceBackgroundColor else Colors.Gray30,
                            color = if (state.selectedMood == it) it.palette.faceColor else Colors.Gray60,
                            onClick = { state.onAddingAction(AddingJournalingIntent.UpdateMood(it)) }
                        )
                    }
                }
                MediumTitle(Res.string.write_your_entry)
                ExpressionAnalysisTextField(
                    text = state.desc,
                    onValueChange = {
                        state.onAddingAction(
                            AddingJournalingIntent.UpdateDescription(it)
                        )
                    }
                )
            }
            Column(
                Modifier.fillMaxSize().paddingHorizontalMedium(),
            ) {
                VerticalSpacer(24.dp)
                ContinueButton(
                    modifier = Modifier.fillMaxWidth(),
                    enabled = canContinue,
                    onClick = { state.onEvent(AddJournalingEvent.OnAdd) }
                )
            }
        }
    }
}

@Preview
@Composable
fun Preview() {
    AddJournalingUI(
        AddJournalingState(
            snackBarState = remember { SnackbarHostState() },
            selectedMood = Mood.Happy,
            title = "title",
            titleError = null,
            desc = "desc",
            onAddingAction = {},
            onEvent = {}
        )
    )
}

@Preview
@Composable
fun Preview2() {
    AddJournalingUI(
        AddJournalingState(
            snackBarState = remember { SnackbarHostState() },
            selectedMood = null,
            title = "",
            titleError = null,
            desc = "",
            onAddingAction = {},
            onEvent = {}
        )
    )
}

