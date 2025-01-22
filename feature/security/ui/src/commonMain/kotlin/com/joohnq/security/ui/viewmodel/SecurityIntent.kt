package com.joohnq.security.ui.viewmodel

import com.joohnq.security.domain.Security

sealed interface SecurityIntent {
    data object GetSecurity : SecurityIntent
    data class UpdateSecurity(val security: Security) : SecurityIntent
}