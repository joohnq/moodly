package com.joohnq.shared_resources.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.close
import com.joohnq.shared_resources.error
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SnackBarUI(
    padding: PaddingValues,
    snackBarHostState: SnackbarHostState,
    onDismiss: () -> Unit = {}
) {
    val scope = rememberCoroutineScope()
    SnackbarHost(
        hostState = snackBarHostState,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp, vertical = 10.dp),
        snackbar = { snackBarData ->
            Column(modifier = Modifier.fillMaxWidth()) {
                VerticalSpacer(padding.calculateTopPadding())
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = Colors.Pink40,
                            shape = Dimens.Shape.Circle
                        )
                        .background(color = Colors.Pink5, shape = Dimens.Shape.Circle)
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(Drawables.Icons.Outlined.Warning),
                        tint = Colors.Pink40,
                        modifier = Modifier.size(24.dp),
                        contentDescription = stringResource(Res.string.error)
                    )
                    Text(
                        text = snackBarData.visuals.message,
                        color = Colors.Brown80,
                        modifier = Modifier.weight(1f)
                    )
                    IconButton(
                        onClick = {
                            scope.launch {
                                snackBarData.dismiss()
                            }
                        },
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
                            modifier = Modifier.size(24.dp),
                            contentDescription = stringResource(Res.string.close)
                        )
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun SnackBarUIPreview() {
    val snackBarHostState = rememberSnackBarState()
    SnackBarUI(
        padding = PaddingValues(),
        snackBarHostState = snackBarHostState
    )

    LaunchedEffect(Unit) {
        snackBarHostState.showSnackbar("dawdawdawdawdawda")
    }
}
