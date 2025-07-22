package com.joohnq.security.impl.data

import com.joohnq.security.api.SecurityAuthentication

expect class SecurityAuthenticationImpl : SecurityAuthentication {
    override fun isDeviceHasBiometric(): Boolean
    override fun authenticateWithFace(callback: (Boolean) -> Unit)
}