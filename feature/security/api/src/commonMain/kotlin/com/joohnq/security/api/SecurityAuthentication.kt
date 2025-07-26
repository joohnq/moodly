package com.joohnq.security.api

interface SecurityAuthentication {
    fun isDeviceHasBiometric(): Boolean

    fun authenticateWithFace(callback: (Boolean) -> Unit)
}
