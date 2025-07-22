package com.joohnq.auth.impl.presentation.avatar.viewmodel

import androidx.compose.ui.graphics.ImageBitmap

data class AvatarState(
    val imageBitmap: ImageBitmap? = null,
    val selectedDrawableIndex: Int = 0,
)