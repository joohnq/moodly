package com.joohnq.moodapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.ui.theme.Colors
import com.joohnq.moodapp.ui.theme.ComponentColors
import com.joohnq.moodapp.ui.theme.Dimens
import com.joohnq.moodapp.ui.theme.TextStyles
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.cancel
import moodapp.composeapp.generated.resources.confirm
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun MainAlertDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: StringResource,
    dialogText: StringResource,
    icon: DrawableResource,
    backgroundColor: Color
) {
    AlertDialog(
        containerColor = backgroundColor,
        icon = {
            Box(
                modifier = Modifier.size(56.dp).background(
                    color = Colors.Brown80,
                    shape = Dimens.Shape.Circle
                ),
                contentAlignment = Alignment.Center
            ) {
                androidx.compose.material3.Icon(
                    painter = painterResource(icon),
                    contentDescription = stringResource(dialogTitle),
                    modifier = Modifier.size(32.dp),
                    tint = Colors.White,
                )
            }
        },
        title = {
            Text(
                text = stringResource(dialogTitle),
                style = TextStyles.TextXlBold(),
                color = Colors.Brown80
            )
        },
        text = {
            Text(
                text = stringResource(dialogText),
                style = TextStyles.TextMdSemiBold(),
                color = Colors.Brown100Alpha64,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        },
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(
                onClick = onConfirmation,
                shape = Dimens.Shape.ExtraSmall,
                colors = ComponentColors.Button.MainButtonColors(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 10.dp)
            ) {
                Text(
                    text = stringResource(Res.string.confirm),
                    style = TextStyles.TextMdBold(),
                    color = Colors.White,
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text(
                    text = stringResource(Res.string.cancel),
                    style = TextStyles.TextMdBold(),
                    color = Colors.Brown80
                )
            }
        }
    )
}