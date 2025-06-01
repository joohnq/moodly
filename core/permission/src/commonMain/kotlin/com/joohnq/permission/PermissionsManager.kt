package com.joohnq.permission

import androidx.compose.runtime.Composable

/*
* Implement to handle specific permission in both platform
* */
expect class PermissionsManager(callback: PermissionCallback) : PermissionHandler

@Composable
expect fun createPermissionsManager(callback: PermissionCallback): PermissionsManager

