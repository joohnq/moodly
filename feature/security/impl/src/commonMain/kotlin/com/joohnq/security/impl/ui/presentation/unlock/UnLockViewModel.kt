package com.joohnq.security.impl.ui.presentation.unlock

import androidx.lifecycle.viewModelScope
import com.joohnq.security.api.Security
import com.joohnq.security.api.getPinCode
import com.joohnq.security.api.use_case.GetSecurityUseCase
import com.joohnq.security.impl.PinHelper
import com.joohnq.ui.BaseViewModel
import kotlinx.coroutines.launch

class UnLockViewModel(
    private val getSecurityUseCase: GetSecurityUseCase,
    private val pinHelper: PinHelper,
    initialState: UnlockContract.State = UnlockContract.State(),
) : BaseViewModel<UnlockContract.State, UnlockContract.Intent, UnlockContract.SideEffect>(
        initialState = initialState
    ),
    UnlockContract.ViewModel {
    override fun onIntent(intent: UnlockContract.Intent) {
        when (intent) {
            is UnlockContract.Intent.ChangeFieldFocused -> {
                updateState {
                    it.copy(
                        focusedIndex = intent.index
                    )
                }
            }

            is UnlockContract.Intent.EnterNumber ->
                enterNumber(
                    index = intent.index,
                    number = intent.number
                )

            UnlockContract.Intent.KeyboardBack ->
                keyboardBack()

            is UnlockContract.Intent.ChangeShowBottomSheet -> {
                updateState {
                    it.copy(
                        showBottomSheet = intent.value
                    )
                }
            }

            is UnlockContract.Intent.RequestFocus -> {
                state.value.focusRequesters.mapIndexed { i, focusRequester ->
                    if (i == intent.index) focusRequester.requestFocus() else null
                }
            }

            UnlockContract.Intent.Action -> onAction()
        }
    }

    init {
        getSecurity()
    }

    private fun getSecurity() {
        viewModelScope.launch {
            try {
                val security = getSecurityUseCase().getOrThrow()

                updateState { it.copy(security = security) }
            } catch (e: Exception) {
                updateState { it.copy(isError = e) }
            }
        }
    }

    private fun onAction() {
        when (state.value.security) {
            is Security.Pin ->
                onIntent(
                    UnlockContract.Intent.ChangeShowBottomSheet(
                        true
                    )
                )

            else -> emitEffect(UnlockContract.SideEffect.ExecuteBiometricSecurity)
        }
    }

    private fun keyboardBack() {
        val (code, focusedIndex) =
            pinHelper.keyboardBack(
                state.value.code,
                state.value.focusedIndex
            )

        updateState {
            it.copy(
                code = code,
                focusedIndex = focusedIndex
            )
        }
    }

    private fun enterNumber(
        index: Int,
        number: Int?,
    ) {
        if (number != null) {
            state.value.focusRequesters.mapIndexed { i, focusRequester ->
                if (i == index) focusRequester.freeFocus() else null
            }
        }

        val (code, focusedIndex) =
            pinHelper.enterNumber(
                index = index,
                number = number,
                code = state.value.code,
                focusedIndex = state.value.focusedIndex
            )

        updateState {
            it.copy(
                code = code,
                focusedIndex = focusedIndex,
                isError = null
            )
        }

        canContinue()
    }

    private fun canContinue() {
        if (!state.value.code.none { digit -> digit == null }) return

        val pin = state.value.security.getPinCode()
        if (pin != state.value.code) {
            updateState { it.copy(isError = Exception("Incorrect PIN")) }
        } else {
            emitEffect(UnlockContract.SideEffect.NavigateNext)
        }
    }
}
