package com.joohnq.auth.ui.presentation.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.TextStyles

@Composable
fun AuthenticationOrDivider() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Box(
            modifier = Modifier.weight(1f).height(1.dp).background(color = Colors.Gray30)
        )
        Text(
            text = "or",
            style = TextStyles.textSmRegular(),
            color = Colors.Gray60
        )
        Box(
            modifier = Modifier.weight(1f).height(1.dp).background(color = Colors.Gray30)
        )
    }
}