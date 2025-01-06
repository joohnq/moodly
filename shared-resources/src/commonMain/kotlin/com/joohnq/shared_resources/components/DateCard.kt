package com.joohnq.shared_resources.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.TextStyles
import kotlinx.datetime.LocalDate

@Composable
fun DateCard(
    isSelected: Boolean,
    date: LocalDate,
    onClick: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.background(
            color = if (isSelected) Colors.White else Colors.Brown70,
            shape = Dimens.Shape.Circle
        ).padding(horizontal = 10.dp, vertical = 15.dp)
            .clickable { onClick() }
    ) {
        Text(
            text = "daw",
            style = TextStyles.TextXsBold(),
            color = if (isSelected) Colors.Brown80 else Colors.Brown20
        )
        VerticalSpacer(5.dp)
        Text(
            text = date.dayOfMonth.toString(),
            style = TextStyles.TextMdExtraBold(),
            color = if (isSelected) Colors.Brown80 else Colors.Brown20
        )
    }
}