package com.joohnq.shared_resources.components.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.spacer.HorizontalSpacer
import com.joohnq.shared_resources.start_sleeping
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.ComponentColors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun AppTimePickerCard(
    modifier: Modifier = Modifier,
    title: StringResource,
    hour: String,
    minutes: String,
    isAfternoon: Boolean,
    onClick: () -> Unit = {}
) {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp), modifier = modifier) {
        Text(
            text = stringResource(title),
            style = TextStyles.textLgExtraBold(),
            color = Colors.Brown80
        )
        Card(
            colors = ComponentColors.Card.mainCardColors(),
            shape = Dimens.Shape.Circle,
            onClick = onClick,
            modifier = Modifier.height(56.dp)
        ) {
            Row(
                modifier = Modifier.padding(10.dp).fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(Drawables.Icons.Outlined.Calendar),
                        contentDescription = stringResource(Res.string.start_sleeping),
                        tint = Colors.Brown80,
                        modifier = Modifier.size(24.dp)
                    )
                    HorizontalSpacer(15.dp)
                    Text(
                        text = "$hour:$minutes",
                        style = TextStyles.textMdBold(),
                        color = Colors.Brown100Alpha64
                    )
                    Text(
                        text = if (isAfternoon) " PM" else " AM",
                        style = TextStyles.textMdBold(),
                        color = Colors.Brown100Alpha64
                    )
                }
                HorizontalSpacer(10.dp)
                Icon(
                    painter = painterResource(Drawables.Icons.Outlined.ArrowOpen),
                    contentDescription = stringResource(Res.string.start_sleeping),
                    tint = Colors.Brown80,
                    modifier = Modifier.size(18.dp).rotate(90f)
                )
            }
        }
    }
}
