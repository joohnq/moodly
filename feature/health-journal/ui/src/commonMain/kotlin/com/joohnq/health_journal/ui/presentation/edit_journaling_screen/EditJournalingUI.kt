package com.joohnq.health_journal.ui.presentation.edit_journaling_screen

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
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.health_journal.ui.components.EditFloatingActionButtons
import com.joohnq.health_journal.ui.presentation.edit_journaling_screen.event.EditJournalingEvent
import com.joohnq.health_journal.ui.presentation.edit_journaling_screen.state.EditJournalingState
import com.joohnq.health_journal.ui.presentation.edit_journaling_screen.viewmodel.EditJournalingIntent
import com.joohnq.health_journal.ui.viewmodel.HealthJournalIntent
import com.joohnq.mood.ui.MoodResource.Companion.toResource
import com.joohnq.shared.domain.DatetimeProvider
import com.joohnq.shared.ui.Res
import com.joohnq.shared.ui.components.MainAlertDialog
import com.joohnq.shared.ui.components.TextWithBackground
import com.joohnq.shared.ui.components.TopBar
import com.joohnq.shared.ui.components.VerticalSpacer
import com.joohnq.shared.ui.delete_journal
import com.joohnq.shared.ui.do_you_wish_to_remove_this_journal
import com.joohnq.shared.ui.edit_journal
import com.joohnq.shared.ui.theme.Colors
import com.joohnq.shared.ui.theme.ComponentColors
import com.joohnq.shared.ui.theme.Drawables
import com.joohnq.shared.ui.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared.ui.theme.TextStyles
import com.joohnq.shared.ui.type_here_your_description
import com.joohnq.shared.ui.type_here_your_title
import org.jetbrains.compose.resources.stringResource

@Composable fun EditJournalingUI(
    state: EditJournalingState,
) {
    val titleFocusRequest = remember { FocusRequester() }
    val descriptionFocusRequest = remember {
        FocusRequester()
    }
    val mood = state.healthJournal.mood
    val resource = mood.toResource()

    if (state.openDeleteDialog)
        MainAlertDialog(
            onDismissRequest = {
                state.onEditingAction(
                    EditJournalingIntent.UpdateOpenDeleteDialog(
                        false
                    )
                )
            },
            onConfirmation = {
                state.onEditingAction(EditJournalingIntent.UpdateOpenDeleteDialog(false))
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
                canSave = state.canSave,
                onEditingAction = state.onEditingAction,
                onEvent = state.onEvent,
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
                    text = Res.string.edit_journal,
                ) {
                    TextWithBackground(
                        text = DatetimeProvider.formatDateTime(state.healthJournal.date),
                        textColor = resource.palette.moodScreenMoodFaceColor,
                        backgroundColor = resource.palette.subColor,
                    )
                }
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
                onValueChange = { state.onEditingAction(EditJournalingIntent.UpdateTitle(it)) },
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
                onValueChange = {
                    state.onEditingAction(
                        EditJournalingIntent.UpdateDescription(it)
                    )
                },
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
            healthJournal = HealthJournalRecord(),
            openDeleteDialog = false,
            canSave = false,
        )
    )
}