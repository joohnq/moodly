package com.joohnq.shared_resources.components.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens

@Composable
fun ProfileImage(
    modifier: Modifier = Modifier,
    painter: Painter,
) {
    Image(
        painter = painter,
        contentDescription = null,
        modifier =
            modifier
                .background(color = Colors.White, shape = Dimens.Shape.Circle)
                .clip(Dimens.Shape.Circle),
        contentScale = ContentScale.Crop,
        alignment = Alignment.Center
    )
}
