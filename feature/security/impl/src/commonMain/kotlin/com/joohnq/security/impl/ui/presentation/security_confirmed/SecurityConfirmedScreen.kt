package com.joohnq.security.impl.ui.presentation.security_confirmed

import androidx.compose.runtime.Composable
import com.joohnq.preferences.impl.ui.viewmodel.PreferencesContract
import com.joohnq.preferences.impl.ui.viewmodel.PreferencesViewModel
import com.joohnq.security.impl.ui.presentation.security_confirmed.event.SecurityConfirmedEvent
import com.joohnq.ui.sharedViewModel

@Composable
fun SecurityConfirmedScreen(
    onNavigateToDashboard: () -> Unit,
) {
    val preferencesViewModel: PreferencesViewModel = sharedViewModel()

    fun onEvent(event: SecurityConfirmedEvent) {
        when (event) {
            SecurityConfirmedEvent.OnContinue -> {
                preferencesViewModel.onAction(
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