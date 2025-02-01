package com.joohnq.stress_level.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.joohnq.core.ui.mapper.toMonthAbbreviatedAndDayString
import com.joohnq.shared_resources.components.HorizontalSpacer
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Dimens
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.TextStyles
import com.joohnq.stress_level.domain.entity.StressLevelRecord
import com.joohnq.stress_level.ui.mapper.toResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun StressLevelCard(
    modifier: Modifier = Modifier,
    record: StressLevelRecord,
) {
    val resource = record.stressLevel.toResource()
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardColors(
            containerColor = Colors.Gray5,
            contentColor = Colors.Gray80,
            disabledContainerColor = Colors.Gray5,
            disabledContentColor = Colors.Gray80
        ),
        shape = Dimens.Shape.Medium,
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painter = painterResource(Drawables.Icons.Warning),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = Colors.Orange40
            )
            HorizontalSpacer(20.dp)
            Column(
                modifier = Modifier.fillMaxHeight().weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(resource.subtitle),
                    style = TextStyles.TextMdBold(),
                    color = Colors.Brown80
                )
                if (record.stressors.isNotEmpty())
                    Text(
                        text = record.stressors.joinToString(", "),
                        style = TextStyles.TextSmMedium(),
                        color = Colors.Brown80,
                        overflow = TextOverflow.Ellipsis
                    )
            }
            HorizontalSpacer(20.dp)
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = record.createdAt.toMonthAbbreviatedAndDayString(),
                    style = TextStyles.TextSmRegular(),
                    color = Colors.Gray60
                )
                Icon(
                    painter = painterResource(Drawables.Icons.ArrowOpen),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp).rotate(180f),
                    tint = Colors.Gray60
                )
            }
        }
    }
}