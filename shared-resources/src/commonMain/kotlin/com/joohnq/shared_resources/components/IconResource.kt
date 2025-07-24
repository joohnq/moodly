package com.joohnq.shared_resources.components

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import com.joohnq.ui.entity.IconResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun IconResource(icon: IconResource) {
    Icon(
        painter = painterResource(icon.icon),
        contentDescription = stringResource(icon.contentDescription),
        tint = icon.tint,
        modifier = icon.modifier
    )
}