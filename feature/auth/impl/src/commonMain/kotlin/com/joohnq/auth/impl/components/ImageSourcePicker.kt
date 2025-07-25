package com.joohnq.auth.impl.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.camera
import com.joohnq.shared_resources.components.spacer.VerticalSpacer
import com.joohnq.shared_resources.components.button.TextAndIconButton
import com.joohnq.shared_resources.gallery
import com.joohnq.shared_resources.select_an_image_source
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.ComponentColors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.ui.entity.IconResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageSourcePicker(
    onDismissRequest: () -> Unit = {},
    onGalleryRequest: () -> Unit = {},
    onCameraRequest: () -> Unit = {},
) {
    val sheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        containerColor = Colors.Brown10,
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
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
                TextAndIconButton(
                    modifier = Modifier.weight(1f).height(60.dp),
                    text = Res.string.camera,
                    icon = IconResource(
                        icon = Drawables.Icons.Outlined.Camera,
                        tint = Colors.White,
                        modifier = Modifier.size(32.dp),
                        contentDescription = Res.string.camera
                    ),
                    colors = ComponentColors.Button.MainButtonColors(),
                    shape = Dimens.Shape.Circle,
                    onClick = { onCameraRequest() }
                )
                TextAndIconButton(
                    modifier = Modifier.weight(1f).height(60.dp),
                    text = Res.string.gallery,
                    icon = IconResource(
                        icon = Drawables.Icons.Outlined.Gallery,
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