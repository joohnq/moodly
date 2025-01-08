package com.joohnq.auth.ui.presentation.avatar.state

import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.ui.graphics.ImageBitmap
import com.joohnq.auth.ui.presentation.avatar.event.AvatarEvent
import org.jetbrains.compose.resources.DrawableResource

data class AvatarState(
    val snackBarState: SnackbarHostState,
    val pagerState: PagerState,
    val images: List<DrawableResource> = emptyList(),
    val onEvent: (AvatarEvent) -> Unit = {},
    val imageBitmap: ImageBitmap? = null,
)