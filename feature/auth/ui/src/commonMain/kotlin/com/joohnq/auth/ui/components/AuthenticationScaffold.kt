package com.joohnq.auth.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import com.joohnq.domain.entity.CurvedCanvasPosition
import com.joohnq.shared_resources.components.CurvedCanvas
import com.joohnq.shared_resources.components.SnackBarNotification
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalLarge

enum class AuthenticationOrientation {
    TOP, BOTTOM
}

fun Modifier.weightIf(columnScope: ColumnScope, condition: Boolean, weight: Float): Modifier {
    val modifier = this
    with(columnScope) {
        return if (condition) modifier.weight(weight) else modifier
    }
}

@Composable
fun AuthenticationScaffold(
    backgroundColor: Color,
    containerColor: Color,
    snackBarHostState: SnackbarHostState,
    orientation: AuthenticationOrientation,
    topContent: @Composable ColumnScope.() -> Unit,
    bottomContent: @Composable ColumnScope.() -> Unit,
) {
    Scaffold { padding ->
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .background(color = backgroundColor)
        ) {
            val scrollState = rememberScrollState()

            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .fillMaxWidth()
                    .heightIn(min = maxHeight)
                    .background(backgroundColor),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                VerticalSpacer(padding.calculateTopPadding())

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(backgroundColor)
                        .padding(top = 12.dp)
                ) {
                    topContent()
                }

                Column {
                    Box(modifier = Modifier.fillMaxWidth().height(100.dp)) {
                        CurvedCanvas(
                            modifier = Modifier.fillMaxSize(),
                            position = CurvedCanvasPosition.TOP,
                            backgroundColor = containerColor
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Colors.White)
                            .paddingHorizontalLarge()
                            .padding(vertical = 20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        bottomContent()
                    }
                }
            }

            SnackBarNotification(
                paddingTop = padding.calculateTopPadding(),
                snackBarHostState = snackBarHostState,
            )
        }
    }
}

