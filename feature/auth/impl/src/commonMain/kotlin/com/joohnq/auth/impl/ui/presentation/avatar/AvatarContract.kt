package com.joohnq.auth.impl.ui.presentation.avatar

import androidx.compose.ui.graphics.ImageBitmap
import com.joohnq.ui.UnidirectionalViewModel

sealed interface AvatarContract {
    interface ViewModel : UnidirectionalViewModel<State, Intent, SideEffect>

    sealed interface Intent {
        data class ChangeImageBitmap(
            val imageBitmap: ImageBitmap? = null,
        ) : Intent

        data object Action : Intent

        data class ChangeImageDrawableIndex(
            val i: Int,
        ) : Intent

        data class ChangeImageSourceOptionDialog(
            val value: Boolean,
        ) : Intent

        data class ChangePermissionRationalDialog(
            val value: Boolean,
        ) : Intent

        data class ChangeLaunchCamera(
            val value: Boolean,
        ) : Intent

        data class ChangeLaunchGallery(
            val value: Boolean,
        ) : Intent

        data class ChangeLaunchSetting(
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
}