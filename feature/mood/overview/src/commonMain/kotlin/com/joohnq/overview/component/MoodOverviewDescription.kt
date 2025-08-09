package com.joohnq.overview.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.spacer.VerticalSpacer
import com.joohnq.shared_resources.components.text.SectionHeader
import com.joohnq.shared_resources.description
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.TextStyles

@Composable
fun MoodOverviewDescription(
    modifier: Modifier = Modifier,
    description: String,
) {
    SectionHeader(
        modifier = modifier,
        title = Res.string.description
    )
    Text(
        text = description,
        style = TextStyles.textMdSemiBold(),
        color = Colors.Brown80,
        modifier = modifier
    )
    VerticalSpacer(20.dp)
}
