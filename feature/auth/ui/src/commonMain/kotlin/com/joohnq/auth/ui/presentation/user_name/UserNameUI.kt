package com.joohnq.auth.ui.presentation.user_name

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.joohnq.auth.ui.presentation.user_name.event.UserNameEvent
import com.joohnq.auth.ui.presentation.user_name.viewmodel.UserNameIntent
import com.joohnq.auth.ui.presentation.user_name.viewmodel.UserNameState
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.*
import com.joohnq.shared_resources.enter_your_name
import com.joohnq.shared_resources.how_we_can_call_you
import com.joohnq.shared_resources.name
import com.joohnq.shared_resources.theme.*
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun UserNameUI(
    snackBarState: SnackbarHostState,
    state: UserNameState,
    onEvent: (UserNameEvent) -> Unit,
    onClearFocus: () -> Unit,
    onGetAction: (UserNameIntent) -> Unit,
) {
    val canContinue by derivedStateOf { state.name.isNotBlank() }

    BoxWithConstraints(modifier = Modifier.background(color = Colors.Brown10)) {
        TopBalloon(
            backgroundColor = Colors.Green50,
            iconColor = Colors.White
        )
        ScaffoldSnackBar(
            containerColor = Colors.Brown10,
            snackBarHostState = snackBarState,
            modifier = Modifier.fillMaxSize().padding(top = maxWidth / 2 + 56.dp, bottom = 20.dp)
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        onClearFocus()
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
                    label = Res.string.name,
                    placeholder = Res.string.enter_your_name,
                    text = state.name,
                    errorText = state.nameError,
                    focusedBorderColor = Colors.Green50Alpha25,
                    leadingIcon = {
                        Icon(
                            painter = painterResource(Drawables.Icons.Outlined.User),
                            contentDescription = null,
                            tint = Colors.Brown80,
                            modifier = Modifier.size(Dimens.Icon)
                        )
                    },
                    colors = ComponentColors.TextField.MainTextFieldColors(),
                    onValueChange = { onGetAction(UserNameIntent.UpdateUserName(it)) },
                )
                VerticalSpacer(24.dp)
                if (canContinue)
                    ContinueButton(
                        modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp),
                        onClick = { onEvent(UserNameEvent.Continue) }
                    )
            }
        }
    }
}
