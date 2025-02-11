package com.joohnq.security.ui.presentation.security_confirmed

import androidx.compose.runtime.Composable
import com.joohnq.ui.sharedViewModel
import com.joohnq.preferences.ui.viewmodel.PreferenceIntent
import com.joohnq.preferences.ui.viewmodel.PreferencesViewModel
import com.joohnq.security.ui.presentation.security_confirmed.event.SecurityConfirmedEvent

@Composable
fun SecurityConfirmedScreen(
    onNavigateToDashboard: () -> Unit,
) {
    val preferencesViewModel: PreferencesViewModel = sharedViewModel()

    fun onEvent(event: SecurityConfirmedEvent) {
        when (event) {
            SecurityConfirmedEvent.OnContinue -> {
                preferencesViewModel.onAction(
                    PreferenceIntent.UpdateSkipSecurity()
                )
                onNavigateToDashboard()
            }
        }
    }

    SecurityConfirmedUI(
        onEvent = ::onEvent
    )
}