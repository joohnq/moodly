package com.joohnq.security.impl.ui.presentation.unlock

import androidx.lifecycle.viewModelScope
import com.joohnq.security.api.use_case.GetSecurityUseCase
import com.joohnq.ui.BaseViewModel
import kotlinx.coroutines.launch

class UnLockViewModel(
    private val getSecurityUseCase: GetSecurityUseCase,
    private val initialState: UnlockContract.State = UnlockContract.State(),
) : BaseViewModel<UnlockContract.State, UnlockContract.Intent, UnlockContract.SideEffect>(
        initialState = initialState
    ),
    UnlockContract.ViewModel {
    override fun onIntent(intent: UnlockContract.Intent) {
        when (intent) {
            UnlockContract.Intent.Init -> init()
            UnlockContract.Intent.Action -> action()
            is UnlockContract.Intent.OnChangeFieldFocused -> {
                updateState {
                    it.copy(
                        focusedIndex = intent.index
                    )
                }
            }

            is UnlockContract.Intent.OnEnterNumber -> {
                if (intent.number != null) {
                    state.value.focusRequesters.mapIndexed { i, focusRequester ->
                        if (i == intent.index) focusRequester.freeFocus() else null
                    }
                }

                enterNumber(index = intent.index, number = intent.number)
            }

            UnlockContract.Intent.OnKeyboardBack ->
                onKeyboardBack()

            is UnlockContract.Intent.UpdateShowBottomSheet -> {
                updateState {
                    it.copy(
                        showBottomSheet = intent.value
                    )
                }
            }

            is UnlockContract.Intent.UpdateIsError -> {
                updateState {
                    it.copy(
                        isError = intent.error
                    )
                }
            }

            is UnlockContract.Intent.RequestFocus -> {
                state.value.focusRequesters.mapIndexed { i, focusRequester ->
                    if (i == intent.index) focusRequester.requestFocus() else null
                }
            }
        }
    }

    private fun action() {
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

    private fun init() {
        viewModelScope.launch {
            try {
                val security = getSecurityUseCase().getOrThrow()

                updateState { it.copy(security = security) }
            } catch (e: Exception) {
                emitEffect(UnlockContract.SideEffect.ShowError(e.message.toString()))
            }
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
                focusedIndex = focusedIndex,
                isError = null
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
