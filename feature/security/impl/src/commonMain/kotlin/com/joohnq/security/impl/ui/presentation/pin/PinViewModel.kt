package com.joohnq.security.impl.ui.presentation.pin

import com.joohnq.ui.BaseViewModel

class PinViewModel(
    initialState: PinContract.State = PinContract.State(),
) : BaseViewModel<PinContract.State, PinContract.Intent, PinContract.SideEffect>(
        initialState = initialState
    ),
    PinContract.ViewModel {
    override fun onIntent(intent: PinContract.Intent) {
        when (intent) {
            is PinContract.Intent.OnChangeFieldFocused ->
                updateState {
                    it.copy(
                        focusedIndex = intent.index
                    )
                }

            is PinContract.Intent.OnEnterNumber ->
                enterNumber(index = intent.index, number = intent.number)

            PinContract.Intent.OnKeyboardBack ->
                onKeyboardBack()
        }
    }

    private fun onKeyboardBack() {
        val previousIndex = getPreviousFocusedIndex(state.value.focusedIndex)

        updateState {
            it.copy(
                code =
                    it.code.mapIndexed { i, number ->
                        if (i == previousIndex) null else number
                    },
                focusedIndex = previousIndex
            )
        }
    }

    private fun enterNumber(
        index: Int,
        number: Int?,
    ) {
        val newCode =
            state.value.code.mapIndexed { currentIndex, currentNumber ->
                if (currentIndex == index) number else currentNumber
            }
        val wasNumberRemoved = number == null
        val focusedIndex =
            if (wasNumberRemoved || state.value.code.getOrNull(index) != null) {
                state.value.focusedIndex
            } else {
                getNextFocusedIndex(
                    currentCode = state.value.code,
                    currentFocusedIndex = state.value.focusedIndex
                )
            }

        updateState {
            it.copy(
                code = newCode,
                focusedIndex = focusedIndex
            )
        }
    }

    private fun getPreviousFocusedIndex(index: Int?): Int? = index?.minus(1)?.coerceAtLeast(0)

    private fun getNextFocusedIndex(
        currentCode: List<Int?>,
        currentFocusedIndex: Int?,
    ): Int? {
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
