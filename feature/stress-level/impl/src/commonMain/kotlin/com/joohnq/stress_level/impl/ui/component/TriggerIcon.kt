package com.joohnq.stress_level.impl.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun TriggerIcon(
    modifier: Modifier = Modifier,
    icon: DrawableResource
) {
    Box(
        modifier = modifier
            .size(40.dp)
            .border(
                width = 1.dp,
                color = Colors.Gray30,
                shape = Dimens.Shape.Circle
            )
            .clip(Dimens.Shape.Circle),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            tint = Colors.Gray80,
            modifier = Modifier.size(24.dp)
        )
    }
}