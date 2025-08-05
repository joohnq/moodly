package com.joohnq.auth.impl.ui.presentation.avatar

import androidx.compose.ui.graphics.ImageBitmap
import com.joohnq.ui.UnidirectionalViewModel

sealed interface AvatarContract {
    interface ViewModel : UnidirectionalViewModel<State, Intent, SideEffect>

    sealed interface Intent {
        data class UpdateImageBitmap(
            val imageBitmap: ImageBitmap? = null,
        ) : Intent

        data object UpdateImage : Intent

        data class UpdateImageDrawableIndex(
            val i: Int,
        ) : Intent

        data class UpdateImageSourceOptionDialog(
            val value: Boolean,
        ) : Intent

        data class UpdatePermissionRationalDialog(
            val value: Boolean,
        ) : Intent

        data class UpdateLaunchCamera(
            val value: Boolean,
        ) : Intent

        data class UpdateLaunchGallery(
            val value: Boolean,
        ) : Intent

        data class UpdateLaunchSetting(
            val value: Boolean,
        ) : Intent
    }

    sealed interface SideEffect {
        data object NavigateNext : SideEffect

        data class ShowError(
            val message: String,
        ) : SideEffect
    }

    data class State(
        val imageBitmap: ImageBitmap? = null,
        val selectedDrawableIndex: Int = 0,
        val launchCamera: Boolean = false,
        val launchGallery: Boolean = false,
        val launchSetting: Boolean = false,
        val imageSourceOptionDialog: Boolean = false,
        val permissionRationalDialog: Boolean = false,
    )

    sealed interface Event {
        data object OnPickAvatar : Event

        data object OnContinue : Event
    }
}
