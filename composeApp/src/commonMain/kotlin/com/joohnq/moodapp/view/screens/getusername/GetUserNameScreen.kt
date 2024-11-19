package com.joohnq.moodapp.view.screens.getusername

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.view.components.ContinueButton
import com.joohnq.moodapp.view.components.TextFieldWithLabelAndDoubleBorder
import com.joohnq.moodapp.view.components.VerticalSpacer
import com.joohnq.moodapp.view.routes.onNavigateToHomeGraph
import com.joohnq.moodapp.view.state.UiState.Companion.fold
import com.joohnq.moodapp.view.ui.Colors
import com.joohnq.moodapp.view.ui.ComponentColors
import com.joohnq.moodapp.view.ui.Dimens
import com.joohnq.moodapp.view.ui.Drawables
import com.joohnq.moodapp.view.ui.PaddingModifier.Companion.paddingHorizontalSmall
import com.joohnq.moodapp.view.ui.TextStyles
import com.joohnq.moodapp.viewmodel.UserIntent
import com.joohnq.moodapp.viewmodel.UserPreferenceIntent
import com.joohnq.moodapp.viewmodel.UserPreferenceViewModel
import com.joohnq.moodapp.viewmodel.UserViewModel
import kotlinx.coroutines.launch
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.enter_your_name
import moodapp.composeapp.generated.resources.how_we_can_call_you
import moodapp.composeapp.generated.resources.name
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun GetUserNameScreenUI(
    snackBarState: SnackbarHostState = remember { SnackbarHostState() },
    name: String,
    nameError: String?,
    onContinue: () -> Unit = {},
    onClearFocus: () -> Unit = {},
    onAction: (UserIntent) -> Unit = {}
) {
    BoxWithConstraints(modifier = Modifier.background(color = Colors.Brown10)) {
        Box(
            modifier = Modifier
                .width(maxWidth * 1.5f)
                .height(0.dp)
                .aspectRatio(1f)
                .background(
                    color = Colors.Green50,
                    shape = Dimens.Shape.Circle
                )
                .padding(30.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Icon(
                painter = painterResource(Drawables.Icons.Logo),
                contentDescription = null,
                modifier = Modifier.size(48.dp),
                tint = Colors.White
            )
        }
        Scaffold(
            containerColor = Colors.Brown10,
            snackbarHost = { SnackbarHost(hostState = snackBarState) },
            modifier = Modifier.fillMaxSize().padding(top = maxWidth / 2 + 56.dp, bottom = 20.dp)
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        onClearFocus()
                    })
                }
        ) { _ ->
            Column(
                modifier = Modifier
                    .paddingHorizontalSmall()
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(Res.string.how_we_can_call_you),
                        style = TextStyles.HeadingSmExtraBold(),
                        textAlign = TextAlign.Center,
                        color = Colors.Brown80
                    )
                    VerticalSpacer(48.dp)
                    TextFieldWithLabelAndDoubleBorder(
                        label = Res.string.name,
                        placeholder = Res.string.enter_your_name,
                        text = name,
                        errorText = nameError,
                        focusedBorderColor = Colors.Green50Alpha25,
                        leadingIcon = {
                            Icon(
                                painter = painterResource(Drawables.Icons.User),
                                contentDescription = null,
                                tint = Colors.Brown80,
                                modifier = Modifier.size(Dimens.Icon)
                            )
                        },
                        colors = ComponentColors.TextField.MainTextFieldColors(),
                        onValueChange = { onAction(UserIntent.UpdateUpdatingUserName(it)) },
                    )
                    VerticalSpacer(24.dp)
                }
                ContinueButton(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp),
                    onClick = onContinue
                )
            }
        }
    }
}

@Composable
fun GetUserNameScreen(
    navigation: NavHostController,
    userViewModel: UserViewModel = sharedViewModel(),
    userPreferencesViewModel: UserPreferenceViewModel = sharedViewModel()
) {
    val scope = rememberCoroutineScope()
    val focusManager: FocusManager = LocalFocusManager.current
    val snackBarState = remember { SnackbarHostState() }
    val userState by userViewModel.userState.collectAsState()

    LaunchedEffect(userState.updating.status) {
        userState.updating.status.fold(
            onError = {
                scope.launch {
                    snackBarState.showSnackbar(it)
                }
            },
            onSuccess = {
                navigation.onNavigateToHomeGraph()
                userPreferencesViewModel.onAction(
                    UserPreferenceIntent.UpdateSkipGetUserNameScreen()
                )
            }
        )
    }

    GetUserNameScreenUI(
        name = userState.updating.name,
        nameError = userState.updating.nameError,
        snackBarState = snackBarState,
        onClearFocus = focusManager::clearFocus,
        onAction = userViewModel::onAction,
        onContinue = {
            focusManager.clearFocus()
            userViewModel.onAction(UserIntent.UpdateUserName)
        },
    )
}
