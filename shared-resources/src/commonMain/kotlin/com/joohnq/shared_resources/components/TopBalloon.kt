package com.joohnq.shared_resources.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import org.jetbrains.compose.resources.painterResource

@Composable
fun BoxWithConstraintsScope.TopBalloon(
    backgroundColor: Color,
    iconColor: Color,
) {
    Box(
        modifier = Modifier
            .width(maxWidth)
            .scale(1.2f)
            .height(0.dp)
            .aspectRatio(1f)
            .background(
                color = backgroundColor,
                shape = Dimens.Shape.Circle
            )
            .padding(30.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Icon(
            painter = painterResource(Drawables.Icons.Filled.Logo),
            contentDescription = null,
            modifier = Modifier.size(48.dp),
            tint = iconColor
        )
    }
}