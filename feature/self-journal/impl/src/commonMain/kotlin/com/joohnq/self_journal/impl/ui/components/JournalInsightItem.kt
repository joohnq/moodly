package com.joohnq.self_journal.impl.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.components.HorizontalSpacer
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun JournalInsightItem(
    count: Int,
    percentage: Float,
    color: Color,
    face: DrawableResource
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = count.toString(),
            style = TextStyles.TextSmBold(),
            color = Colors.Gray60
        )
        HorizontalSpacer(10.dp)
        LinearProgressIndicator(
            modifier = Modifier.height(12.dp).weight(1f),
            progress = { percentage },
            trackColor = Colors.Gray20,
            color = color,
        )
        HorizontalSpacer(10.dp)
        Icon(
            painter = painterResource(face),
            contentDescription = null,
            tint = Colors.Gray40,
            modifier = Modifier.size(24.dp)
        )
    }
}