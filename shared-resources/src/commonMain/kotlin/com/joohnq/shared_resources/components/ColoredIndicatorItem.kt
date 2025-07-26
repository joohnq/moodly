package com.joohnq.shared_resources.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.TextStyles

@Composable
fun ColoredIndicatorItem(
    title: String,
    description: String,
    color: Color,
    isNotLast: Boolean,
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier =
                        Modifier
                            .size(10.dp)
                            .background(color = color, shape = Dimens.Shape.Circle)
                            .clip(Dimens.Shape.Circle)
                )
                Text(
                    text = title,
                    style = TextStyles.textSmRegular(),
                    color = Colors.Gray80
                )
            }
            Text(
                text = description,
                style = TextStyles.textSmSemiBold(),
                color = Colors.Gray80
            )
        }
        if (isNotLast) {
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                color = Colors.Gray20
            )
        }
    }
}
