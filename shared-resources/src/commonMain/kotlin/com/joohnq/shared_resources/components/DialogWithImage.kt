package com.joohnq.shared_resources.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.cancel
import com.joohnq.shared_resources.confirm
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.ComponentColors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun DialogWithImage(
    dialogTitle: StringResource,
    dialogText: StringResource,
    image: DrawableResource,
    backgroundColor: Color,
    onDismissRequest: () -> Unit = {},
    onConfirmation: () -> Unit = {},
) {
    AlertDialog(
        containerColor = backgroundColor,
        icon = {
            Image(
                painter = painterResource(image),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().height(250.dp)
            )
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
                color = Colors.Brown80,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        },
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(
                onClick = onConfirmation,
                shape = Dimens.Shape.ExtraSmall,
                colors = ComponentColors.Button.DeleteButtonColors(),
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
