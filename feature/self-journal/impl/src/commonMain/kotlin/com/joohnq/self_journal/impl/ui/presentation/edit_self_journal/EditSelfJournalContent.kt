package com.joohnq.self_journal.impl.ui.presentation.edit_self_journal

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
import com.joohnq.api.mapper.toFormattedDateString
import com.joohnq.mood.impl.ui.mapper.toResource
import com.joohnq.self_journal.impl.ui.components.EditFloatingActionButtons
import com.joohnq.self_journal.impl.ui.presentation.edit_self_journal.event.EditSelfJournalEvent
import com.joohnq.self_journal.impl.ui.presentation.edit_self_journal.viewmodel.EditSelfJournalIntent
import com.joohnq.self_journal.impl.ui.presentation.edit_self_journal.viewmodel.EditSelfJournalState
import com.joohnq.self_journal.impl.ui.viewmodel.SelfJournalIntent
import com.joohnq.shared_resources.*
import com.joohnq.shared_resources.components.*
import com.joohnq.shared_resources.components.layout.AppScaffoldLayout
import com.joohnq.shared_resources.components.layout.ImageDialogLayout
import com.joohnq.shared_resources.components.spacer.VerticalSpacer
import com.joohnq.shared_resources.components.text.TextWithBackground
import com.joohnq.shared_resources.remember.rememberFocusRequester
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.ComponentColors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.stringResource

@Composable
fun EditJournalingContent(
    snackBarState: SnackbarHostState = rememberSnackBarState(),
    state: EditSelfJournalState,
    canSave: Boolean,
    onEvent: (EditSelfJournalEvent) -> Unit = {},
    onAction: (EditSelfJournalIntent) -> Unit = {},
    onSelfJournalAction: (SelfJournalIntent) -> Unit = {},
) {
    val titleFocusRequest = rememberFocusRequester()
    val descriptionFocusRequest = rememberFocusRequester()
    val mood = state.editingSelfJournalRecord.mood
    val resource = mood.toResource()

    if (state.openDeleteDialog)
        ImageDialogLayout(
            onDismissRequest = {
                onAction(
                    EditSelfJournalIntent.UpdateOpenDeleteDialog(
                        false
                    )
                )
            },
            onConfirmation = {
                onAction(EditSelfJournalIntent.UpdateOpenDeleteDialog(false))
                onSelfJournalAction(SelfJournalIntent.Delete(state.editingSelfJournalRecord.id))
            },
            dialogTitle = Res.string.delete_journal,
            dialogText = Res.string.do_you_wish_to_remove_this_journal,
            image = Drawables.Images.SelfJournalDeleting,
            backgroundColor = Colors.White
        )

    AppScaffoldLayout(
        containerColor = Colors.Brown10,
        modifier = Modifier.fillMaxSize(),
        snackBarHostState = snackBarState,
        floatingActionButton = {
            EditFloatingActionButtons(
                isEditing = state.isEditing,
                canSave = canSave,
                onEditingAction = onAction,
                onEvent = onEvent,
                onRequestTitleFocus = titleFocusRequest::requestFocus,
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
                AppTopBar(
                    onGoBack = { onEvent(EditSelfJournalEvent.OnGoBack) },
                    text = Res.string.edit_journal,
                ) {
                    TextWithBackground(
                        text = state.editingSelfJournalRecord.createdAt.date.toFormattedDateString(),
                        textColor = resource.palette.backgroundColor,
                        backgroundColor = resource.palette.color,
                    )
                }
            }
            VerticalSpacer(30.dp)
            TextField(
                enabled = state.isEditing,
                value = state.editingSelfJournalRecord.title,
                placeholder = {
                    Text(
                        text = stringResource(Res.string.type_here_your_title),
                        style = TextStyles.headingMdExtraBold(),
                        color = Colors.Brown100Alpha64
                    )
                },
                onValueChange = { onAction(EditSelfJournalIntent.UpdateTitle(it)) },
                modifier = Modifier.fillMaxWidth().focusRequester(titleFocusRequest),
                colors = ComponentColors.TextField.textFieldTitleTransparentColors(),
                textStyle = TextStyles.headingMdExtraBold(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = { titleFocusRequest.freeFocus() }
                )
            )
            VerticalSpacer(10.dp)
            TextField(
                enabled = state.isEditing,
                value = state.editingSelfJournalRecord.description,
                placeholder = {
                    Text(
                        text = stringResource(Res.string.type_here_your_description),
                        style = TextStyles.paragraphLg(),
                        color = Colors.Brown100Alpha64
                    )
                },
                onValueChange = {
                    onAction(
                        EditSelfJournalIntent.UpdateDescription(it)
                    )
                },
                modifier = Modifier.fillMaxWidth().focusRequester(descriptionFocusRequest),
                colors = ComponentColors.TextField.textFieldDescriptionTransparentColors(),
                textStyle = TextStyles.paragraphLg(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = { descriptionFocusRequest.freeFocus() }
                )
            )
        }
    }
}