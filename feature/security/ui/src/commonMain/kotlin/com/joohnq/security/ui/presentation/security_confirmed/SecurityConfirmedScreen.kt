package com.joohnq.security.ui.presentation.security_confirmed

import androidx.compose.runtime.Composable
import com.joohnq.core.ui.CustomScreen
import com.joohnq.security.ui.presentation.security_confirmed.event.SecurityConfirmedEvent
import com.joohnq.security.ui.presentation.security_confirmed.state.SecurityConfirmedState

class SecurityConfirmedScreen(
    private val onNavigateToDashboard: () -> Unit,
) : CustomScreen<SecurityConfirmedState>() {
    @Composable
    override fun Screen(): SecurityConfirmedState {
        fun onEvent(event: SecurityConfirmedEvent) {
            when (event) {
                SecurityConfirmedEvent.OnContinue -> {
                    onNavigateToDashboard()
                }
            }
        }

        return SecurityConfirmedState(
            onEvent = ::onEvent
        )
    }

    @Composable
    override fun UI(state: SecurityConfirmedState) = SecurityConfirmedUI(state)
}