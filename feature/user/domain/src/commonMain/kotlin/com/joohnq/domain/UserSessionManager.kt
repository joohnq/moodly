package com.joohnq.domain

import com.joohnq.domain.entity.UiState
import com.joohnq.domain.entity.UserSessionState
import kotlinx.coroutines.flow.Flow

interface UserSessionManager {
    val session: Flow<UiState<UserSessionState>>
    suspend fun clearSession()
}