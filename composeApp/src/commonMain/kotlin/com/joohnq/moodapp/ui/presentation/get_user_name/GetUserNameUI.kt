package com.joohnq.moodapp.ui.presentation.get_user_name

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.joohnq.moodapp.ui.components.ContinueButton
import com.joohnq.moodapp.ui.components.TextFieldWithLabelAndDoubleBorder
import com.joohnq.moodapp.ui.components.VerticalSpacer
import com.joohnq.moodapp.ui.presentation.get_user_name.event.GetUserNameEvent
import com.joohnq.moodapp.ui.presentation.get_user_name.state.GetUserNameState
import com.joohnq.moodapp.ui.theme.Colors
import com.joohnq.moodapp.ui.theme.ComponentColors
import com.joohnq.moodapp.ui.theme.Dimens
import com.joohnq.moodapp.ui.theme.Drawables
import com.joohnq.moodapp.ui.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.moodapp.ui.theme.TextStyles
import com.joohnq.moodapp.viewmodel.UserIntent
import moodapp.composeapp.generated.resources.Res
import moodapp.composeapp.generated.resources.enter_your_name
import moodapp.composeapp.generated.resources.how_we_can_call_you
import moodapp.composeapp.generated.resources.name
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun GetUserNameUI(
    state: GetUserNameState,
) {
    val canContinue by derivedStateOf { state.name.isNotBlank() }

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
            snackbarHost = { SnackbarHost(hostState = state.snackBarState) },
            modifier = Modifier.fillMaxSize().padding(top = maxWidth / 2 + 56.dp, bottom = 20.dp)
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        state.onClearFocus()
                    })
                }
        ) { _ ->
            Column(
                modifier = Modifier
                    .paddingHorizontalMedium()
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = stringResource(Res.string.how_we_can_call_you),
                    style = TextStyles.HeadingSmExtraBold(),
                    textAlign = TextAlign.Center,
                    color = Colors.Brown80
                )
                VerticalSpacer(48.dp)
                TextFieldWithLabelAndDoubleBorder(
                    modifier = Modifier.testTag(GetUserNameScreen.GetUserNameTestTag.TEXT_INPUT),
                    label = Res.string.name,
                    placeholder = Res.string.enter_your_name,
                    text = state.name,
                    errorText = state.nameError,
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
                    onValueChange = { state.onAction(UserIntent.UpdateUpdatingUserName(it)) },
                )
                VerticalSpacer(24.dp)
                if (canContinue)
                    ContinueButton(
                        modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp),
                        onClick = { state.onEvent(GetUserNameEvent.OnContinue) }
                    )
            }
        }
    }
}

@Preview
@Composable
fun Preview() {
    GetUserNameUI(
        GetUserNameState(
            snackBarState = remember { SnackbarHostState() },
            name = "dawd",
            nameError = null,
            onEvent = {},
            onClearFocus = {},
            onAction = {}
        )
    )
}