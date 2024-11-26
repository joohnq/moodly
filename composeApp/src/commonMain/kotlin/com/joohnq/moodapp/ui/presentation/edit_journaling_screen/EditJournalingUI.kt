package com.joohnq.moodapp.ui.presentation.edit_journaling_screen

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.domain.HealthJournalRecord
import com.joohnq.moodapp.ui.components.MainAlertDialog
import com.joohnq.moodapp.ui.components.TopBar
import com.joohnq.moodapp.ui.components.VerticalSpacer
import com.joohnq.moodapp.ui.presentation.edit_journaling_screen.event.EditJournalingEvent
import com.joohnq.moodapp.ui.presentation.edit_journaling_screen.state.EditJournalingState
import com.joohnq.moodapp.ui.theme.Colors
import com.joohnq.moodapp.ui.theme.ComponentColors
import com.joohnq.moodapp.ui.theme.Drawables
import com.joohnq.moodapp.ui.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.moodapp.ui.theme.TextStyles
import com.joohnq.moodapp.viewmodel.HealthJournalIntent
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.delete_journal
import moodapp.composeapp.generated.resources.do_you_wish_to_remove_this_journal
import moodapp.composeapp.generated.resources.edit_journal
import moodapp.composeapp.generated.resources.type_here_your_description
import moodapp.composeapp.generated.resources.type_here_your_title
import org.jetbrains.compose.resources.stringResource

@Composable fun EditJournalingUI(
    state: EditJournalingState,
) {
    val titleFocusRequest = remember { FocusRequester() }
    val descriptionFocusRequest = remember { FocusRequester() }

    if (state.openDeleteDialog) MainAlertDialog(
        onDismissRequest = { state.onAction(HealthJournalIntent.UpdateEditingOpenDeleteDialog(false)) },
        onConfirmation = {
            state.onAction(HealthJournalIntent.UpdateEditingOpenDeleteDialog(false))
            state.onAction(HealthJournalIntent.DeleteHealthJournal(state.healthJournal.id))
        },
        dialogTitle = Res.string.delete_journal,
        dialogText = Res.string.do_you_wish_to_remove_this_journal,
        icon = Drawables.Icons.Trash,
        backgroundColor = Colors.White
    )

    Scaffold(
        containerColor = Colors.Brown10,
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(state.snackBarState) },
        floatingActionButton = {
            EditFloatingActionButtons(
                isEditing = state.isEditing,
                canSave = state.isDifferent && state.healthJournal.title.isNotBlank() && state.healthJournal.description.isNotBlank(),
                onAction = state.onAction,
                requestTitleFocus = titleFocusRequest::requestFocus,
            )
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { pad ->
        val padding = PaddingValues(
            top = pad.calculateTopPadding(),
            bottom = pad.calculateBottomPadding() + 80.dp,
        )
        Column(
            Modifier.fillMaxSize().padding(padding)
                .verticalScroll(rememberScrollState()),
        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
                    .paddingHorizontalMedium()
            ) {
                TopBar(
                    onGoBack = { state.onEvent(EditJournalingEvent.OnGoBack) },
                    text = Res.string.edit_journal
                )
            }
            VerticalSpacer(30.dp)
            TextField(
                enabled = state.isEditing,
                value = state.healthJournal.title,
                placeholder = {
                    Text(
                        text = stringResource(Res.string.type_here_your_title),
                        style = TextStyles.HeadingMdExtraBold(),
                        color = Colors.Brown100Alpha64
                    )
                },
                onValueChange = { state.onAction(HealthJournalIntent.UpdateEditingTitle(it)) },
                modifier = Modifier.fillMaxWidth().focusRequester(titleFocusRequest),
                colors = ComponentColors.TextField.TextFieldTitleTransparentColors(),
                textStyle = TextStyles.HeadingMdExtraBold(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = { titleFocusRequest.freeFocus() }
                )
            )
            VerticalSpacer(10.dp)
            TextField(
                enabled = state.isEditing,
                value = state.healthJournal.description,
                placeholder = {
                    Text(
                        text = stringResource(Res.string.type_here_your_description),
                        style = TextStyles.ParagraphLg(),
                        color = Colors.Brown100Alpha64
                    )
                },
                onValueChange = { state.onAction(HealthJournalIntent.UpdateEditingDescription(it)) },
                modifier = Modifier.fillMaxWidth().focusRequester(descriptionFocusRequest),
                colors = ComponentColors.TextField.TextFieldDescriptionTransparentColors(),
                textStyle = TextStyles.ParagraphLg(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = { descriptionFocusRequest.freeFocus() }
                )
            )
        }
    }
}

@Preview
@Composable
fun Preview() {
    EditJournalingUI(
        EditJournalingState(
            snackBarState = remember { SnackbarHostState() },
            isEditing = true,
            isDifferent = true,
            healthJournal = HealthJournalRecord.init(),
            onAction = {},
            onEvent = {},
            openDeleteDialog = false
        )
    )
}