package com.joohnq.mood.components

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import com.joohnq.domain.entity.Icon
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable fun Icon(icon: Icon) {
    Icon(
        painter = painterResource(icon.icon),
        contentDescription = stringResource(icon.contentDescription),
        tint = icon.tint,
        modifier = icon.modifier
    )
}