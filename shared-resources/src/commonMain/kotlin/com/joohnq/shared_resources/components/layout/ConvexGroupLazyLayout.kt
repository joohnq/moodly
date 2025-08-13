package com.joohnq.shared_resources.components.layout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.components.AppTopBar
import com.joohnq.shared_resources.components.button.SmallAddButton
import com.joohnq.shared_resources.components.modifier.dpOffset
import com.joohnq.shared_resources.components.spacer.VerticalSpacer
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.PaddingModifier.paddingHorizontalMedium
import dev.chrisbanes.haze.ExperimentalHazeApi
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeSource
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalHazeApi::class)
@Composable
fun ConvexGroupLazyLayout(
    containerColor: Color = Color.White,
    panelBackgroundColor: Color = Colors.Brown10,
    isDark: Boolean,
    title: StringResource,
    image: DrawableResource,
    color: Color,
    onAddButton: () -> Unit,
    onGoBack: () -> Unit,
    panel: @Composable ColumnScope.(Modifier) -> Unit,
    body: @Composable ColumnScope.(Modifier) -> Unit,
) {
    val scrollState = rememberScrollState()
    val hazeState = remember { HazeState() }

    Scaffold(
        containerColor = containerColor,
        topBar = {
            AppTopBar(
                modifier =
                    Modifier
                        .hazeSource(hazeState)
                        .background(Colors.Brown5.copy(alpha = if (scrollState.value > 200) 0.8f else 0f))
                        .paddingHorizontalMedium()
                        .statusBarsPadding(),
                text = title,
                isDark = if (isDark) true else scrollState.value > 200,
                onGoBack = onGoBack
            )
        }
    ) { padding ->
        Column(
            Modifier
                .padding(bottom = padding.calculateBottomPadding().plus(50.dp))
                .verticalScroll(scrollState)
                .hazeSource(hazeState)
        ) {
            Box(
                modifier =
                    Modifier
                        .wrapContentSize()
                        .background(color = panelBackgroundColor)
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
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(modifier = Modifier.padding(top = padding.calculateTopPadding()))
                    VerticalSpacer(20.dp)
                    panel(Modifier.paddingHorizontalMedium())
                    VerticalSpacer(60.dp)
                    ConvexColumnLayout(
                        backgroundColor = Colors.White,
                        offset = 60.dp
                    ) {
                        SmallAddButton(
                            modifier = Modifier.dpOffset(y = (-58).dp),
                            onClick = onAddButton
                        )
                    }
                }
            }
            Column(
                modifier =
                    Modifier
                        .background(color = containerColor)
            ) {
                body(
                    Modifier
                        .paddingHorizontalMedium()
                )
            }
        }
    }
}

@Composable
fun ConvexGroupLazyLayout(
    containerColor: Color = Color.White,
    panelBackgroundColor: Color = Colors.Brown5,
    title: StringResource,
    isDark: Boolean = true,
    onAddButton: () -> Unit,
    onGoBack: () -> Unit,
    panel: @Composable ColumnScope.(Modifier) -> Unit,
    body: @Composable ColumnScope.(Modifier) -> Unit,
) {
    val scrollState = rememberScrollState()
    val hazeState = remember { HazeState() }

    Scaffold(
        contentWindowInsets = WindowInsets(top = 50.dp),
        containerColor = containerColor,
        topBar = {
            AppTopBar(
                modifier =
                    Modifier
                        .hazeSource(hazeState)
                        .background(Colors.Brown5.copy(alpha = if (scrollState.value > 200) 0.8f else 0f))
                        .paddingHorizontalMedium()
                        .statusBarsPadding(),
                text = title,
                isDark = isDark,
                onGoBack = onGoBack
            )
        }
    ) { padding ->
        Column(
            Modifier
                .padding(bottom = padding.calculateBottomPadding().plus(50.dp))
                .fillMaxWidth()
                .background(color = panelBackgroundColor)
                .verticalScroll(scrollState)
                .hazeSource(hazeState)
        ) {
            Box(modifier = Modifier.statusBarsPadding())
            VerticalSpacer(76.dp)
            panel(Modifier.paddingHorizontalMedium())
            VerticalSpacer(60.dp)
            ConvexColumnLayout(
                backgroundColor = Colors.White,
                offset = 60.dp
            ) {
                SmallAddButton(
                    modifier = Modifier.dpOffset(y = (-58).dp),
                    onClick = onAddButton
                )
            }
            Column(modifier = Modifier.background(color = containerColor)) {
                body(
                    Modifier
                        .paddingHorizontalMedium()
                )
            }
        }
    }
}
