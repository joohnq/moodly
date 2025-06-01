package com.joohnq.auth.ui.presentation.avatar.viewmodel

import androidx.compose.ui.graphics.ImageBitmap

sealed interface AvatarContract {
    sealed interface Event : AvatarContract {
        data object Continue : Event
        data object PickAvatar : Event
    }

    sealed interface Intent : AvatarContract {
        data class UpdateImageBitmap(val imageBitmap: ImageBitmap? = null) : Intent
        data class UpdateImageDrawableIndex(val i: Int) : Intent
    }

    data class State(val imageBitmap: ImageBitmap? = null, val selectedDrawableIndex: Int = 0) : AvatarContract
}