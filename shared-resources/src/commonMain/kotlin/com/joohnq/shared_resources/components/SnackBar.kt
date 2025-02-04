package com.joohnq.shared_resources.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens

@Composable
fun SnackBarUI(
    padding: PaddingValues,
    snackBarHostState: SnackbarHostState,
) {
    SnackbarHost(
        hostState = snackBarHostState,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
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
                        .padding(8.dp)
                ) {
                    Text(text = snackBarData.visuals.message, color = Colors.Brown80)
                }
            }
        }
    )
}
