package com.joohnq.onboarding.impl.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.joohnq.user.impl.ui.parameter.MedicationsSupplementsResourceParameterProvider
import com.joohnq.user.impl.ui.resource.MedicationsSupplementsResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun MedicationsSupplementsRadioButtonPreview(
    @PreviewParameter(MedicationsSupplementsResourceParameterProvider::class)
    item: MedicationsSupplementsResource
) {
    Column {
        MedicationsSupplementsRadioButton(
            text = "Option",
            icon = item.icon,
            selected = false
        )
        MedicationsSupplementsRadioButton(
            text = "Option",
            icon = item.icon,
            selected = true
        )
    }
}
