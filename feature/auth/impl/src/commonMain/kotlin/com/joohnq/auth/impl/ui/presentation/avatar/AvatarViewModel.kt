package com.joohnq.auth.impl.ui.presentation.avatar

import androidx.lifecycle.viewModelScope
import com.joohnq.api.use_case.UpdateUserImageBitmapUseCase
import com.joohnq.api.use_case.UpdateUserImageDrawableUseCase
import com.joohnq.ui.BaseViewModel
import kotlinx.coroutines.launch

class AvatarViewModel(
    initialState: AvatarContract.State = AvatarContract.State(),
    private val updateUserImageBitmapUseCase: UpdateUserImageBitmapUseCase,
    private val updateUserImageDrawableUseCase: UpdateUserImageDrawableUseCase,
) : BaseViewModel<AvatarContract.State, AvatarContract.Intent, AvatarContract.SideEffect>(
        initialState = initialState
    ),
    AvatarContract.ViewModel {
    override fun onIntent(intent: AvatarContract.Intent) {
        when (intent) {
            is AvatarContract.Intent.ChangeImageBitmap ->
                updateState { it.copy(imageBitmap = intent.imageBitmap) }

            is AvatarContract.Intent.ChangeImageDrawableIndex ->
                updateState { it.copy(selectedDrawableIndex = intent.i) }

            AvatarContract.Intent.Action -> updateImage()
            is AvatarContract.Intent.ChangeImageSourceOptionDialog -> {
                updateState {
                    it.copy(
                        imageSourceOptionDialog = intent.value
                    )
                }
            }
            is AvatarContract.Intent.ChangeLaunchCamera -> {
                updateState {
                    it.copy(
                        launchCamera = intent.value
                    )
                }
            }
            is AvatarContract.Intent.ChangeLaunchGallery -> {
                updateState {
                    it.copy(
                        launchGallery = intent.value
                    )
                }
            }
            is AvatarContract.Intent.ChangeLaunchSetting -> {
                updateState {
                    it.copy(
                        launchSetting = intent.value
                    )
                }
            }
            is AvatarContract.Intent.ChangePermissionRationalDialog -> {
                updateState {
                    it.copy(
                        permissionRationalDialog = intent.value
                    )
                }
            }
        }
    }

    private fun updateImage() {
        viewModelScope.launch {
            try {
                if (state.value.imageBitmap != null) {
                    updateUserImageBitmapUseCase(state.value.imageBitmap!!)
                } else {
                    updateUserImageDrawableUseCase(state.value.selectedDrawableIndex)
                }

                emitEffect(AvatarContract.SideEffect.NavigateNext)
            } catch (e: Exception) {
                emitEffect(AvatarContract.SideEffect.ShowError(e.message.toString()))
            }
        }
    }
}
