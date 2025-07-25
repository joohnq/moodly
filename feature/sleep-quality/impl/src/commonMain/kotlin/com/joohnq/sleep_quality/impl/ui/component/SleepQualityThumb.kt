package com.joohnq.sleep_quality.impl.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import org.jetbrains.compose.resources.painterResource

@Composable
fun SleepQualityThumb() {
    Box(
        modifier = Modifier
            .size(64.dp)
            .background(
                color = Colors.Orange40,
                shape = Dimens.Shape.Circle
            ).border(4.dp, color = Colors.Orange40Alpha25, shape = Dimens.Shape.Circle),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(Drawables.Icons.Outlined.Resize),
            contentDescription = null,
            modifier = Modifier.size(Dimens.Icon),
            tint = Colors.White
        )
    }
}