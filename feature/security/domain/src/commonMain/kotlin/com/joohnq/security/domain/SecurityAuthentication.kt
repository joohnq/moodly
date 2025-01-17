package com.joohnq.security.domain

interface SecurityAuthentication {
    fun isDeviceHasBiometric(): Boolean
    fun authenticateWithFace(callback: (Boolean) -> Unit)
}