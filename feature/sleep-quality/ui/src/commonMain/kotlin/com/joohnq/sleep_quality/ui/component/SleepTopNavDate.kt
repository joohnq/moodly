package com.joohnq.sleep_quality.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.joohnq.core.ui.getNow
import com.joohnq.core.ui.mapper.capitalize
import com.joohnq.core.ui.mapper.toMonthCompleteDayAndYear
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.TextStyles

@Composable
fun SleepTopNavDate(
    modifier: Modifier = Modifier,
) {
    val now = getNow()

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = now.dayOfWeek.name.lowercase().capitalize(),
                style = TextStyles.TextXlBold(),
                color = Colors.Brown80
            )
            Text(
                text = now.date.toMonthCompleteDayAndYear(),
                style = TextStyles.TextMdRegular(),
                color = Colors.Gray60
            )
        }
    }
}