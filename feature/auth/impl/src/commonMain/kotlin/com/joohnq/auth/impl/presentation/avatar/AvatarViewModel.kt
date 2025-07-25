package com.joohnq.auth.impl.presentation.avatar

import com.joohnq.ui.BaseViewModel

class AvatarViewModel(
    initialState: AvatarContract.State = AvatarContract.State(),
) : BaseViewModel<AvatarContract.State, AvatarContract.Intent, AvatarContract.SideEffect>(
    initialState = initialState
), AvatarContract.ViewModel {
    override fun onIntent(intent: AvatarContract.Intent) {
        when (intent) {
            is AvatarContract.Intent.UpdateImageBitmap ->
                updateState { it.copy(imageBitmap = intent.imageBitmap) }

            is AvatarContract.Intent.UpdateImageDrawableIndex ->
                updateState { it.copy(selectedDrawableIndex = intent.i) }
        }
    }
}