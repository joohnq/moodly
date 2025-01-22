package com.joohnq.security.domain

interface UserSecurityPreference {
    suspend fun update(security: Security): Result<Boolean>
    suspend fun get(): Result<Security>
    suspend fun initUserSecurity(): Result<Boolean>
}