package com.joohnq.self_journal.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.self_journal.ui.presentation.edit_self_journal.event.EditSelfJournalEvent
import com.joohnq.self_journal.ui.presentation.edit_self_journal.viewmodel.EditSelfJournalIntent
import com.joohnq.shared_resources.*
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun EditFloatingActionButtons(
    isEditing: Boolean,
    canSave: Boolean,
    onEditingAction: (EditSelfJournalIntent) -> Unit,
    onEvent: (EditSelfJournalEvent) -> Unit,
    requestTitleFocus: () -> Unit,
) {
    Box(
        modifier = Modifier.imePadding().fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        LazyRow(
            modifier = Modifier.background(
                color = Colors.White,
                shape = Dimens.Shape.Circle
            ).padding(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            item {
                FilledIconButton(
                    onClick = {
                        onEditingAction(
                            EditSelfJournalIntent.UpdateOpenDeleteDialog(true)
                        )
                    },
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
            }
            item {
                Button(
                    onClick = {
                        onEditingAction(EditSelfJournalIntent.UpdateIsEditing(!isEditing))
                        requestTitleFocus()
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
                            painter = painterResource(Drawables.Icons.Edit),
                            contentDescription = stringResource(Res.string.edit_journal),
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
            }
            item {
                Button(
                    enabled = canSave,
                    onClick = {
                        onEvent(EditSelfJournalEvent.OnSave)
                    },
                    contentPadding = if (canSave) PaddingValues(
                        horizontal = 20.dp,
                        vertical = 8.dp
                    ) else PaddingValues(0.dp),
                    shape = Dimens.Shape.Circle,
                    colors = ButtonColors(
                        containerColor = Colors.Green60,
                        contentColor = Colors.White,
                        disabledContainerColor = Colors.Gray60,
                        disabledContentColor = Colors.Gray90,
                    ),
                    modifier = Modifier.height(56.dp).then(
                        if (canSave) Modifier.width(IntrinsicSize.Max) else Modifier.width(56.dp)
                    )
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
                        if (canSave)
                            Text(
                                text = stringResource(Res.string.save),
                                style = TextStyles.TextMdBold(),
                                color = Colors.White
                            )
                    }
                }
            }
        }
    }
}