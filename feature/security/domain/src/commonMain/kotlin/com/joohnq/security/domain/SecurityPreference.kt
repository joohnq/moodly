package com.joohnq.security.domain

interface SecurityPreference {
    suspend fun update(security: Security): Result<Boolean>
    suspend fun get(): Result<Security>
    suspend fun initUserSecurity(): Result<Boolean>
}