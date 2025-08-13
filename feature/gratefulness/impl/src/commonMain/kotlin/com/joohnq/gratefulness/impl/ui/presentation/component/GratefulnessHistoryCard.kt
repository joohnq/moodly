package com.joohnq.gratefulness.impl.ui.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.joohnq.api.mapper.LocalDateTimeMapper.toMonthAbbreviatedAndDayString
import com.joohnq.gratefulness.api.entity.Gratefulness
import com.joohnq.shared_resources.Res
import com.joohnq.shared_resources.components.layout.CardWithMoreMenuLayout
import com.joohnq.shared_resources.gratefulness_overview_title
import com.joohnq.shared_resources.theme.Colors
import com.joohnq.shared_resources.theme.Drawables
import com.joohnq.shared_resources.theme.PaddingModifier.paddingAllSmall
import com.joohnq.shared_resources.theme.TextStyles
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun GratefulnessHistoryCard(
    modifier: Modifier = Modifier,
    gratefulness: Gratefulness,
    onDelete: (Int) -> Unit,
) {
    CardWithMoreMenuLayout(
        modifier = modifier,
        menuContainerColor = Colors.Brown80,
        onDelete = { onDelete(gratefulness.id) }
    ) { modifier ->
        Row(
            modifier = modifier.paddingAllSmall(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Icon(
                    painter = painterResource(Drawables.Icons.Filled.HandHeart),
                    contentDescription = null,
                    tint = Colors.Brown60,
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text =
                        stringResource(
                            Res.string.gratefulness_overview_title,
                            gratefulness.iAmGratefulFor
                        ),
                    style = TextStyles.textMdBold(),
                    color = Colors.Gray80
                )
            }
            Text(
                text =
                    gratefulness.createdAt.toMonthAbbreviatedAndDayString(),
                style = TextStyles.textSmRegular(),
                color = Colors.Gray60
            )
        }
    }
}
