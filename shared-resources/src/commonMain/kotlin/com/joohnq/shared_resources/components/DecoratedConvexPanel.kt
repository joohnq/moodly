package com.joohnq.shared_resources.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import org.jetbrains.compose.resources.StringResource

@Composable
fun DecoratedConvexPanel(
    panelBackgroundColor: Color,
    backgroundColor: Color = Colors.Brown10,
    panelContent: @Composable () -> Unit,
    content: @Composable (ColumnScope) -> Unit,
) {
    Scaffold(containerColor = backgroundColor) {
        Column(
            modifier = Modifier
                .padding(bottom = 20.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.6f)
                        .background(color = panelBackgroundColor),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    panelContent()
                    ConvexContentLayout(
                        backgroundColor = backgroundColor,
                    )
                }
                Column(
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                    content(this)
                }
            }
        }
    }
}

@Composable
fun DecoratedConvexPanelList(
    containerColor: Color = Color.White,
    panelBackgroundColor: Color = Colors.Brown10,
    isDark: Boolean = true,
    title: StringResource,
    onAddButton: () -> Unit,
    onGoBack: () -> Unit,
    panel: @Composable (Modifier) -> Unit,
    content: LazyListScope.() -> Unit,
) {
    Scaffold(
        containerColor = containerColor,
    ) { padding ->
        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            item {
                Column(
                    Modifier
                        .padding(padding)
                        .background(color = containerColor)
                        .fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = panelBackgroundColor)
                    ) {
                        TopBar(
                            modifier = Modifier
                                .paddingHorizontalMedium(),
                            isDark = isDark,
                            onGoBack = onGoBack,
                        )
                        VerticalSpacer(20.dp)
                        panel(
                            Modifier
                                .paddingHorizontalMedium()
                        )
                        VerticalSpacer(60.dp)
                        ConvexContentLayout(
                            backgroundColor = Colors.White,
                            offset = 60.dp
                        ) {
                            SmallAddButton(
                                modifier = Modifier.dpOffset(y = (-58).dp),
                                onClick = onAddButton
                            )
                        }
                    }
                    VerticalSpacer(20.dp)
                    Title(
                        modifier = Modifier.paddingHorizontalMedium(),
                        text = title
                    )
                }
            }
            content()
        }
    }
}

@Composable
fun DecoratedConvexPanelList(
    containerColor: Color = Color.White,
    panelBackgroundColor: Color = Colors.Brown10,
    isDark: Boolean = true,
    onAddButton: () -> Unit,
    onGoBack: () -> Unit,
    panel: @Composable ColumnScope.(Modifier) -> Unit,
    content: @Composable ColumnScope.(Modifier) -> Unit,
) {
    Scaffold(
        containerColor = containerColor,
    ) { padding ->
        Column(
            Modifier
                .padding(padding)
                .background(color = containerColor)
//                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = panelBackgroundColor)
            ) {
                TopBar(
                    modifier = Modifier
                        .paddingHorizontalMedium(),
                    isDark = isDark,
                    onGoBack = onGoBack,
                )
                VerticalSpacer(20.dp)
                panel(
                    Modifier.paddingHorizontalMedium()
                )
                VerticalSpacer(60.dp)
                ConvexContentLayout(
                    backgroundColor = Colors.White,
                    offset = 60.dp
                ) {
                    SmallAddButton(
                        modifier = Modifier.dpOffset(y = (-58).dp),
                        onClick = onAddButton
                    )
                }
            }
            content(Modifier.paddingHorizontalMedium())
        }
    }
}

@Composable
fun DecoratedConvexPanelItem(
    containerColor: Color = Color.White,
    panelBackgroundColor: Color = Colors.Brown10,
    isDark: Boolean = true,
    title: StringResource,
    onAddButton: () -> Unit,
    onGoBack: () -> Unit,
    panel: @Composable (Modifier) -> Unit,
    content: @Composable LazyItemScope.() -> Unit,
) {
    Scaffold(
        containerColor = containerColor,
    ) { padding ->
        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            item {
                Column(
                    Modifier
                        .padding(padding)
                        .background(color = containerColor)
                        .fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = panelBackgroundColor)
                    ) {
                        TopBar(
                            modifier = Modifier
                                .paddingHorizontalMedium(),
                            isDark = isDark,
                            onGoBack = onGoBack,
                        )
                        VerticalSpacer(20.dp)
                        panel(
                            Modifier
                                .paddingHorizontalMedium()
                        )
                        VerticalSpacer(60.dp)
                        ConvexContentLayout(
                            backgroundColor = Colors.White,
                            offset = 60.dp
                        ) {
                            SmallAddButton(
                                modifier = Modifier.dpOffset(y = (-58).dp),
                                onClick = onAddButton
                            )
                        }
                    }
                    VerticalSpacer(20.dp)
                    Title(
                        modifier = Modifier.paddingHorizontalMedium(),
                        text = title
                    )
                }
            }
            item {
                content()
            }
        }
    }
}

