package com.joohnq.auth.impl.ui.presentation.avatar

import androidx.compose.ui.graphics.ImageBitmap
import com.joohnq.ui.UnidirectionalViewModel

sealed interface AvatarContract {
    interface ViewModel : UnidirectionalViewModel<State, Intent, SideEffect>

    sealed interface Intent {
        data class UpdateImageBitmap(val imageBitmap: ImageBitmap? = null) : Intent
        data class UpdateImageDrawableIndex(val i: Int) : Intent
    }

    sealed interface SideEffect

    data class State(
        val imageBitmap: ImageBitmap? = null,
        val selectedDrawableIndex: Int = 0
    )

    sealed interface Event {
        data object OnPickAvatar : Event
        data object OnContinue : Event
    }
}