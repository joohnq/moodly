package com.joohnq.onboarding.impl.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.material3.Button
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
fun MedicationsSupplementsRadioButton(
    modifier: Modifier = Modifier,
    text: String,
    icon: IconResource,
    selected: Boolean,
    onClick: () -> Unit = {},
) {
    val colors = ComponentColors.RadioButton.TextRadioButtonColors()

    Button(
        modifier = modifier.aspectRatio(1f),
        shape = Dimens.Shape.Medium,
        colors = ComponentColors.Button.TextRadioButtonColors(selected = selected, colors = colors),
        border = if (selected) BorderStroke(
            color = colors.selectedBorderColor,
            width = 4.dp
        ) else null,
        contentPadding = PaddingValues(0.dp),
        onClick = onClick
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize().padding(PaddingValues(all = 16.dp)),
            horizontalAlignment = Alignment.Start
        ) {
            IconResource(
                icon.copy(
                    tint = if (selected) colors.selectedContentColor else colors.unSelectedContentColor,
                    modifier = Modifier.size(Dimens.Icon)
                )
            )
            Text(
                text = text,
                style = TextStyles.TextMdBold(),
                color = if (selected) colors.selectedContentColor else colors.unSelectedContentColor
            )
        }
    }
}