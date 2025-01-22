package com.joohnq.auth.ui.presentation.avatar.viewmodel

import androidx.compose.ui.graphics.ImageBitmap

sealed interface AvatarIntent {
    data class UpdateImageBitmap(val imageBitmap: ImageBitmap? = null) : AvatarIntent
    data class UpdateImageDrawableIndex(val i: Int) : AvatarIntent
}