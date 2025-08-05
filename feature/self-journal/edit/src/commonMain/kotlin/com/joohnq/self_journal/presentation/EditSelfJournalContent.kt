package com.joohnq.self_journal.presentation

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
import com.joohnq.api.mapper.LocalDateMapper.toFormattedDateString
import com.joohnq.mood.add.ui.mapper.MoodResourceMapper.toResource
import com.joohnq.self_journal.components.EditFloatingActionButtons
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.AppTopBar
import com.joohnq.shared_resources.components.layout.AppScaffoldLayout
import com.joohnq.shared_resources.components.layout.ImageDialogLayout
import com.joohnq.shared_resources.components.spacer.VerticalSpacer
import com.joohnq.shared_resources.components.text.TextWithBackground
import com.joohnq.shared_resources.delete_journal
import com.joohnq.shared_resources.do_you_wish_to_remove_this_journal
import com.joohnq.shared_resources.edit_journal
import com.joohnq.shared_resources.remember.rememberFocusRequester
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.ComponentColors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.shared_resources.type_here_your_description
import com.joohnq.shared_resources.type_here_your_title
import org.jetbrains.compose.resources.stringResource

@Composable
fun EditJournalingContent(
    snackBarState: SnackbarHostState = rememberSnackBarState(),
    state: EditSelfJournalContract.State,
    canSave: Boolean,
    onEvent: (EditSelfJournalContract.Event) -> Unit = {},
    onIntent: (EditSelfJournalContract.Intent) -> Unit = {},
) {
    val titleFocusRequest = rememberFocusRequester()
    val descriptionFocusRequest = rememberFocusRequester()
    val mood = state.editingSelfJournalRecord.mood
    val resource = mood.toResource()

    if (state.openDeleteDialog) {
        ImageDialogLayout(
            onDismissRequest = {
                onIntent(
                    EditSelfJournalContract.Intent.UpdateOpenDeleteDialog(
                        false
                    )
                )
            },
            onConfirmation = {
                onIntent(EditSelfJournalContract.Intent.UpdateOpenDeleteDialog(false))
                onIntent(EditSelfJournalContract.Intent.Delete(state.editingSelfJournalRecord.id))
            },
            dialogTitle = Res.string.delete_journal,
            dialogText = Res.string.do_you_wish_to_remove_this_journal,
            image = Drawables.Images.SelfJournalDeleting,
            backgroundColor = Colors.White
        )
    }

    AppScaffoldLayout(
        containerColor = Colors.Brown10,
        modifier = Modifier.fillMaxSize(),
        snackBarHostState = snackBarState,
        floatingActionButton = {
            EditFloatingActionButtons(
                isEditing = state.isEditing,
                canSave = canSave,
                onDelete = {
                    onIntent(
                        EditSelfJournalContract.Intent.UpdateOpenDeleteDialog(true)
                    )
                },
                onEdit = {
                    onIntent(EditSelfJournalContract.Intent.UpdateIsEditing(!state.isEditing))
                },
                onSave = { onIntent(EditSelfJournalContract.Intent.Update) }
            )
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { pad ->
        val padding =
            PaddingValues(
                top = pad.calculateTopPadding(),
                bottom = pad.calculateBottomPadding() + 80.dp
            )
        Column(
            Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .paddingHorizontalMedium()
            ) {
                AppTopBar(
                    onGoBack = { onEvent(EditSelfJournalContract.Event.OnGoBack) },
                    text = Res.string.edit_journal
                ) {
                    TextWithBackground(
                        text =
                            state.editingSelfJournalRecord.createdAt.date
                                .toFormattedDateString(),
                        textColor = resource.palette.backgroundColor,
                        backgroundColor = resource.palette.color
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
                onValueChange = { onIntent(EditSelfJournalContract.Intent.UpdateTitle(it)) },
                modifier = Modifier.fillMaxWidth().focusRequester(titleFocusRequest),
                colors = ComponentColors.TextField.textFieldTitleTransparentColors(),
                textStyle = TextStyles.headingMdExtraBold(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions =
                    KeyboardActions(
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
                    onIntent(
                        EditSelfJournalContract.Intent.UpdateDescription(it)
                    )
                },
                modifier = Modifier.fillMaxWidth().focusRequester(descriptionFocusRequest),
                colors = ComponentColors.TextField.textFieldDescriptionTransparentColors(),
                textStyle = TextStyles.paragraphLg(),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions =
                    KeyboardActions(
                        onDone = { descriptionFocusRequest.freeFocus() }
                    )
            )
        }
    }
}
