package com.joohnq.self_journal.impl.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.spacer.VerticalSpacer
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.shared_resources.total_words
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun SelfJournalPanelInfo(
    modifier: Modifier = Modifier,
    progress: Float,
    value: String,
    title: StringResource,
    color: Color,
    icon: DrawableResource,
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier.size(56.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                progress = { progress },
                modifier = Modifier.fillMaxSize(),
                trackColor = Colors.Gray20,
                color = color
            )
            Icon(
                painter = painterResource(icon),
                contentDescription = stringResource(Res.string.total_words),
                tint = color,
                modifier = Modifier.size(24.dp)
            )
        }
        VerticalSpacer(8.dp)
        Text(
            text = value,
            style = TextStyles.textXlBold(),
            color = Colors.Gray80
        )
        VerticalSpacer(2.dp)
        Text(
            text = stringResource(title),
            style = TextStyles.textSmRegular(),
            color = Colors.Gray60
        )
    }
}
