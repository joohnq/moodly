package com.joohnq.home.impl.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.joohnq.navigation.Destination
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.ui.entity.CentralAction
import org.jetbrains.compose.resources.painterResource

@Composable
fun CentralButton(
    modifier: Modifier = Modifier,
    item: CentralAction<Destination>,
    onClick: (Destination) -> Unit = {}
) {
    Column(
        modifier = modifier.clickable(onClick = { onClick(item.destination) }),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(color = Colors.Brown10, shape = Dimens.Shape.Circle)
                .clip(Dimens.Shape.Circle),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(item.icon),
                contentDescription = item.title,
                tint = Colors.Brown60,
                modifier = Modifier.size(24.dp)
            )
        }
        Text(
            text = item.title,
            style = TextStyles.textSmRegular(),
            color = Colors.Gray60
        )
    }
}