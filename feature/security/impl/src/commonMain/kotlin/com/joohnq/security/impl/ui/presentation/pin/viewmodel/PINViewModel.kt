package com.joohnq.security.impl.ui.presentation.pin.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class PINViewModel : ViewModel() {
    private val _state: MutableStateFlow<PINState> =
        MutableStateFlow(PINState())
    val state: StateFlow<PINState> = _state

    fun onAction(intent: PINIntent) {
        when (intent) {
            is PINIntent.OnChangeFieldFocused -> {
                _state.update {
                    it.copy(
                        focusedIndex = intent.index
                    )
                }
            }

            is PINIntent.OnEnterNumber -> {
                enterNumber(index = intent.index, number = intent.number)
            }

            PINIntent.OnKeyboardBack -> {
                val previousIndex = getPreviousFocusedIndex(state.value.focusedIndex)
                _state.update {
                    it.copy(
                        code = it.code.mapIndexed { i, number ->
                            if (i == previousIndex) null else number
                        },
                        focusedIndex = previousIndex
                    )
                }
            }
        }
    }

    private fun enterNumber(index: Int, number: Int?) {
        val newCode = state.value.code.mapIndexed { currentIndex, currentNumber ->
            if (currentIndex == index) number else currentNumber
        }
        val wasNumberRemoved = number == null
        _state.update {
            it.copy(
                code = newCode,
                focusedIndex = if (wasNumberRemoved || it.code.getOrNull(index) != null) {
                    it.focusedIndex
                } else {
                    getNextFocusedIndex(
                        currentCode = it.code,
                        currentFocusedIndex = it.focusedIndex
                    )
                }

            )
        }
    }

    private fun getPreviousFocusedIndex(index: Int?): Int? =
        index?.minus(1)?.coerceAtLeast(0)

    private fun getNextFocusedIndex(currentCode: List<Int?>, currentFocusedIndex: Int?): Int? {
        if (currentFocusedIndex == null) return null
        if (currentFocusedIndex == 3) return currentFocusedIndex
        return getFirstEmptyFieldIndexAfterFocusedIndex(currentCode, currentFocusedIndex)
    }


    private fun getFirstEmptyFieldIndexAfterFocusedIndex(
        code: List<Int?>,
        currentFocusedIndex: Int,
    ): Int {
        code.forEachIndexed { i, number ->
            if (i <= currentFocusedIndex) return@forEachIndexed
            if (number == null) return i
        }
        return currentFocusedIndex
    }
}