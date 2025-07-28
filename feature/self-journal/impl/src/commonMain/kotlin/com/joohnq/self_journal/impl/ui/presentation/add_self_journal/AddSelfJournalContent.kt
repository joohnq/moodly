package com.joohnq.self_journal.impl.ui.presentation.add_self_journal

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
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp
import com.joohnq.mood.impl.ui.components.MoodFace
import com.joohnq.mood.impl.ui.mapper.getAllMoodResource
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.AppTopBar
import com.joohnq.shared_resources.components.button.PrimaryButton
import com.joohnq.shared_resources.components.input_field.AppOutlinedTextField
import com.joohnq.shared_resources.components.input_field.ExpressionAnalysisTextField
import com.joohnq.shared_resources.components.layout.AppScaffoldLayout
import com.joohnq.shared_resources.components.spacer.VerticalSpacer
import com.joohnq.shared_resources.components.text.MediumTitle
import com.joohnq.shared_resources.continue_word
import com.joohnq.shared_resources.enter_the_title
import com.joohnq.shared_resources.journal_title
import com.joohnq.shared_resources.mood
import com.joohnq.shared_resources.new_journal_entry
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.ComponentColors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.paddingHorizontalMedium
import com.joohnq.shared_resources.write_your_entry
import org.jetbrains.compose.resources.painterResource

@Composable
fun AddJournalingContent(
    snackBarState: SnackbarHostState = rememberSnackBarState(),
    state: AddSelfJournalContract.State,
    onAction: (AddSelfJournalContract.Intent) -> Unit = {},
    onEvent: (AddSelfJournalContract.Event) -> Unit = {},
) {
    val focusRequester = FocusRequester()
    val moods = remember { getAllMoodResource() }

    AppScaffoldLayout(
        containerColor = Colors.Brown10,
        modifier = Modifier.fillMaxSize(),
        snackBarHostState = snackBarState
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).verticalScroll(rememberScrollState())
        ) {
            Column(
                Modifier
                    .fillMaxSize()
                    .paddingHorizontalMedium()
            ) {
                AppTopBar(
                    onGoBack = { onEvent(AddSelfJournalContract.Event.OnGoBack) },
                    text = Res.string.new_journal_entry
                )
                MediumTitle(Res.string.journal_title)
                AppOutlinedTextField(
                    text = state.title,
                    placeholder = Res.string.enter_the_title,
                    errorText = state.titleError,
                    colors = ComponentColors.TextField.mainTextFieldColors(),
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
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .focusRequester(focusRequester),
                    onValueChange = {
                        onAction(
                            AddSelfJournalContract.Intent.UpdateTitle(
                                it
                            )
                        )
                    }
                )
                MediumTitle(Res.string.mood)
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(moods) { resource ->
                        MoodFace(
                            modifier = Modifier.size(32.dp),
                            resource = resource,
                            backgroundColor =
                                if (state.mood ==
                                    resource
                                ) {
                                    resource.palette.faceBackgroundColor
                                } else {
                                    Colors.Gray30
                                },
                            color = if (state.mood == resource) resource.palette.faceColor else Colors.Gray60,
                            onClick = {
                                onAction(
                                    AddSelfJournalContract.Intent.UpdateMood(resource)
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
                            AddSelfJournalContract.Intent.UpdateDescription(it)
                        )
                    }
                )
            }
            Column(
                Modifier.fillMaxSize().paddingHorizontalMedium()
            ) {
                VerticalSpacer(24.dp)
                PrimaryButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = Res.string.continue_word,
                    onClick = { onEvent(AddSelfJournalContract.Event.OnAdd) }
                )
            }
        }
    }
}
