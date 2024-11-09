package com.joohnq.moodapp.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.view.constants.Colors
import com.joohnq.moodapp.view.constants.Drawables
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun PanelContentDark(
    padding: PaddingValues,
    text: StringResource,
    backgroundColor: Color,
    background: DrawableResource,
    color: Color,
    onAdd: () -> Unit,
    onGoBack: () -> Unit,
    content: @Composable () -> Unit
) {
    BoxWithConstraints {
        val height = maxHeight * 0.5f

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
                .background(color = backgroundColor)
        ) {
            Image(
                painter = painterResource(background),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.tint(color = color),
                modifier = Modifier.fillMaxSize()
            )

            Box(
                contentAlignment = Alignment.TopCenter,
                modifier = Modifier.fillMaxSize()
            ) {
                TopBarDark(
                    modifier = Modifier
                        .padding(top = padding.calculateTopPadding())
                        .padding(horizontal = 20.dp),
                    text = text,
                    onGoBack = onGoBack
                )
            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                content()
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .absoluteOffset(y = 40.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                IconButton(
                    onClick = onAdd,
                    modifier = Modifier
                        .size(80.dp)
                        .background(color = Colors.Brown80, shape = CircleShape)
                ) {
                    Icon(
                        painter = painterResource(Drawables.Icons.Add),
                        contentDescription = null,
                        tint = Colors.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun PanelContentLight(
    padding: PaddingValues,
    text: StringResource,
    backgroundColor: Color,
    background: DrawableResource,
    color: Color,
    onAdd: () -> Unit,
    onGoBack: () -> Unit,
    content: @Composable () -> Unit
) {
    BoxWithConstraints {
        val height = maxHeight * 0.5f

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
                .background(color = backgroundColor)
        ) {
            Image(
                painter = painterResource(background),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.tint(color = color),
                modifier = Modifier.fillMaxSize()
            )

            Box(
                contentAlignment = Alignment.TopCenter,
                modifier = Modifier.fillMaxSize()
            ) {
                TopBarLight(
                    modifier = Modifier
                        .padding(top = padding.calculateTopPadding())
                        .padding(horizontal = 20.dp),
                    text = text,
                    onGoBack = onGoBack
                )
            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                content()
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .absoluteOffset(y = 40.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                IconButton(
                    onClick = onAdd,
                    modifier = Modifier
                        .size(80.dp)
                        .background(color = Colors.Brown80, shape = CircleShape)
                ) {
                    Icon(
                        painter = painterResource(Drawables.Icons.Add),
                        contentDescription = null,
                        tint = Colors.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}
