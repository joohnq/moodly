package com.joohnq.shared_resources.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.theme.Colors
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

@Composable fun SharedPanelComponent(
    containerColor: Color = Colors.Brown10,
    isDark: Boolean,
    onGoBack: () -> Unit,
    backgroundColor: Color,
    backgroundImage: DrawableResource,
    panelTitle: StringResource,
    bodyTitle: StringResource,
    color: Color,
    onAdd: () -> Unit,
    topBarContent: (@Composable () -> Unit)? = null,
    panelContent: @Composable (PaddingValues) -> Unit,
    content: LazyListScope.() -> Unit,
) {
    Scaffold(
        containerColor = containerColor,
    ) { padding ->
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            item {
                Column {
                    PanelContent(
                        modifier = Modifier.fillMaxWidth()
                            .fillParentMaxHeight(0.5f),
                        isDark = isDark,
                        padding = padding,
                        text = panelTitle,
                        backgroundColor = backgroundColor,
                        background = backgroundImage,
                        color = color,
                        onAdd = onAdd,
                        onGoBack = onGoBack,
                        content = { panelContent(padding) },
                        topBarContent = topBarContent
                    )
                    VerticalSpacer(20.dp)
                    Title(bodyTitle)
                }
            }
            content()
            item {
                VerticalSpacer(20.dp)
            }
        }
    }
}