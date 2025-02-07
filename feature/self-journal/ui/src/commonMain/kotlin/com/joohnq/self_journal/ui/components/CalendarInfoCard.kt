package com.joohnq.self_journal.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.joohnq.core.ui.entity.CalendarInfo
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.stringResource

@Composable
fun CalendarInfoCard(info: CalendarInfo) {
    Row(horizontalArrangement = Arrangement.spacedBy(6.dp), verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(6.dp)
                .clip(Dimens.Shape.Circle)
                .background(color = info.backgroundColor, shape = Dimens.Shape.Circle)
                .border(
                    width = 1.dp,
                    color = info.borderColor,
                    shape = Dimens.Shape.Circle
                )
        )
        Text(
            text = stringResource(info.text),
            style = TextStyles.TextXsMedium(),
            color = Colors.Gray60
        )
    }
}
