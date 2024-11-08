package com.joohnq.moodapp.view.screens.getusername

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.joohnq.moodapp.constants.TestConstants
import com.joohnq.moodapp.sharedViewModel
import com.joohnq.moodapp.view.components.ButtonWithArrowRight
import com.joohnq.moodapp.view.components.TextStyles
import com.joohnq.moodapp.view.components.UserNameTextField
import com.joohnq.moodapp.view.constants.Colors
import com.joohnq.moodapp.view.constants.Drawables
import com.joohnq.moodapp.view.routes.onNavigateToCompilingData
import com.joohnq.moodapp.view.state.UiState.Companion.fold
import com.joohnq.moodapp.viewmodel.UserPreferenceIntent
import com.joohnq.moodapp.viewmodel.UserPreferenceViewModel
import com.joohnq.moodapp.viewmodel.UserViewModel
import kotlinx.coroutines.launch
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.continue_word
import moodapp.composeapp.generated.resources.how_we_can_call_you
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun GetUserNameScreenUI(
    snackBarState: SnackbarHostState = remember { SnackbarHostState() },
    name: String,
    nameError: String,
    setName: (String) -> Unit,
    setNameError: (String) -> Unit,
    onAction: () -> Unit,
    focusManager: FocusManager = LocalFocusManager.current
) {
    BoxWithConstraints(modifier = Modifier.background(color = Colors.Brown10)) {
        Box(
            modifier = Modifier
                .width(maxWidth * 1.5f)
                .height(0.dp)
                .aspectRatio(1f)
                .background(
                    color = Colors.Green50,
                    shape = CircleShape
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
                        focusManager.clearFocus()
                    })
                }
        ) { padding ->
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
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
                        style = TextStyles.OnboardingScreenTitle(),
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(48.dp))
                    UserNameTextField(
                        modifier = Modifier.testTag(TestConstants.TEXT_INPUT),
                        name = name,
                        errorText = nameError,
                        onValueChange = {
                            setName(it)
                            setNameError("")
                        }
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                }
                ButtonWithArrowRight(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp),
                    text = Res.string.continue_word
                ) {
                    focusManager.clearFocus()
                    if (name.trim().isEmpty()) {
                        setNameError("Name is required")
                        return@ButtonWithArrowRight
                    }

                    onAction()
                }
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
    var name by remember { mutableStateOf("") }
    var nameError by remember { mutableStateOf("") }
    val focusManager: FocusManager = LocalFocusManager.current
    val snackBarState = remember { SnackbarHostState() }
    val userState by userViewModel.userState.collectAsState()

    LaunchedEffect(userState) {
        userState.updatingStatus.fold(
            onError = {
                scope.launch {
                    snackBarState.showSnackbar(it)
                }
            },
            onSuccess = {
                navigation.onNavigateToCompilingData()
                userPreferencesViewModel.onAction(
                    UserPreferenceIntent.UpdateSkipOnboardingScreen(
                        true
                    )
                )
            }
        )
    }

    GetUserNameScreenUI(
        name = name,
        nameError = nameError,
        snackBarState = snackBarState,
        focusManager = focusManager,
        setName = { name = it },
        setNameError = { nameError = it },
        onAction = {
            userViewModel.updateUserName(name)
        },
    )
}
