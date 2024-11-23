package com.joohnq.moodapp.ui.presentation.add_journaling_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.domain.Mood
import com.joohnq.moodapp.ui.components.ContinueButton
import com.joohnq.moodapp.ui.components.CustomOutlinedTextField
import com.joohnq.moodapp.ui.components.ExpressionAnalysisTextField
import com.joohnq.moodapp.ui.components.MediumTitle
import com.joohnq.moodapp.ui.components.MoodFace
import com.joohnq.moodapp.ui.components.TopBar
import com.joohnq.moodapp.ui.components.VerticalSpacer
import com.joohnq.moodapp.ui.presentation.add_journaling_screen.event.AddJournalingEvent
import com.joohnq.moodapp.ui.presentation.add_journaling_screen.state.AddJournalingState
import com.joohnq.moodapp.ui.theme.Colors
import com.joohnq.moodapp.ui.theme.ComponentColors
import com.joohnq.moodapp.ui.theme.Dimens
import com.joohnq.moodapp.ui.theme.Drawables
import com.joohnq.moodapp.ui.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.moodapp.viewmodel.HealthJournalIntent
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
    val (snackBarState, selectedMood, title, titleError, desc, onAction, onNavigation) = state

    val focusRequester = FocusRequester()
    var isFocused by remember { mutableStateOf(false) }
    val moods = remember { Mood.getAll() }
    Scaffold(
        containerColor = Colors.Brown10,
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackBarState) }
    ) { padding ->
        Column(
            Modifier.fillMaxSize()
                .padding(padding).paddingHorizontalMedium(),
        ) {
            TopBar(
                onGoBack = { onNavigation(AddJournalingEvent.OnGoBack) },
                text = Res.string.new_journal_entry
            )
            VerticalSpacer(20.dp)
            MediumTitle(Res.string.journal_title)
            VerticalSpacer(10.dp)
            CustomOutlinedTextField(
                text = title,
                placeholder = Res.string.enter_the_title,
                errorText = titleError,
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
                onValueChange = { onAction(HealthJournalIntent.UpdateAddingTitle(it)) },
            )
            VerticalSpacer(20.dp)
            MediumTitle(Res.string.mood)
            VerticalSpacer(10.dp)
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                items(moods) {
                    MoodFace(
                        modifier = Modifier.size(32.dp),
                        mood = it,
                        backgroundColor = if (selectedMood == it) it.palette.faceBackgroundColor else Colors.Gray30,
                        color = if (selectedMood == it) it.palette.faceColor else Colors.Gray60,
                        onClick = { onAction(HealthJournalIntent.UpdateAddingMood(it)) }
                    )
                }
            }
            VerticalSpacer(20.dp)
            MediumTitle(Res.string.write_your_entry)
            ExpressionAnalysisTextField(
                text = desc,
                onValueChange = { onAction(HealthJournalIntent.UpdateAddingDescription(it)) }
            )
            VerticalSpacer(48.dp)
            if (selectedMood != null)
                ContinueButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onAction(HealthJournalIntent.AddHealthJournal) }
                )
        }
    }
}