package com.joohnq.shared_resources.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.close
import com.joohnq.shared_resources.error
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalSmall
import com.joohnq.shared_resources.theme.TextStyles
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SnackBarNotification(
    paddingTop: Dp = 0.dp,
    snackBarHostState: SnackbarHostState,
) {
    val scope = rememberCoroutineScope()

    SnackbarHost(
        hostState = snackBarHostState,
        modifier = Modifier
            .paddingHorizontalSmall(),
        snackbar = { snackBarData ->
            SnackBarUI(
                modifier = Modifier
                    .padding(top = paddingTop + 10.dp)
                    .fillMaxWidth(),
                message = snackBarData.visuals.message,
                onClick = {
                    scope.launch {
                        snackBarData.dismiss()
                    }
                }
            )
        }
    )
}

@Composable
fun SnackBarUI(
    modifier: Modifier = Modifier,
    message: String,
    onClick: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .border(
                width = 1.dp,
                color = Colors.Pink50,
                shape = Dimens.Shape.Circle
            )
            .background(color = Colors.Pink5, shape = Dimens.Shape.Circle)
            .padding(vertical = 5.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            enabled = false,
            onClick = onClick,
            colors = IconButtonColors(
                containerColor = Colors.Transparent,
                contentColor = Colors.Gray60,
                disabledContentColor = Colors.Gray60,
                disabledContainerColor = Colors.Transparent
            )
        ) {
            Icon(
                painter = painterResource(Drawables.Icons.Outlined.Warning),
                tint = Colors.Pink40,
                modifier = Modifier.size(20.dp),
                contentDescription = stringResource(Res.string.error)
            )
        }
        Text(
            text = message,
            color = Colors.Gray80,
            modifier = Modifier.weight(1f),
            style = TextStyles.textSmBold()
        )
        IconButton(
            onClick = onClick,
            colors = IconButtonColors(
                containerColor = Colors.Transparent,
                contentColor = Colors.Gray60,
                disabledContentColor = Colors.Gray60,
                disabledContainerColor = Colors.Transparent
            )
        ) {
            Icon(
                painter = painterResource(Drawables.Icons.Outlined.Close),
                tint = Colors.Gray60,
                modifier = Modifier.size(20.dp),
                contentDescription = stringResource(Res.string.close)
            )
        }
    }
}

@Preview
@Composable
fun SnackBarUIPreview() {
    SnackBarUI(
        modifier = Modifier.height(44.dp),
        message = "This is a snackbar message",
    )
}
