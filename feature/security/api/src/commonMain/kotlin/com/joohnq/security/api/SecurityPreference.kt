package com.joohnq.security.api

interface SecurityPreference {
    suspend fun update(security: Security)

    suspend fun get(): Security

    suspend fun init()
}
