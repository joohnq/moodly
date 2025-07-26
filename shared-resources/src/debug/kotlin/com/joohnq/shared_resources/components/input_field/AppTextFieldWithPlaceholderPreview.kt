package com.joohnq.shared_resources.components.input_field

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.app_name
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.ComponentColors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun AppTextFieldWithPlaceholderPreview() {
    AppTextFieldWithPlaceholder(
        label = Res.string.app_name,
        placeholder = Res.string.app_name,
        text = "Name",
        focusedBorderColor = Colors.Green50Alpha25,
        leadingIcon = {
            Icon(
                painter = painterResource(Drawables.Icons.Outlined.User),
                contentDescription = null,
                tint = Colors.Brown80,
                modifier = Modifier.size(Dimens.Icon)
            )
        },
        colors = ComponentColors.TextField.mainTextFieldColors()
    )
}
