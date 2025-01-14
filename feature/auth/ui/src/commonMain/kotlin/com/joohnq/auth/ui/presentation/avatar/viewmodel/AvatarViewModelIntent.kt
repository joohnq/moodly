package com.joohnq.auth.ui.presentation.avatar.viewmodel

import androidx.compose.ui.graphics.ImageBitmap

sealed class AvatarViewModelIntent {
    data class UpdateImageBitmap(val imageBitmap: ImageBitmap? = null) : AvatarViewModelIntent()
    data class UpdateImageDrawableIndex(val i: Int) : AvatarViewModelIntent()
}