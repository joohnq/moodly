package com.joohnq.auth.ui.presentation.avatar

import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.ViewModel
import com.joohnq.auth.ui.presentation.avatar.AvatarContract
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class AvatarViewModel : ViewModel() {
    private val _state: MutableStateFlow<AvatarContract.State> =
        MutableStateFlow(AvatarContract.State())
    val state: StateFlow<AvatarContract.State> = _state

    fun onIntent(intent: AvatarContract.Intent) {
        when (intent) {
            is AvatarContract.Intent.UpdateImageBitmap -> updateImageBitmap(intent.imageBitmap)
            is AvatarContract.Intent.UpdateImageDrawableIndex -> updateImageDrawableIndex(intent.i)
        }
    }

    private fun updateImageBitmap(imageBitmap: ImageBitmap?) {
        _state.update {
            it.copy(imageBitmap = imageBitmap)
        }
    }

    private fun updateImageDrawableIndex(i: Int) {
        _state.update {
            it.copy(selectedDrawableIndex = i)
        }
    }
}