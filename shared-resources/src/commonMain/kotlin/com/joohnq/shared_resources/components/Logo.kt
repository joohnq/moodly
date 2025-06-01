package com.joohnq.shared_resources.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.logo
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun Logo(
    modifier: Modifier = Modifier,
    tint: Color
) {
    Image(
        painter = painterResource(Drawables.Icons.Filled.Logo),
        contentDescription = stringResource(Res.string.logo),
        modifier = modifier,
        colorFilter = ColorFilter.tint(tint)
    )
}