package com.joohnq.self_journal.impl.ui.presentation.add_self_journal

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.unit.dp
import com.joohnq.mood.ui.components.MoodFace
import com.joohnq.mood.ui.mapper.getAllMoodResource
import com.joohnq.self_journal.ui.presentation.add_self_journal.event.AddSelfJournalEvent
import com.joohnq.self_journal.ui.presentation.add_self_journal.viewmodel.AddSelfJournalIntent
import com.joohnq.self_journal.ui.presentation.add_self_journal.viewmodel.AddSelfJournalState
import com.joohnq.shared_resources.*
import com.joohnq.shared_resources.components.*
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.ComponentColors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import org.jetbrains.compose.resources.painterResource

@Composable
fun AddJournalingUI(
    snackBarState: SnackbarHostState,
    state: AddSelfJournalState,
    onAction: (AddSelfJournalIntent) -> Unit = {},
    onEvent: (AddSelfJournalEvent) -> Unit = {},
) {
    val canContinue by derivedStateOf {
        state.title.isNotEmpty() && state.mood != null && state.description.isNotEmpty()
    }
    val focusRequester = FocusRequester()
    val moods = remember { getAllMoodResource() }

    ScaffoldSnackBar(
        containerColor = Colors.Brown10,
        modifier = Modifier.fillMaxSize(),
        snackBarHostState = snackBarState
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).verticalScroll(rememberScrollState())
        ) {
            Column(
                Modifier.fillMaxSize()
                    .paddingHorizontalMedium(),
            ) {
                TopBar(
                    onGoBack = { onEvent(AddSelfJournalEvent.OnGoBack) },
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
                            painter = painterResource(Drawables.Icons.Outlined.Document),
                            contentDescription = null,
                            tint = Colors.Brown80,
                            modifier = Modifier.size(Dimens.Icon)
                        )
                    },
                    trailingIcon = {
                        Icon(
                            painter = painterResource(Drawables.Icons.Outlined.Edit),
                            contentDescription = null,
                            tint = Colors.Brown80,
                            modifier = Modifier.size(Dimens.Icon)
                        )
                    },
                    modifier = Modifier.fillMaxWidth().height(56.dp)
                        .focusRequester(focusRequester),
                    onValueChange = {
                        onAction(
                            AddSelfJournalIntent.UpdateTitle(
                                it
                            )
                        )
                    },
                )
                MediumTitle(Res.string.mood)
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    items(moods) { resource ->
                        MoodFace(
                            modifier = Modifier.size(32.dp),
                            resource = resource,
                            backgroundColor = if (state.mood == resource) resource.palette.faceBackgroundColor else Colors.Gray30,
                            color = if (state.mood == resource) resource.palette.faceColor else Colors.Gray60,
                            onClick = {
                                onAction(
                                    AddSelfJournalIntent.UpdateMood(resource)
                                )
                            }
                        )
                    }
                }
                MediumTitle(Res.string.write_your_entry)
                ExpressionAnalysisTextField(
                    text = state.description,
                    onValueChange = {
                        onAction(
                            AddSelfJournalIntent.UpdateDescription(it)
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
                    onClick = { onEvent(AddSelfJournalEvent.OnAdd) }
                )
            }
        }
    }
}

