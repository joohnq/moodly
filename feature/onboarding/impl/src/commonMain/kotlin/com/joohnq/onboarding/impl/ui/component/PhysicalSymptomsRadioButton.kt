package com.joohnq.onboarding.impl.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.components.IconResource
import com.joohnq.shared_resources.theme.ComponentColors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.ui.entity.IconResource

@Composable
fun PhysicalSymptomsRadioButton(
    modifier: Modifier = Modifier,
    text: String,
    icon: IconResource,
    selected: Boolean,
    onClick: () -> Unit = {},
) {
    val colors = ComponentColors.RadioButton.textRadioButtonColors()
    Button(
        modifier = modifier,
        shape = Dimens.Shape.Medium,
        colors = ComponentColors.Button.textRadioButtonColors(selected = selected, colors = colors),
        border =
            if (selected) {
                BorderStroke(
                    color = colors.selectedBorderColor,
                    width = 4.dp
                )
            } else {
                null
            },
        contentPadding = PaddingValues(0.dp),
        onClick = onClick
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth().padding(PaddingValues(all = 16.dp)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconResource(
                    icon.copy(
                        modifier = Modifier.size(Dimens.Icon),
                        tint = if (selected) colors.selectedContentColor else colors.unSelectedContentColor
                    )
                )
                Text(
                    text = text,
                    style = TextStyles.textLgExtraBold()
                )
            }
            RadioButton(
                selected = selected,
                onClick = onClick,
                colors = ComponentColors.RadioButton.iconAndTextRadioButtonHorizontalColors(colors)
            )
        }
    }
}
