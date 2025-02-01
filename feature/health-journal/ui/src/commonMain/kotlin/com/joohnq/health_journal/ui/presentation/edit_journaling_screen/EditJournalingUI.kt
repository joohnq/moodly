package com.joohnq.health_journal.ui.presentation.edit_journaling_screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FabPosition
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.joohnq.core.ui.mapper.toFormattedDateString
import com.joohnq.health_journal.domain.entity.HealthJournalRecord
import com.joohnq.health_journal.ui.components.EditFloatingActionButtons
import com.joohnq.health_journal.ui.presentation.edit_journaling_screen.event.EditJournalingEvent
import com.joohnq.health_journal.ui.presentation.edit_journaling_screen.viewmodel.EditJournalingIntent
import com.joohnq.health_journal.ui.presentation.edit_journaling_screen.viewmodel.EditJournalingState
import com.joohnq.health_journal.ui.viewmodel.HealthJournalIntent
import com.joohnq.mood.ui.mapper.toResource
import com.joohnq.shared_resources.*
import com.joohnq.shared_resources.components.*
import com.joohnq.shared_resources.remember.rememberFocusRequester
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.ComponentColors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun EditJournalingUI(
    snackBarState: SnackbarHostState,
    state: EditJournalingState,
    canSave: Boolean,
    onEvent: (EditJournalingEvent) -> Unit = {},
    onAction: (EditJournalingIntent) -> Unit = {},
    onHealthJournalAction: (HealthJournalIntent) -> Unit = {},
) {
    val titleFocusRequest = rememberFocusRequester()
    val descriptionFocusRequest = rememberFocusRequester()
    val mood = state.editingHealthJournalRecord.mood
    val resource = mood.toResource()

    if (state.openDeleteDialog)
        ImageAlertDialog(
            onDismissRequest = {
                onAction(
                    EditJournalingIntent.UpdateOpenDeleteDialog(
                        false
                    )
                )
            },
            onConfirmation = {
                onAction(EditJournalingIntent.UpdateOpenDeleteDialog(false))
                onHealthJournalAction(HealthJournalIntent.DeleteHealthJournal(state.editingHealthJournalRecord.id))
            },
            dialogTitle = Res.string.delete_journal,
            dialogText = Res.string.do_you_wish_to_remove_this_journal,
            image = Drawables.Images.DeleteSelfJournalIllustration,
            backgroundColor = Colors.White
        )

    ScaffoldSnackBar(
        containerColor = Colors.Brown10,
        modifier = Modifier.fillMaxSize(),
        snackBarHostState = snackBarState,
        floatingActionButton = {
            EditFloatingActionButtons(
                isEditing = state.isEditing,
                canSave = canSave,
                onEditingAction = onAction,
                onEvent = onEvent,
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
                    onGoBack = { onEvent(EditJournalingEvent.OnGoBack) },
                    text = Res.string.edit_journal,
                ) {
                    TextWithBackground(
                        text = state.editingHealthJournalRecord.createdAt.date.toFormattedDateString(),
                        textColor = resource.palette.moodScreenMoodFaceColor,
                        backgroundColor = resource.palette.subColor,
                    )
                }
            }
            VerticalSpacer(30.dp)
            TextField(
                enabled = state.isEditing,
                value = state.editingHealthJournalRecord.title,
                placeholder = {
                    Text(
                        text = stringResource(Res.string.type_here_your_title),
                        style = TextStyles.HeadingMdExtraBold(),
                        color = Colors.Brown100Alpha64
                    )
                },
                onValueChange = { onAction(EditJournalingIntent.UpdateTitle(it)) },
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
                value = state.editingHealthJournalRecord.description,
                placeholder = {
                    Text(
                        text = stringResource(Res.string.type_here_your_description),
                        style = TextStyles.ParagraphLg(),
                        color = Colors.Brown100Alpha64
                    )
                },
                onValueChange = {
                    onAction(
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
fun EditJournalingUIPreview() {
    ImageAlertDialog(
        onDismissRequest = {

        },
        onConfirmation = {

        },
        dialogTitle = Res.string.delete_journal,
        dialogText = Res.string.do_you_wish_to_remove_this_journal,
        image = Drawables.Images.DeleteSelfJournalIllustration,
        backgroundColor = Colors.White
    )
}

