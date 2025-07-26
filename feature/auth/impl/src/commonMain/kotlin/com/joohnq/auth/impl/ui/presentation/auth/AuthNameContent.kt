package com.joohnq.auth.impl.ui.presentation.auth

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.joohnq.api.entity.CurvedCanvasPosition
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.button.PrimaryButton
import com.joohnq.shared_resources.components.input_field.AppTextFieldWithPlaceholder
import com.joohnq.shared_resources.components.layout.AppScaffoldLayout
import com.joohnq.shared_resources.components.layout.ConvexColumnLayout
import com.joohnq.shared_resources.components.spacer.VerticalSpacer
import com.joohnq.shared_resources.continue_word
import com.joohnq.shared_resources.enter_your_name
import com.joohnq.shared_resources.how_we_can_call_you
import com.joohnq.shared_resources.name
import com.joohnq.shared_resources.remember.rememberSnackBarState
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.ComponentColors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.Companion.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun AuthNameContent(
    snackBarState: SnackbarHostState = rememberSnackBarState(),
    state: AuthNameContract.State,
    onEvent: (AuthNameContract.Event) -> Unit,
    onClearFocus: () -> Unit,
    onGetAction: (AuthNameContract.Intent) -> Unit
) {
    val canContinue by derivedStateOf { state.name.isNotBlank() }

    AppScaffoldLayout(
        containerColor = Colors.Brown10,
        snackBarHostState = snackBarState,
        modifier = Modifier.fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    onClearFocus()
                })
            }
    ) { _ ->
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            ConvexColumnLayout(
                backgroundColor = Colors.Green50,
                spacer = 150.dp,
                position = CurvedCanvasPosition.BOTTOM
            ) {
                Icon(
                    painter = painterResource(Drawables.Icons.Filled.Logo),
                    contentDescription = null,
                    modifier = Modifier.size(48.dp),
                    tint = Colors.White
                )
            }
            VerticalSpacer(50.dp)
            Column(
                modifier = Modifier
                    .paddingHorizontalMedium()
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(Res.string.how_we_can_call_you),
                    style = TextStyles.headingSmExtraBold(),
                    textAlign = TextAlign.Center,
                    color = Colors.Brown80
                )
                VerticalSpacer(48.dp)
                AppTextFieldWithPlaceholder(
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
                    colors = ComponentColors.TextField.mainTextFieldColors(),
                    onValueChange = { onGetAction(AuthNameContract.Intent.Update(it)) }
                )
                VerticalSpacer(24.dp)
                if (canContinue) {
                    PrimaryButton(
                        modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp),
                        text = Res.string.continue_word,
                        onClick = { onEvent(AuthNameContract.Event.OnContinue) }
                    )
                }
            }
        }
    }
}