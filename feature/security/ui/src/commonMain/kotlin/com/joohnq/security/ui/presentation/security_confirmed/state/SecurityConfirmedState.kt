package com.joohnq.security.ui.presentation.security_confirmed.state

import com.joohnq.security.ui.presentation.security_confirmed.event.SecurityConfirmedEvent

data class SecurityConfirmedState(
    val onEvent: (SecurityConfirmedEvent) -> Unit,
)
