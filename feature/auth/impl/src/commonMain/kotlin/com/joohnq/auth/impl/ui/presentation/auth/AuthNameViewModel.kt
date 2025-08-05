package com.joohnq.auth.impl.ui.presentation.auth

import androidx.lifecycle.viewModelScope
import com.joohnq.api.use_case.UpdateUserNameUseCase
import com.joohnq.preferences.api.use_case.UpdateSkipAuthUseCase
import com.joohnq.ui.BaseViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class AuthNameViewModel(
    private val updateUserNameUseCase: UpdateUserNameUseCase,
    private val updateSkipAuthUseCase: UpdateSkipAuthUseCase,
    initialState: AuthNameContract.State = AuthNameContract.State(),
) : BaseViewModel<AuthNameContract.State, AuthNameContract.Intent, AuthNameContract.SideEffect>(
        initialState = initialState
    ),
    AuthNameContract.ViewModel {
    override fun onIntent(intent: AuthNameContract.Intent) {
        when (intent) {
            is AuthNameContract.Intent.ChangeName -> updateState { it.copy(name = intent.name) }
            AuthNameContract.Intent.Action -> action()
        }
    }

    private fun action() {
        viewModelScope.launch {
            try {
                async { updateUserNameUseCase(state.value.name) }.await()
                async { updateSkipAuthUseCase(true) }.await()

                emitEffect(AuthNameContract.SideEffect.NavigateNext)
            } catch (e: Exception) {
                emitEffect(AuthNameContract.SideEffect.ShowError(e.message.toString()))
            }
        }
    }
}