package com.joohnq.shared_resources.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.PanelEvent
import com.joohnq.shared_resources.dpOffset
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

@Composable
fun SharedPanelComponent(
    containerColor: Color = Colors.Brown10,
    paddingValues: PaddingValues,
    isDark: Boolean,
    backgroundColor: Color,
    image: DrawableResource,
    title: StringResource,
    color: Color,
    onEvent: (PanelEvent) -> Unit,
    topBar: @Composable () -> Unit = { },
    panel: @Composable () -> Unit,
    content: @Composable () -> Unit,
) {
    Scaffold(
        containerColor = containerColor,
    ) { padding ->
        Column(
            Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = backgroundColor)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                )
                VerticalSpacer(paddingValues.calculateTopPadding() + 10.dp)
                TopBar(
                    modifier = Modifier
                        .padding(top = padding.calculateBottomPadding())
                        .paddingHorizontalMedium(),
                    isDark = isDark,
                    text = title,
                    onGoBack = { onEvent(PanelEvent.OnGoBack) },
                    content = topBar
                )
                VerticalSpacer(20.dp)
                panel()
                VerticalSpacer(60.dp)
//                ConvexContentLayout(
//                    backgroundColor = containerColor,
//                ) {
//                    SmallAddButton(
//                        modifier = Modifier.dpOffset(y = (-88).dp),
//                        onClick = { onEvent(PanelEvent.OnAdd) }
//                    )
//                }
            }
            content()
        }
    }
}

@Composable
fun SharedPanelComponent(
    containerColor: Color = Colors.Brown10,
    paddingValues: PaddingValues,
    isDark: Boolean,
    backgroundColor: Color,
    onEvent: (PanelEvent) -> Unit,
    topBar: @Composable () -> Unit = { },
    panel: @Composable () -> Unit,
    content: @Composable (Modifier) -> Unit,
) {
    Scaffold(
        containerColor = containerColor,
    ) { padding ->
        Column(
            Modifier
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = backgroundColor)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                )
                VerticalSpacer(paddingValues.calculateTopPadding() + 10.dp)
                TopBar(
                    modifier = Modifier
                        .padding(top = padding.calculateBottomPadding())
                        .paddingHorizontalMedium(),
                    isDark = isDark,
                    onGoBack = { onEvent(PanelEvent.OnGoBack) },
                    content = topBar
                )
                VerticalSpacer(20.dp)
                panel()
                VerticalSpacer(60.dp)
                ConvexContentLayout(
                    backgroundColor = containerColor,
                ) {
                    SmallAddButton(
                        modifier = Modifier.dpOffset(y = (-88).dp),
                        onClick = { onEvent(PanelEvent.OnAdd) }
                    )
                }
            }
            content(Modifier.paddingHorizontalMedium())
        }
    }
}