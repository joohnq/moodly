package com.joohnq.auth.ui

import androidx.compose.runtime.Composable

/*
* Implement to handle specific permission in both platform
* */
expect class PermissionsManager(callback: PermissionCallback) : PermissionHandler

interface PermissionCallback {
    fun onPermissionStatus(permissionType: PermissionType, status: PermissionStatus)
}

/*
* Initialize the permission manager class
* OBS: will be substituted with dependence injection
* */
@Composable
expect fun createPermissionsManager(callback: PermissionCallback): PermissionsManager

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