package com.joohnq.self_journal.ui.presentation.edit_self_journal

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
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.joohnq.domain.mapper.toFormattedDateString
import com.joohnq.mood.ui.resource.mapper.toResource
import com.joohnq.self_journal.ui.components.EditFloatingActionButtons
import com.joohnq.self_journal.ui.presentation.edit_self_journal.viewmodel.EditSelfJournalContract
import com.joohnq.self_journal.ui.presentation.self_journal.viewmodel.SelfJournalContract
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.ImageAlertDialog
import com.joohnq.shared_resources.components.ScaffoldSnackBar
import com.joohnq.shared_resources.components.TextWithBackground
import com.joohnq.shared_resources.components.TopBar
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.delete_journal
import com.joohnq.shared_resources.do_you_wish_to_remove_this_journal
import com.joohnq.shared_resources.edit_journal
import com.joohnq.shared_resources.remember.rememberFocusRequester
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.ComponentColors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.shared_resources.type_here_your_description
import com.joohnq.shared_resources.type_here_your_title
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun EditJournalingUI(
    snackBarState: SnackbarHostState,
    state: EditSelfJournalContract.State,
    canSave: Boolean,
    onEvent: (EditSelfJournalContract.Event) -> Unit = {},
    onEditIntent: (EditSelfJournalContract.Intent) -> Unit = {},
    onIntent: (SelfJournalContract.Intent) -> Unit = {},
) {
    val titleFocusRequest = rememberFocusRequester()
    val descriptionFocusRequest = rememberFocusRequester()
    val mood = state.editingSelfJournalRecord.mood
    val resource = mood.toResource()

    if (state.openDeleteDialog)
        ImageAlertDialog(
            onDismissRequest = {
                onEditIntent(
                    EditSelfJournalContract.Intent.UpdateOpenDeleteDialog(
                        false
                    )
                )
            },
            onConfirmation = {
                onEditIntent(EditSelfJournalContract.Intent.UpdateOpenDeleteDialog(false))
                onIntent(SelfJournalContract.Intent.Delete(state.editingSelfJournalRecord.id))
            },
            dialogTitle = Res.string.delete_journal,
            dialogText = Res.string.do_you_wish_to_remove_this_journal,
            image = Drawables.Images.SelfJournalDeleting,
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
                onIntent = onEditIntent,
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
                    onGoBack = { onEvent(EditSelfJournalContract.Event.GoBack) },
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
                onValueChange = { onEditIntent(EditSelfJournalContract.Intent.UpdateTitle(it)) },
                modifier = Modifier.fillMaxWidth().focusRequester(titleFocusRequest),
                colors = ComponentColors.TextField.TextFieldTitleTransparentColors(),
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
                        style = TextStyles.textLgMedium(),
                        color = Colors.Brown100Alpha64
                    )
                },
                onValueChange = {
                    onEditIntent(
                        EditSelfJournalContract.Intent.UpdateDescription(it)
                    )
                },
                modifier = Modifier.fillMaxWidth().focusRequester(descriptionFocusRequest),
                colors = ComponentColors.TextField.TextFieldDescriptionTransparentColors(),
                textStyle = TextStyles.textLgMedium(),
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
        image = Drawables.Images.SelfJournalDeleting,
        backgroundColor = Colors.White
    )
}

