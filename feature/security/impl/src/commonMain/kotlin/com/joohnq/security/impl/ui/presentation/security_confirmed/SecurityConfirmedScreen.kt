package com.joohnq.security.impl.ui.presentation.security_confirmed

import androidx.compose.runtime.Composable
import com.joohnq.preferences.impl.ui.viewmodel.PreferencesContract
import com.joohnq.preferences.impl.ui.viewmodel.PreferencesViewModel
import com.joohnq.ui.sharedViewModel

@Composable
fun SecurityConfirmedScreen(
    onNavigateToDashboard: () -> Unit,
    preferencesViewModel: PreferencesViewModel = sharedViewModel(),
) {
    fun onEvent(event: SecurityConfirmedContract.Event) {
        when (event) {
            SecurityConfirmedContract.Event.OnContinue -> {
                preferencesViewModel.onIntent(
                    PreferencesContract.Intent.UpdateSkipSecurity()
                )
                onNavigateToDashboard()
            }
        }
    }

    SecurityConfirmedContent(
        onEvent = ::onEvent
    )
}
