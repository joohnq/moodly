package com.joohnq.mood.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.mood.domain.entity.MoodRecord
import com.joohnq.mood.ui.resource.MoodRecordResource
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.SectionHeader
import com.joohnq.shared_resources.components.VerticalSpacer
import com.joohnq.shared_resources.description
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.TextStyles

@Composable
fun DescriptionSection(modifier: Modifier = Modifier, record: MoodRecordResource?) {
    if (record != null) {
        SectionHeader(
            modifier = modifier,
            title = Res.string.description,
        )
        VerticalSpacer(10.dp)
        Text(
            text = record.description,
            style = TextStyles.TextMdSemiBold(),
            color = Colors.Brown80,
            modifier = modifier
        )
        VerticalSpacer(40.dp)
    }
}
