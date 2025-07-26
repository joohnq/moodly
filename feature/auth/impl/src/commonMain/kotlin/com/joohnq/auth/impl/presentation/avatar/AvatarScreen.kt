package com.joohnq.auth.impl.presentation.avatar

import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import com.joohnq.auth.impl.components.AlertMessageDialog
import com.joohnq.auth.impl.components.ImageSourcePicker
import com.joohnq.permission.PermissionCallback
import com.joohnq.permission.PermissionStatus
import com.joohnq.permission.PermissionType
import com.joohnq.permission.createPermissionsManager
import com.joohnq.permission.rememberCameraManager
import com.joohnq.permission.rememberGalleryManager
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.cancel
import com.joohnq.shared_resources.permission_required
import com.joohnq.shared_resources.remember.rememberAvatars
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.shared_resources.settings
import com.joohnq.shared_resources.to_set_your_profile_picture
import com.joohnq.ui.sharedViewModel
import com.joohnq.user.impl.ui.viewmodel.UserContract
import com.joohnq.user.impl.ui.viewmodel.UserViewModel
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource

@Composable
fun AvatarScreen(
    onNavigateToUserName: () -> Unit
) {
    val snackBarState = rememberSnackBarState()
    val avatars = rememberAvatars()
    val pagerState = rememberPagerState(pageCount = { avatars.size })
    val avatarViewModel: AvatarViewModel = sharedViewModel()
    val avatarState by avatarViewModel.state.collectAsState()
    val userViewModel: UserViewModel = sharedViewModel()
    val scope = rememberCoroutineScope()
    var imageSourceOptionDialog by remember { mutableStateOf(value = false) }
    var launchCamera by remember { mutableStateOf(value = false) }
    var launchGallery by remember { mutableStateOf(value = false) }
    var launchSetting by remember { mutableStateOf(value = false) }
    var permissionRationalDialog by remember { mutableStateOf(value = false) }
    val permissionsManager =
        createPermissionsManager(object : PermissionCallback {
            override fun onPermissionStatus(
                permissionType: PermissionType,
                status: PermissionStatus
            ) {
                when (status) {
                    PermissionStatus.GRANTED -> {
                        when (permissionType) {
                            PermissionType.CAMERA -> launchCamera = true
                            PermissionType.GALLERY -> launchGallery = true
                        }
                    }

                    else -> {
                        permissionRationalDialog = true
                    }
                }
            }
        })
    val cameraManager = rememberCameraManager {
        scope.launch {
            avatarViewModel.onIntent(AvatarContract.Intent.UpdateImageBitmap(it?.toImageBitmap()))
        }
    }
    val galleryManager = rememberGalleryManager {
        scope.launch {
            avatarViewModel.onIntent(AvatarContract.Intent.UpdateImageBitmap(it?.toImageBitmap()))
        }
    }
    if (imageSourceOptionDialog) {
        ImageSourcePicker(onDismissRequest = {
            imageSourceOptionDialog = false
        }, onGalleryRequest = {
            imageSourceOptionDialog = false
            launchGallery = true
        }, onCameraRequest = {
            imageSourceOptionDialog = false
            launchCamera = true
        })
    }
    if (launchGallery) {
        if (permissionsManager.isPermissionGranted(PermissionType.GALLERY)) {
            galleryManager.launch()
        } else {
            permissionsManager.askPermission(PermissionType.GALLERY)
        }
        launchGallery = false
    }
    if (launchCamera) {
        if (permissionsManager.isPermissionGranted(PermissionType.CAMERA)) {
            cameraManager.launch()
        } else {
            permissionsManager.askPermission(PermissionType.CAMERA)
        }
        launchCamera = false
    }
    if (launchSetting) {
        permissionsManager.launchSettings()
        launchSetting = false
    }
    if (permissionRationalDialog) {
        AlertMessageDialog(
            title = stringResource(Res.string.permission_required),
            message = stringResource(Res.string.to_set_your_profile_picture),
            positiveButtonText = stringResource(Res.string.settings),
            negativeButtonText = stringResource(Res.string.cancel),
            onPositiveClick = {
                permissionRationalDialog = false
                launchSetting = true
            },
            onNegativeClick = {
                permissionRationalDialog = false
            }
        )
    }

    fun onError(error: String) {
        scope.launch {
            snackBarState.showSnackbar(error)
        }
    }

    fun onEvent(event: AvatarContract.Event) {
        when (event) {
            AvatarContract.Event.OnPickAvatar -> {
                imageSourceOptionDialog = true
            }

            AvatarContract.Event.OnContinue -> {
                val action = if (avatarState.imageBitmap == null) {
                    UserContract.Intent.UpdateImageDrawable(avatarState.selectedDrawableIndex)
                } else {
                    UserContract.Intent.UpdateImageBitmap(avatarState.imageBitmap!!)
                }

                userViewModel.onIntent(action)
            }
        }
    }

    LaunchedEffect(userViewModel) {
        userViewModel.sideEffect.collect { event ->
            when (event) {
                is UserContract.SideEffect.AvatarSavedSuccess -> {
                    onNavigateToUserName()
                }

                is UserContract.SideEffect.ShowError -> onError(event.error)
                else -> {}
            }
        }
    }

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            avatarViewModel.onIntent(AvatarContract.Intent.UpdateImageDrawableIndex(page))
        }
    }

    AvatarContent(
        snackBarState = snackBarState,
        state = avatarState,
        avatars = avatars,
        onEvent = ::onEvent
    )
}
