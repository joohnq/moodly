package com.joohnq.security.ui.presentation.security_confirmed

import androidx.compose.runtime.Composable
import com.joohnq.core.ui.sharedViewModel
import com.joohnq.security.ui.presentation.security_confirmed.event.SecurityConfirmedEvent
import com.joohnq.user.ui.viewmodel.user_preferences.UserPreferenceIntent
import com.joohnq.user.ui.viewmodel.user_preferences.UserPreferencesViewModel

@Composable
fun SecurityConfirmedScreen(
    onNavigateToDashboard: () -> Unit,
) {
    val userPreferencesViewModel: UserPreferencesViewModel = sharedViewModel()

    fun onEvent(event: SecurityConfirmedEvent) {
        when (event) {
            SecurityConfirmedEvent.OnContinue -> {
                userPreferencesViewModel.onAction(
                    UserPreferenceIntent.UpdateSkipSecurity()
                )
                onNavigateToDashboard()
            }
        }
    }

    SecurityConfirmedUI(
        onEvent = ::onEvent
    )
}