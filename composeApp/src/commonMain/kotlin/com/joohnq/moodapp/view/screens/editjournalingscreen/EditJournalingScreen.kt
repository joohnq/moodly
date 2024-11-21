package com.joohnq.moodapp.view.screens.editjournalingscreen

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.joohnq.moodapp.entities.HealthJournalRecord
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.view.NextAndBackAction
import com.joohnq.moodapp.view.ScreenDimensions
import com.joohnq.moodapp.view.components.MainAlertDialog
import com.joohnq.moodapp.view.components.TopBar
import com.joohnq.moodapp.view.components.VerticalSpacer
import com.joohnq.moodapp.view.state.UiState.Companion.onSuccess
import com.joohnq.moodapp.view.ui.Colors
import com.joohnq.moodapp.view.ui.ComponentColors
import com.joohnq.moodapp.view.ui.Dimens
import com.joohnq.moodapp.view.ui.Drawables
import com.joohnq.moodapp.view.ui.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.moodapp.view.ui.TextStyles
import com.joohnq.moodapp.viewmodel.HealthJournalIntent
import com.joohnq.moodapp.viewmodel.HealthJournalViewModel
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.delete_journal
import moodapp.composeapp.generated.resources.do_you_wish_to_remove_this_journal
import moodapp.composeapp.generated.resources.edit_journal
import moodapp.composeapp.generated.resources.editing
import moodapp.composeapp.generated.resources.remove_journal
import moodapp.composeapp.generated.resources.save
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

@Composable fun EditJournalingScreenUI(
    snackBarState: SnackbarHostState = remember { SnackbarHostState() },
    healthJournal: HealthJournalRecord,
    isEditing: Boolean,
    isDifferent: Boolean,
    openDeleteDialog: Boolean,
    onNavigation: (NextAndBackAction) -> Unit = {},
    onAction: (HealthJournalIntent) -> Unit = {}
) {
    val titleFocusRequest = remember { FocusRequester() }
    val descriptionFocusRequest = remember { FocusRequester() }
    val screenDimensions: ScreenDimensions = koinInject()

    if (openDeleteDialog) MainAlertDialog(
        onDismissRequest = { onAction(HealthJournalIntent.UpdateEditingOpenDeleteDialog(false)) },
        onConfirmation = {
            onAction(HealthJournalIntent.UpdateEditingOpenDeleteDialog(false))
            onAction(HealthJournalIntent.DeleteHealthJournal(healthJournal.id))
        },
        dialogTitle = Res.string.delete_journal,
        dialogText = Res.string.do_you_wish_to_remove_this_journal,
        icon = Drawables.Icons.Trash,
        backgroundColor = Colors.White
    )

    Scaffold(
        containerColor = Colors.Brown10,
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackBarState) },
        floatingActionButtonPosition = FabPosition.Center,
        floatingActionButton = {
            Row(
                modifier = Modifier.background(
                    color = Colors.White,
                    shape = Dimens.Shape.Circle
                ).padding(10.dp), horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                FilledIconButton(
                    onClick = { onAction(HealthJournalIntent.UpdateEditingOpenDeleteDialog(true)) },
                    modifier = Modifier.size(56.dp),
                    shape = Dimens.Shape.Circle,
                    colors = IconButtonColors(
                        containerColor = Colors.Orange50,
                        contentColor = Colors.White,
                        disabledContainerColor = Colors.Orange50,
                        disabledContentColor = Colors.White
                    )
                ) {
                    Icon(
                        painter = painterResource(Drawables.Icons.Trash),
                        contentDescription = stringResource(Res.string.remove_journal),
                        tint = Colors.White,
                        modifier = Modifier.size(28.dp)
                    )
                }
                Button(
                    onClick = {
                        onAction(HealthJournalIntent.UpdateIsEditing(!isEditing))
                        titleFocusRequest.requestFocus()
                    },
                    contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp),
                    shape = Dimens.Shape.Circle,
                    colors = ButtonColors(
                        containerColor = if (isEditing) Colors.Yellow50 else Colors.White,
                        contentColor = if (isEditing) Colors.White else Colors.Brown80,
                        disabledContainerColor = if (isEditing) Colors.Yellow50 else Colors.White,
                        disabledContentColor = if (isEditing) Colors.White else Colors.Brown80,
                    ),
                    modifier = Modifier.height(56.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(Drawables.Icons.Edit2),
                            contentDescription = stringResource(Res.string.remove_journal),
                            tint = if (isEditing) Colors.White else Colors.Brown80,
                            modifier = Modifier.size(28.dp)
                        )
                        if (isEditing) Text(
                            text = stringResource(Res.string.editing),
                            style = TextStyles.TextMdBold(),
                            color = Colors.White
                        )
                    }
                }
                Button(
                    enabled = isDifferent,
                    onClick = {
                        onAction(HealthJournalIntent.UpdateEditingHealthJournal)
                    },
                    contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp),
                    shape = Dimens.Shape.Circle,
                    colors = ButtonColors(
                        containerColor = Colors.Green60,
                        contentColor = Colors.White,
                        disabledContainerColor = Colors.Gray60,
                        disabledContentColor = Colors.Gray90,
                    ),
                    modifier = Modifier.height(56.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(Drawables.Icons.Check),
                            contentDescription = stringResource(Res.string.remove_journal),
                            tint = Colors.White,
                            modifier = Modifier.size(28.dp)
                        )
                        if (isDifferent)
                            Text(
                                text = stringResource(Res.string.save),
                                style = TextStyles.TextMdBold(),
                                color = Colors.White
                            )
                    }
                }
            }
        }
    ) { pad ->
        val padding = PaddingValues(
            top = pad.calculateTopPadding(),
            bottom = pad.calculateBottomPadding() + 100.dp,
        )

        Column(
            Modifier.fillMaxSize().padding(bottom = padding.calculateBottomPadding())
                .verticalScroll(rememberScrollState()),
        ) {
            Box(
                modifier = Modifier.fillMaxWidth().paddingHorizontalMedium()
                    .padding(top = padding.calculateTopPadding())
            ) {
                TopBar(
                    onGoBack = { onNavigation(NextAndBackAction.OnGoBack) },
                    text = Res.string.edit_journal
                )
            }
            VerticalSpacer(30.dp)
            TextField(
                enabled = isEditing,
                value = healthJournal.title,
                onValueChange = { onAction(HealthJournalIntent.UpdateEditingTitle(it)) },
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
                enabled = isEditing,
                value = healthJournal.description,
                onValueChange = { onAction(HealthJournalIntent.UpdateEditingDescription(it)) },
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

@Composable fun EditJournalingScreen(
    id: Int,
    navigation: NavHostController,
    healthJournalViewModel: HealthJournalViewModel = sharedViewModel()
) {
    val snackBarState = remember { SnackbarHostState() }
    val healthJournalState by healthJournalViewModel.healthJournalState.collectAsState()

    LaunchedEffect(Unit) {
        healthJournalViewModel.onAction(HealthJournalIntent.GetHealthJournalById(id))
    }

    LaunchedEffect(healthJournalState.deleting) {
        healthJournalState.deleting.onSuccess {
            navigation.popBackStack()
            healthJournalViewModel.onAction(HealthJournalIntent.ResetDeletingHeathJournal)
        }
    }

    LaunchedEffect(healthJournalState.editing.status) {
        healthJournalState.editing.status.onSuccess {
            navigation.popBackStack()
            healthJournalViewModel.onAction(HealthJournalIntent.GetHealthJournals)
            healthJournalViewModel.onAction(HealthJournalIntent.ResetEditingHeathJournal)
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            healthJournalViewModel.onAction(HealthJournalIntent.ResetEditingHeathJournal)
        }
    }

    EditJournalingScreenUI(snackBarState = snackBarState,
        healthJournal = healthJournalState.editing.editingHealthJournalRecord,
        isEditing = healthJournalState.editing.isEditing,
        isDifferent = healthJournalState.editing.editingHealthJournalRecord.title != healthJournalState.editing.currentHealthJournalRecord.title || healthJournalState.editing.editingHealthJournalRecord.description != healthJournalState.editing.currentHealthJournalRecord.description,
        openDeleteDialog = healthJournalState.editing.openDeleteDialog,
        onAction = healthJournalViewModel::onAction,
        onNavigation = { action ->
            when (action) {
                NextAndBackAction.OnGoBack -> navigation.popBackStack()
                NextAndBackAction.OnContinue -> {}
            }
        })
}

@Preview @Composable fun EditJournalingScreenPreview() {
    EditJournalingScreenUI(
        healthJournal = HealthJournalRecord.init()
            .copy(title = "Title Here", description = "Description Here"),
        isEditing = false,
        openDeleteDialog = false,
        isDifferent = false
    )
}


@Preview @Composable fun EditJournalingScreenPreview2() {
    EditJournalingScreenUI(
        healthJournal = HealthJournalRecord.init()
            .copy(title = "Title Here", description = "Description Here"),
        isEditing = true,
        openDeleteDialog = false,
        isDifferent = true
    )
}


@Preview @Composable fun EditJournalingScreenPreview3() {
    EditJournalingScreenUI(
        healthJournal = HealthJournalRecord.init()
            .copy(title = "Title Here", description = "Description Here"),
        isEditing = false,
        openDeleteDialog = true,
        isDifferent = false
    )
}