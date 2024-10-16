package com.joohnq.moodapp.view.onboarding

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.joohnq.moodapp.Colors
import com.joohnq.moodapp.Drawables
import com.joohnq.moodapp.view.components.ButtonWithArrowRight
import com.joohnq.moodapp.view.components.TextStyles
import com.joohnq.moodapp.view.components.UserNameTextField
import com.joohnq.moodapp.view.home.HomeScreen
import com.joohnq.moodapp.viewmodel.UserViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.how_we_can_call_you
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

class GetUserNameScreen : Screen {
    @Composable
    @Preview
    override fun Content() {
        var name by remember { mutableStateOf("") }
        var nameError by remember { mutableStateOf("") }
        val focusManager: FocusManager = LocalFocusManager.current
        val userViewModel: UserViewModel = koinInject()
        val scope = rememberCoroutineScope()
        val ioDispatcher: CoroutineDispatcher = koinInject()
        val navigator = LocalNavigator.currentOrThrow
        val snackBarState = remember { SnackbarHostState() }
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
                modifier = Modifier.fillMaxSize().padding(top = maxWidth / 2 + 56.dp)
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
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(Res.string.how_we_can_call_you),
                        style = TextStyles.OnboardingScreenTitle()
                    )
                    Spacer(modifier = Modifier.height(48.dp))
                    UserNameTextField(
                        name = name,
                        errorText = nameError,
                        onValueChange = {
                            name = it
                            nameError = ""
                        }
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    ButtonWithArrowRight(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Continue"
                    ) {
                        focusManager.clearFocus()
                        scope.launch(ioDispatcher) {
                            try {
                                if (name.isEmpty()) throw Exception("Name is required")
                                val res: Boolean = userViewModel.setUserName(name)
                                if (!res) {
                                    snackBarState.showSnackbar("Something went wrong")
                                    return@launch
                                }

                                navigator.push(HomeScreen())
                            } catch (e: Exception) {
                                nameError = e.message ?: ""
                            }
                        }
                    }
                }
            }
        }
    }
}
