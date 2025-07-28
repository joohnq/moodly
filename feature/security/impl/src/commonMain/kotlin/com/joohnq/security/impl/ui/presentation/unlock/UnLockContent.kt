package com.joohnq.security.impl.ui.presentation.unlock

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.joohnq.api.entity.CurvedCanvasPosition
import com.joohnq.security.impl.ui.components.PinCode
import com.joohnq.security.impl.ui.presentation.pin.PinContract
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.button.PrimaryButton
import com.joohnq.shared_resources.components.layout.ConvexColumnLayout
import com.joohnq.shared_resources.components.spacer.VerticalSpacer
import com.joohnq.shared_resources.components.view.ErrorView
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.paddingHorizontalMedium
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.shared_resources.this_password_is_the_same_validation_method
import com.joohnq.shared_resources.type_your_four_digit_pin_to_unlock_the_app
import com.joohnq.shared_resources.use_device_password
import com.joohnq.shared_resources.use_your_authentication_to_securely
import com.joohnq.shared_resources.welcome_back
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnLockContent(
    sheetState: SheetState = rememberModalBottomSheetState(),
    isError: Exception? = null,
    showBottomSheet: Boolean,
    state: PinContract.State,
    focusRequesters: List<FocusRequester> = emptyList(),
    focusManager: FocusManager = LocalFocusManager.current,
    keyboardManager: SoftwareKeyboardController? = null,
    onAction: (PinContract.Intent) -> Unit = {},
    onEvent: (UnlockContract.Event) -> Unit = {},
) {
    if (showBottomSheet) {
        ModalBottomSheet(
            containerColor = Colors.Brown10,
            onDismissRequest = {
                onEvent(UnlockContract.Event.OnUpdateShowBottomSheet(false))
            },
            sheetState = sheetState
        ) {
            Column(
                modifier = Modifier.paddingHorizontalMedium().padding(bottom = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                VerticalSpacer(10.dp)
                Text(
                    text = stringResource(Res.string.type_your_four_digit_pin_to_unlock_the_app),
                    style = TextStyles.paragraphLg(),
                    color = Colors.Brown100Alpha64,
                    textAlign = TextAlign.Center
                )
                VerticalSpacer(10.dp)
                PinCode(
                    code = state.code,
                    focusedIndex = state.focusedIndex,
                    onNumberChanged = { i, newNumber ->
                        onAction(
                            PinContract.Intent.OnEnterNumber(
                                index = i,
                                number = newNumber
                            )
                        )
                    },
                    onKeyboardBack = { onAction(PinContract.Intent.OnKeyboardBack) },
                    focusRequesters = focusRequesters,
                    focusManager = focusManager,
                    keyboardManager = keyboardManager,
                    onFocusChanged = { i -> onAction(PinContract.Intent.OnChangeFieldFocused(i)) }
                )
                isError?.let {
                    VerticalSpacer(15.dp)
                    ErrorView(it.message.toString())
                }
            }
        }
    }

    BoxWithConstraints(modifier = Modifier.background(color = Colors.Brown10)) {
        ConvexColumnLayout(
            backgroundColor = Colors.Brown80,
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
        Scaffold(
            containerColor = Colors.Brown10,
            modifier = Modifier.padding(top = maxWidth / 2 + 56.dp)
        ) { padding ->
            Column(
                modifier = Modifier.fillMaxSize().padding(padding).paddingHorizontalMedium(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    VerticalSpacer(40.dp)
                    Text(
                        text = stringResource(Res.string.welcome_back),
                        style = TextStyles.headingSmExtraBold(),
                        color = Colors.Brown80
                    )
                    VerticalSpacer(10.dp)
                    Text(
                        text = stringResource(Res.string.use_your_authentication_to_securely),
                        style = TextStyles.paragraphLg(),
                        color = Colors.Brown100Alpha64
                    )
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    PrimaryButton(
                        text = Res.string.use_device_password,
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { onEvent(UnlockContract.Event.OnContinue) }
                    )
                    VerticalSpacer(16.dp)
                    Text(
                        text = stringResource(Res.string.this_password_is_the_same_validation_method),
                        style = TextStyles.textSmMedium(),
                        color = Colors.Brown100Alpha64,
                        textAlign = TextAlign.Center
                    )
                    VerticalSpacer(10.dp)
                }
            }
        }
    }
}
