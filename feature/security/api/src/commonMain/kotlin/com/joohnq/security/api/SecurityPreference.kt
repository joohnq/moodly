package com.joohnq.security.api

interface SecurityPreference {
    suspend fun update(security: Security): Result<Boolean>

    suspend fun get(): Result<Security>

    suspend fun initUserSecurity(): Result<Boolean>
}
