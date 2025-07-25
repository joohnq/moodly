package com.joohnq.shared_resources.components.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.components.AppTopBar
import com.joohnq.shared_resources.components.spacer.VerticalSpacer
import com.joohnq.shared_resources.components.button.SmallAddButton
import com.joohnq.shared_resources.components.modifier.dpOffset
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun ConvexGroupLazyLayout(
    containerColor: Color = Color.White,
    panelBackgroundColor: Color = Colors.Brown10,
    isDark: Boolean = true,
    image: DrawableResource,
    color: Color,
    onAddButton: () -> Unit,
    onGoBack: () -> Unit,
    panel: @Composable ColumnScope.(Modifier) -> Unit,
    body: @Composable ColumnScope.(Modifier) -> Unit,
) {
    Scaffold(
        containerColor = containerColor,
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(bottom = padding.calculateBottomPadding()),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item {
                Column(
                    Modifier
                        .background(color = containerColor)
                ) {
                    Box(
                        modifier = Modifier.wrapContentSize()
                            .background(color = panelBackgroundColor),
                    ) {
                        Image(
                            painter = painterResource(image),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.matchParentSize(),
                            colorFilter = ColorFilter.tint(color = color),
                            alignment = Alignment.TopCenter
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            VerticalSpacer(padding.calculateTopPadding())
                            AppTopBar(
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
                            ConvexColumnLayout(
                                backgroundColor = Colors.White,
                                offset = 60.dp
                            ) {
                                SmallAddButton(
                                    modifier = Modifier.Companion.dpOffset(y = (-58).dp),
                                    onClick = onAddButton
                                )
                            }
                        }
                    }
                }
            }
            item {
                Column {
                    body(Modifier.paddingHorizontalMedium())
                }
            }
        }
    }
}