package com.joohnq.permission

import androidx.compose.runtime.Composable

/*
* The interface to define a contract to handle permissions on android and ios
* */
interface PermissionHandler {
    @Composable
    fun askPermission(permission: PermissionType)

    @Composable
    fun isPermissionGranted(permission: PermissionType): Boolean

    @Composable
    fun launchSettings()
}
