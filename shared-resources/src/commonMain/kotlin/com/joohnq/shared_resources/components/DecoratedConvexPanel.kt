package com.joohnq.shared_resources.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.theme.Colors

@Composable
fun DecoratedConvexPanel(
    panelBackgroundColor: Color,
    backgroundColor: Color = Colors.Brown10,
    panelContent: @Composable () -> Unit,
    content: @Composable (ColumnScope) -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize().background(color = backgroundColor)
    ) {
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
