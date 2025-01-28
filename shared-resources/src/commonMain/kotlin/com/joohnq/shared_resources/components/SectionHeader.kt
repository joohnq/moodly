package com.joohnq.shared_resources.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.see_all
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun SectionHeader(
    modifier: Modifier = Modifier,
    title: StringResource,
    onSeeAll: () -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(title),
            style = TextStyles.TextLgExtraBold(),
            color = Colors.Brown80,
            modifier = Modifier.padding(vertical = 20.dp)
        )
        Text(
            modifier = Modifier.clickable { onSeeAll() },
            text = stringResource(Res.string.see_all),
            style = TextStyles.TextSmMedium(),
            color = Colors.Brown80
        )
    }
}