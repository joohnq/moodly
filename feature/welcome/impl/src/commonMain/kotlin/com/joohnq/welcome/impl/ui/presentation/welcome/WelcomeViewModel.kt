package com.joohnq.welcome.impl.ui.presentation.welcome

import androidx.lifecycle.viewModelScope
import com.joohnq.preferences.api.use_case.UpdateSkipWelcomeUseCase
import com.joohnq.ui.BaseViewModelWithoutState
import kotlinx.coroutines.launch

class WelcomeViewModel(
    private val updateSkipWelcomeUseCase: UpdateSkipWelcomeUseCase,
) : BaseViewModelWithoutState<WelcomeContract.Intent, WelcomeContract.SideEffect>(),
    WelcomeContract.ViewModel {
    override fun onIntent(intent: WelcomeContract.Intent) {
        when (intent) {
            WelcomeContract.Intent.Action -> updateSkipWelcome()
        }
    }

    private fun updateSkipWelcome() {
        viewModelScope.launch {
            try {
                updateSkipWelcomeUseCase(true).getOrThrow()

                emitEffect(WelcomeContract.SideEffect.NavigateNext)
            } catch (e: Exception) {
                emitEffect(WelcomeContract.SideEffect.ShowError(e.message.toString()))
            }
        }
    }
}