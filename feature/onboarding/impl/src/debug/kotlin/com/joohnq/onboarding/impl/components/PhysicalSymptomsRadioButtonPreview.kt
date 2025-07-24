package com.joohnq.onboarding.impl.components

import androidx.compose.runtime.Composable
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.app_name
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.ui.entity.IconResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun PhysicalSymptomsRadioButtonNotSelectedPreview() {
    PhysicalSymptomsRadioButton(
        text = "Option",
        icon = IconResource(
            icon = Drawables.Icons.Outlined.Logo,
            contentDescription = Res.string.app_name,
        ),
        selected = false,
    )
}

@Preview
@Composable
fun PhysicalSymptomsRadioButtonSelectedPreview() {
    PhysicalSymptomsRadioButton(
        text = "Option",
        icon = IconResource(
            icon = Drawables.Icons.Outlined.Logo,
            contentDescription = Res.string.app_name,
        ),
        selected = false,
    )
}