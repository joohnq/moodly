package com.joohnq.moodapp.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.view.ui.Colors
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

@Composable fun <T> SharedPanelComponent(
    isDark: Boolean,
    onGoBack: () -> Unit,
    backgroundColor: Color,
    backgroundImage: DrawableResource,
    panelTitle: StringResource,
    bodyTitle: StringResource,
    color: Color,
    onAdd: () -> Unit,
    items: List<T>,
    panelContent: @Composable (PaddingValues) -> Unit,
    content: @Composable (T) -> Unit,
) {
    Scaffold(
        containerColor = Colors.Brown10,
    ) { scaffoldPadding ->
        val padding = PaddingValues(
            top = scaffoldPadding.calculateTopPadding(),
            bottom = scaffoldPadding.calculateBottomPadding() + 100.dp,
            start = scaffoldPadding.calculateStartPadding(LayoutDirection.Ltr),
            end = scaffoldPadding.calculateEndPadding(LayoutDirection.Rtl)
        )
        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            item {
                Column {
                    PanelContent(
                        isDark = isDark,
                        padding = padding,
                        text = panelTitle,
                        backgroundColor = backgroundColor,
                        background = backgroundImage,
                        color = color,
                        onAdd = onAdd,
                        onGoBack = onGoBack,
                        content = { panelContent(padding) }
                    )
                    VerticalSpacer(20.dp)
                    Title(bodyTitle)
                }
            }
            items(items) { content(it) }
            item {
                VerticalSpacer(20.dp)
            }
        }
    }
}

@Composable fun SharedItem(
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
        containerColor = Colors.Brown10,
    ) { scaffoldPadding ->
        val padding = PaddingValues(
            top = scaffoldPadding.calculateTopPadding(),
            bottom = scaffoldPadding.calculateBottomPadding() + 100.dp,
            start = scaffoldPadding.calculateStartPadding(LayoutDirection.Ltr),
            end = scaffoldPadding.calculateEndPadding(LayoutDirection.Rtl)
        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            item {
                Column {
                    PanelContent(
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
                HorizontalSpacer(20.dp)
            }
        }
    }
}