package com.joohnq.auth.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.joohnq.core.ui.entity.DIcon
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.camera
import com.joohnq.shared_resources.components.ButtonTextAndIcon
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.gallery
import com.joohnq.shared_resources.select_an_image_source
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.ComponentColors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageSourceOptionDialog(
    onDismissRequest: () -> Unit,
    onGalleryRequest: () -> Unit = {},
    onCameraRequest: () -> Unit = {},
) {
    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
                .background(Colors.Brown10, shape = Dimens.Shape.Large)
                .padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(Res.string.select_an_image_source),
                style = TextStyles.TextXlExtraBold(),
                color = Colors.Brown80
            )
            VerticalSpacer(20.dp)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                ButtonTextAndIcon(
                    modifier = Modifier.weight(1f).height(60.dp),
                    text = Res.string.camera,
                    icon = DIcon(
                        icon = Drawables.Icons.Camera,
                        tint = Colors.White,
                        modifier = Modifier.size(32.dp),
                        contentDescription = Res.string.camera
                    ),
                    colors = ComponentColors.Button.MainButtonColors(),
                    shape = Dimens.Shape.Circle,
                    onClick = { onCameraRequest() }
                )
                ButtonTextAndIcon(
                    modifier = Modifier.weight(1f).height(60.dp),
                    text = Res.string.gallery,
                    icon = DIcon(
                        icon = Drawables.Icons.Gallery,
                        tint = Colors.White,
                        modifier = Modifier.size(32.dp),
                        contentDescription = Res.string.gallery
                    ),
                    colors = ComponentColors.Button.MainButtonColors(),
                    shape = Dimens.Shape.Circle,
                    onClick = { onGalleryRequest() }
                )
            }
        }
    }
}

@Composable
fun AlertMessageDialog(
    title: StringResource,
    message: StringResource? = null,
    positiveButtonText: StringResource? = null,
    negativeButtonText: StringResource? = null,
    onPositiveClick: () -> Unit = {},
    onNegativeClick: () -> Unit = {},
) {
    Dialog(
        onDismissRequest = {}, properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false,
        )
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth().wrapContentHeight(),
            shape = RoundedCornerShape(size = 12.dp)
        ) {
            Column(
                modifier = Modifier.background(Colors.Brown10)
                    .padding(all = 16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = stringResource(title),
                    style = TextStyles.TextXlExtraBold(),
                    fontWeight = FontWeight.Medium,
                    color = Colors.Brown80,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(10.dp))
                message?.let {
                    Text(
                        text = stringResource(it),
                        style = TextStyles.TextMdSemiBold(),
                        fontWeight = FontWeight.Medium,
                        color = Colors.Brown100Alpha64,
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth().padding(end = 16.dp, start = 16.dp)
                ) {
                    negativeButtonText?.let {
                        Button(
                            modifier = Modifier.weight(1f), onClick = {
                                onNegativeClick()
                            }
                        ) {
                            Text(
                                text = stringResource(it),
                                textAlign = TextAlign.Center,
                                maxLines = 1
                            )
                        }

                        Spacer(modifier = Modifier.width(6.dp))
                    }
                    positiveButtonText?.let {
                        Button(
                            modifier = Modifier.weight(1f), onClick = {
                                onPositiveClick()
                            }
                        ) {
                            Text(
                                text = stringResource(it),
                                textAlign = TextAlign.Center,
                                maxLines = 1
                            )
                        }
                    }
                }
            }

        }

    }
}