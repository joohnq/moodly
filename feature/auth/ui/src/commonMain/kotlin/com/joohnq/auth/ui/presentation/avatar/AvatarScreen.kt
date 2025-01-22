package com.joohnq.auth.ui.presentation.avatar

import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import com.joohnq.auth.ui.components.AlertMessageDialog
import com.joohnq.auth.ui.components.ImageSourceOptionDialog
import com.joohnq.auth.ui.presentation.avatar.event.AvatarEvent
import com.joohnq.auth.ui.presentation.avatar.viewmodel.AvatarIntent
import com.joohnq.auth.ui.presentation.avatar.viewmodel.AvatarViewModel
import com.joohnq.core.ui.sharedViewModel
import com.joohnq.permission.PermissionCallback
import com.joohnq.permission.PermissionStatus
import com.joohnq.permission.PermissionType
import com.joohnq.permission.createPermissionsManager
import com.joohnq.permission.rememberCameraManager
import com.joohnq.permission.rememberGalleryManager
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.cancel
import com.joohnq.shared_resources.permission_required
import com.joohnq.shared_resources.settings
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.to_set_your_profile_picture
import com.joohnq.user.ui.viewmodel.user.UserIntent
import com.joohnq.user.ui.viewmodel.user.UserSideEffect
import com.joohnq.user.ui.viewmodel.user.UserViewModel
import kotlinx.coroutines.launch

@Composable
fun AvatarScreen(
    onNavigateToUserName: () -> Unit,
) {
    val snackBarState = remember { SnackbarHostState() }
    val images = remember { Drawables.Avatar.avatars }
    val pagerState = rememberPagerState(pageCount = { images.size })
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
                status: PermissionStatus,
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
            avatarViewModel.onAction(AvatarIntent.UpdateImageBitmap(it?.toImageBitmap()))
        }
    }
    val galleryManager = rememberGalleryManager {
        scope.launch {
            avatarViewModel.onAction(AvatarIntent.UpdateImageBitmap(it?.toImageBitmap()))
        }
    }
    if (imageSourceOptionDialog) {
        ImageSourceOptionDialog(onDismissRequest = {
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
        AlertMessageDialog(title = Res.string.permission_required,
            message = Res.string.to_set_your_profile_picture,
            positiveButtonText = Res.string.settings,
            negativeButtonText = Res.string.cancel,
            onPositiveClick = {
                permissionRationalDialog = false
                launchSetting = true
            },
            onNegativeClick = {
                permissionRationalDialog = false
            }
        )
    }

    fun onError(error: Throwable) {
        scope.launch {
            snackBarState.showSnackbar(error.message.toString())
        }
    }

    fun onEvent(event: AvatarEvent) {
        when (event) {
            AvatarEvent.OnPickAvatar -> {
                imageSourceOptionDialog = true
            }

            AvatarEvent.OnContinue -> {
                val action = if (avatarState.imageBitmap == null)
                    UserIntent.UpdateUserImageDrawable(avatarState.selectedDrawableIndex)
                else
                    UserIntent.UpdateUserImageBitmap(avatarState.imageBitmap!!)

                userViewModel.onAction(action)
            }
        }
    }

    LaunchedEffect(userViewModel) {
        scope.launch {
            userViewModel.sideEffect.collect { event ->
                when (event) {
                    is UserSideEffect.AvatarSavedSuccess -> {
                        onNavigateToUserName()
                    }

                    is UserSideEffect.ShowError -> onError(event.error)
                    else -> {}
                }
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            userViewModel.onAction(UserIntent.ResetUpdatingStatus)
        }
    }

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            avatarViewModel.onAction(AvatarIntent.UpdateImageDrawableIndex(page))
        }
    }

    AvatarUI(
        snackBarState = snackBarState,
        pagerState = pagerState,
        images = images,
        onEvent = ::onEvent,
        avatarState = avatarState
    )
}
