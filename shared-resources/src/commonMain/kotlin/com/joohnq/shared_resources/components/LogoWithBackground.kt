package com.joohnq.shared_resources.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.logo
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun LogoWithBackground() {
    Box(
        modifier = Modifier.size(64.dp)
            .background(color = Colors.Brown80, shape = Dimens.Shape.Circle),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(Drawables.Icons.Filled.Logo),
            contentDescription = stringResource(Res.string.logo),
            modifier = Modifier.size(32.dp),
            colorFilter = ColorFilter.tint(Colors.White)
        )
    }
}