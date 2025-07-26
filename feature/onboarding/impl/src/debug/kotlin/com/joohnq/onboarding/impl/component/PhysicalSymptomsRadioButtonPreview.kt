package com.joohnq.onboarding.impl.component

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.joohnq.onboarding.impl.ui.component.PhysicalSymptomsRadioButton
import com.joohnq.user.impl.ui.parameter.PhysicalSymptomsResourceParameterProvider
import com.joohnq.user.impl.ui.resource.PhysicalSymptomsResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun PhysicalSymptomsRadioButtonPreview(
    @PreviewParameter(PhysicalSymptomsResourceParameterProvider::class)
    item: PhysicalSymptomsResource
) {
    Column {
        PhysicalSymptomsRadioButton(
            text = "Option",
            icon = item.icon,
            selected = false
        )
        PhysicalSymptomsRadioButton(
            text = "Option",
            icon = item.icon,
            selected = true
        )
    }
}