package com.joohnq.auth.ui.presentation.avatar.viewmodel

import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class AvatarViewModel : ViewModel() {
    private val _state: MutableStateFlow<AvatarState> =
        MutableStateFlow(AvatarState())
    val state: StateFlow<AvatarState> = _state

    fun onAction(intent: AvatarIntent) {
        when (intent) {
            is AvatarIntent.UpdateImageBitmap -> updateImageBitmap(intent.imageBitmap)
            is AvatarIntent.UpdateImageDrawableIndex -> updateImageDrawableIndex(intent.i)
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