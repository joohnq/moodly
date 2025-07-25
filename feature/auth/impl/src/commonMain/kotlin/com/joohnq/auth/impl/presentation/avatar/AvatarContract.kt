package com.joohnq.auth.impl.presentation.avatar

import androidx.compose.ui.graphics.ImageBitmap

sealed interface AvatarContract {
    sealed interface Intent {
        data class UpdateImageBitmap(val imageBitmap: ImageBitmap? = null) : Intent
        data class UpdateImageDrawableIndex(val i: Int) : Intent
    }

    data class State(
        val imageBitmap: ImageBitmap? = null,
        val selectedDrawableIndex: Int = 0,
    )

    sealed interface Event {
        data object OnPickAvatar : Event
        data object OnContinue : Event
    }
}