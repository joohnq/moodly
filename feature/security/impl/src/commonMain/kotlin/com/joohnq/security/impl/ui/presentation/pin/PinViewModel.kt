package com.joohnq.security.impl.ui.presentation.pin

import androidx.lifecycle.viewModelScope
import com.joohnq.api.mapper.ListMapper.itemsNotNull
import com.joohnq.security.api.Security
import com.joohnq.security.api.use_case.UpdateSecurityUseCase
import com.joohnq.security.impl.PinHelper
import com.joohnq.ui.BaseViewModel
import kotlinx.coroutines.launch

class PinViewModel(
    private val updateSecurityUseCase: UpdateSecurityUseCase,
    private val pinHelper: PinHelper,
    initialState: PinContract.State = PinContract.State(),
) : BaseViewModel<PinContract.State, PinContract.Intent, PinContract.SideEffect>(
        initialState = initialState
    ),
    PinContract.ViewModel {
    override fun onIntent(intent: PinContract.Intent) {
        when (intent) {
            is PinContract.Intent.ChangeFieldFocused ->
                updateState {
                    it.copy(
                        focusedIndex = intent.index
                    )
                }

            is PinContract.Intent.EnterNumber ->
                enterNumber(index = intent.index, number = intent.number)

            PinContract.Intent.KeyboardBack ->
                keyboardBack()

            PinContract.Intent.Action -> action()
        }
    }

    private fun action() {
        viewModelScope.launch {
            try {
                updateSecurityUseCase(
                    Security.Pin(
                        enabled = true,
                        code = state.value.code.itemsNotNull()
                    )
                )
            } catch (e: Exception) {
                emitEffect(PinContract.SideEffect.ShowError(e.message.toString()))
            }
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
                focusedIndex = focusedIndex
            )
        }
    }
}
