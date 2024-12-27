package com.joohnq.health_journal.ui.presentation.edit_journaling_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.health_journal.ui.presentation.edit_journaling_screen.event.EditJournalingEvent
import com.joohnq.mood.ui.theme.Colors
import com.joohnq.mood.ui.theme.Dimens
import com.joohnq.mood.ui.theme.Drawables
import com.joohnq.mood.ui.theme.TextStyles
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.edit_journal
import moodapp.composeapp.generated.resources.editing
import moodapp.composeapp.generated.resources.remove_journal
import moodapp.composeapp.generated.resources.save
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun EditFloatingActionButtons(
    isEditing: Boolean,
    canSave: Boolean,
    onEditingAction: (EditJournalingIntent) -> Unit,
    onEvent: (EditJournalingEvent) -> Unit,
    requestTitleFocus: () -> Unit
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
                            EditJournalingIntent.UpdateOpenDeleteDialog(true)
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
                        onEditingAction(EditJournalingIntent.UpdateIsEditing(!isEditing))
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
                        onEvent(EditJournalingEvent.OnSave)
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