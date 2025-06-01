package com.joohnq.security.ui.presentation.security_confirmed

import androidx.compose.runtime.Composable
import com.joohnq.preferences.ui.viewmodel.PreferencesContract
import com.joohnq.preferences.ui.viewmodel.PreferencesViewModel
import com.joohnq.ui.sharedViewModel

@Composable
fun SecurityConfirmedScreen(
    onNavigateToDashboard: () -> Unit,
) {
    val preferencesViewModel: PreferencesViewModel = sharedViewModel()

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

    SecurityConfirmedUI(
        onEvent = ::onEvent
    )
}