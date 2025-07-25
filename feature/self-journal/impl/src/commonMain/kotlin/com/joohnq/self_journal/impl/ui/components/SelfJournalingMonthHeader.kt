package com.joohnq.self_journal.impl.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.remember.rememberWeekChars
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.stringResource

@Composable
fun SelfJournalingMonthHeader() {
    Row(modifier = Modifier.padding(bottom = 10.dp)) {
        val weekChars = rememberWeekChars()
        weekChars.forEach { day ->
            Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                Text(
                    text = stringResource(day),
                    style = TextStyles.textSmBold(),
                    color = Colors.Gray80
                )
            }
        }
    }
}